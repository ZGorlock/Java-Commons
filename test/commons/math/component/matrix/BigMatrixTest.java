/*
 * File:    BigMatrixTest.java
 * Package: commons.math.component.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.matrix;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import commons.math.MathUtility;
import commons.math.component.BaseComponent;
import commons.math.component.ComponentInterface;
import commons.math.component.handler.error.ComponentErrorHandlerInterface;
import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import commons.math.component.handler.math.BigComponentMathHandler;
import commons.math.component.vector.BigVector;
import commons.math.component.vector.IntVector;
import commons.math.component.vector.Vector;
import commons.math.component.vector.VectorInterface;
import commons.string.StringUtility;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of BigMatrix.
 *
 * @see BigMatrix
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigMatrix.class})
public class BigMatrixTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigMatrixTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private BigMatrix sut;
    
    
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
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#BigMatrix(BigDecimal...)
     * @see BigMatrix#BigMatrix(double...)
     * @see BigMatrix#BigMatrix(String...)
     * @see BigMatrix#BigMatrix(List)
     * @see BigMatrix#BigMatrix(BigMatrix)
     * @see BigMatrix#BigMatrix(BigMatrix)
     * @see BigMatrix#BigMatrix(int)
     * @see BigMatrix#BigMatrix()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        BigMatrix matrix = new BigMatrix(new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")}, matrix.getRawComponents());
        Assert.assertEquals(3, matrix.getDimensionality());
        
        //double components
        BigMatrix matrix2 = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8.0"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")}, matrix2.getRawComponents());
        Assert.assertEquals(3, matrix.getDimensionality());
        
        //string components
        BigMatrix matrix3 = new BigMatrix("0.884", "2", "1.1", "-9.3", "1.61", "8", "-0.77", "5.06", "4.4");
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")}, matrix3.getRawComponents());
        Assert.assertEquals(3, matrix.getDimensionality());
        
        //list of components
        List<BigDecimal> values = Arrays.asList(new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4"));
        BigMatrix matrix4 = new BigMatrix(values);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8.0"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")}, matrix2.getRawComponents());
        Assert.assertEquals(3, matrix2.getDimensionality());
        
        //big matrix
        BigMatrix matrix5 = new BigMatrix(new BigMatrix(new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")}, matrix5.getRawComponents());
        Assert.assertEquals(3, matrix5.getDimensionality());
        
        //matrix
        BigMatrix matrix6 = new BigMatrix(new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.88400000000000000799360577730112709105014801025390625"), new BigDecimal("2"), new BigDecimal("1.100000000000000088817841970012523233890533447265625"), new BigDecimal("-9.300000000000000710542735760100185871124267578125"), new BigDecimal("1.6100000000000000976996261670137755572795867919921875"), new BigDecimal("8"), new BigDecimal("-0.770000000000000017763568394002504646778106689453125"), new BigDecimal("5.05999999999999960920149533194489777088165283203125"), new BigDecimal("4.4000000000000003552713678800500929355621337890625")}, matrix6.getRawComponents());
        Assert.assertEquals(3, matrix6.getDimensionality());
        
        //dimensionality
        BigMatrix matrixDimensionality = new BigMatrix(4);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")}, matrixDimensionality.getRawComponents());
        Assert.assertEquals(4, matrixDimensionality.getDimensionality());
        
        //empty
        BigMatrix matrixDefault = new BigMatrix();
        Assert.assertArrayEquals(new Double[] {}, matrixDefault.getRawComponents());
        Assert.assertEquals(0, matrixDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(matrix, matrix2);
        Assert.assertEquals(matrix2, matrix3);
        Assert.assertEquals(matrix3, matrix4);
        Assert.assertEquals(matrix4, matrix5);
        
        //invalid
        
        TestUtils.assertException(ArithmeticException.class, new BigMatrix().getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06}), () ->
                new BigMatrix(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06));
        
        TestUtils.assertException(NullPointerException.class, () ->
                new BigMatrix(Arrays.asList(0.884, null, 1.1, -9.3)));
        TestUtils.assertException(NullPointerException.class, () ->
                new BigMatrix((BigMatrix) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new BigMatrix((Matrix) null));
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        //standard
        
        sut = new BigMatrix(1.0);
        Assert.assertEquals("[<1>]", sut.matrixString());
        
        sut = new BigMatrix(1, 2, 3, 4);
        Assert.assertEquals("[<1, 2>, <3, 4>]", sut.matrixString());
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1, 2, 3>, <4, 5, 6>, <7, 8, 9>]", sut.matrixString());
        
        sut = new BigMatrix(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("[<8.15, 77.1654, 3>, <3.66, 7.15, 890.1>, <11, 7.9888, 0.79455>]", sut.matrixString());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("1.0405650498540941874980498641878019451498248105484"));
        Assert.assertEquals("[<1.040565049854094187498049864187801945>]", sut.matrixString());
        
        sut = new BigMatrix(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"), new BigDecimal("3.049879819842684284189481749807987819878971978971"), new BigDecimal("4.45642136569789098404109190470454984849845156"));
        Assert.assertEquals("[<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705>, <3.04987981984268428418948174980798782, 4.456421365697890984041091904704549848>]", sut.matrixString());
        
        sut = new BigMatrix(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"), new BigDecimal("3.049879819842684284189481749807987819878971978971"), new BigDecimal("4.45642136569789098404109190470454984849845156"), new BigDecimal("5.1312156941890181498428720078407484450904908"), new BigDecimal("6.674650596084657409841510212131065151984"), new BigDecimal("7.6098789408415640894804540564321065154980747848"), new BigDecimal("8.4549807546543213215818048482848429842854988"), new BigDecimal("9.9870808798056051210515913121904848490879879845046"));
        Assert.assertEquals("[<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705, 3.04987981984268428418948174980798782>, <4.456421365697890984041091904704549848, 5.131215694189018149842872007840748445, 6.674650596084657409841510212131065152>, <7.609878940841564089480454056432106515, 8.454980754654321321581804848284842984, 9.987080879805605121051591312190484849>]", sut.matrixString());
        
        sut = new BigMatrix(new BigDecimal("8.150000000000000000000000000000000000000001"), new BigDecimal("77.16540000000000000000000000000000000000000001"), new BigDecimal("3.0000000000000000000000000000000000000001"), new BigDecimal("3.660000000000000000000000000000000000000001"), new BigDecimal("7.150000000000000000000000000000000000000001"), new BigDecimal("890.10000000000000000000000000000000000000001"), new BigDecimal("11.0000000000000000000000000000000000000001"), new BigDecimal("7.98880000000000000000000000000000000000000001"), new BigDecimal("0.794550000000000000000000000000000000000000001"));
        Assert.assertEquals("[<8.15, 77.1654, 3>, <3.66, 7.15, 890.1>, <11, 7.9888, 0.79455>]", sut.matrixString());
        
        //empty
        
        sut = new BigMatrix();
        Assert.assertEquals("[]", sut.matrixString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new BigMatrix(1.0);
        Assert.assertEquals("[<1>]", sut.toString());
        
        sut = new BigMatrix(1, 2, 3, 4);
        Assert.assertEquals("[<1, 2>, <3, 4>]", sut.toString());
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1, 2, 3>, <4, 5, 6>, <7, 8, 9>]", sut.toString());
        
        sut = new BigMatrix(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("[<8.15, 77.1654, 3>, <3.66, 7.15, 890.1>, <11, 7.9888, 0.79455>]", sut.toString());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("1.0405650498540941874980498641878019451498248105484"));
        Assert.assertEquals("[<1.040565049854094187498049864187801945>]", sut.toString());
        
        sut = new BigMatrix(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"), new BigDecimal("3.049879819842684284189481749807987819878971978971"), new BigDecimal("4.45642136569789098404109190470454984849845156"));
        Assert.assertEquals("[<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705>, <3.04987981984268428418948174980798782, 4.456421365697890984041091904704549848>]", sut.toString());
        
        sut = new BigMatrix(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"), new BigDecimal("3.049879819842684284189481749807987819878971978971"), new BigDecimal("4.45642136569789098404109190470454984849845156"), new BigDecimal("5.1312156941890181498428720078407484450904908"), new BigDecimal("6.674650596084657409841510212131065151984"), new BigDecimal("7.6098789408415640894804540564321065154980747848"), new BigDecimal("8.4549807546543213215818048482848429842854988"), new BigDecimal("9.9870808798056051210515913121904848490879879845046"));
        Assert.assertEquals("[<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705, 3.04987981984268428418948174980798782>, <4.456421365697890984041091904704549848, 5.131215694189018149842872007840748445, 6.674650596084657409841510212131065152>, <7.609878940841564089480454056432106515, 8.454980754654321321581804848284842984, 9.987080879805605121051591312190484849>]", sut.toString());
        
        sut = new BigMatrix(new BigDecimal("8.150000000000000000000000000000000000000001"), new BigDecimal("77.16540000000000000000000000000000000000000001"), new BigDecimal("3.0000000000000000000000000000000000000001"), new BigDecimal("3.660000000000000000000000000000000000000001"), new BigDecimal("7.150000000000000000000000000000000000000001"), new BigDecimal("890.10000000000000000000000000000000000000001"), new BigDecimal("11.0000000000000000000000000000000000000001"), new BigDecimal("7.98880000000000000000000000000000000000000001"), new BigDecimal("0.794550000000000000000000000000000000000000001"));
        Assert.assertEquals("[<8.15, 77.1654, 3>, <3.66, 7.15, 890.1>, <11, 7.9888, 0.79455>]", sut.toString());
        
        //empty
        
        sut = new BigMatrix();
        Assert.assertEquals("[]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigMatrix(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new Matrix().equals(new Matrix()));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("8.5"), new BigDecimal("-1.944"), new BigDecimal("2.67"), new BigDecimal("8.0"));
        
        other = new BigMatrix(new BigDecimal("8.5"), new BigDecimal("-1.944"), new BigDecimal("2.67"), new BigDecimal("8.0"));
        Assert.assertTrue(sut.equals(other));
        
        other = new BigMatrix(new BigDecimal("8.500000000000000000000000000000000001"), new BigDecimal("-1.944000000000000000000000000000000001"), new BigDecimal("2.670000000000000000000000000000000001"), new BigDecimal("8.000000000000000000000000000000000001"));
        Assert.assertFalse(sut.equals(other));
        
        other = new BigMatrix(new BigDecimal("8.5000000000000000000000000000000000001"), new BigDecimal("-1.9440000000000000000000000000000000001"), new BigDecimal("2.6700000000000000000000000000000000001"), new BigDecimal("8.0000000000000000000000000000000000001"));
        Assert.assertTrue(sut.equals(other));
        
        //invalid
        
        Assert.assertFalse(sut.equals(""));
        Assert.assertFalse(sut.equals(BigDecimal.valueOf(8.5)));
        Assert.assertFalse(sut.equals(new File(".")));
        Assert.assertFalse(sut.equals(null));
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigMatrix(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new Matrix().dimensionalityEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigMatrix(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        Assert.assertTrue(new Matrix().lengthEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigMatrix(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new Matrix().componentTypeEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new BigMatrix(8.1, 6.6, 5, 1.09);
        BigMatrix clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), clone.hashCode());
        
        //big
        sut = new BigMatrix(new BigDecimal("8.1798906456468046140621074678418708"), new BigDecimal("6.68987846515405151504957982720704"), new BigDecimal("5.08917984982428719878814854359782987184"), new BigDecimal("1.097084949814687678164861560484198265140"));
        clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), clone.hashCode());
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new BigMatrix(8.1, 6.6, 5, 1.09);
        BigMatrix emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(BigMatrix.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), emptyCopy.hashCode());
        
        //big
        sut = new BigMatrix(new BigDecimal("8.1798906456468046140621074678418708"), new BigDecimal("6.68987846515405151504957982720704"), new BigDecimal("5.08917984982428719878814854359782987184"), new BigDecimal("1.097084949814687678164861560484198265140"));
        emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(BigMatrix.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), emptyCopy.hashCode());
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //standard
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigVector(), sut.newVector());
        
        sut = new BigMatrix(8.1);
        Assert.assertEquals(
                new BigVector(0.0), sut.newVector());
        
        sut = new BigMatrix(8.1, 6.6, 5, 1.09);
        Assert.assertEquals(
                new BigVector(0.0, 0.0), sut.newVector());
        
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0), sut.newVector());
        
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0, 0.0), sut.newVector());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new BigMatrix();
        
        //standard
        Assert.assertEquals(
                new BigMatrix(), sut.createNewInstance(0));
        Assert.assertEquals(
                new BigMatrix(0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new BigMatrix(), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //standard
        
        sut = new BigMatrix(1.0);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        
        sut = new BigMatrix(1, 2, 3, 4);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(3, sut.toIndex(1, 1));
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(4, sut.toIndex(1, 1));
        Assert.assertEquals(7, sut.toIndex(1, 2));
        
        //invalid
        
        sut = new BigMatrix();
        Assert.assertEquals(9, sut.toIndex(9, 9));
        
        sut = new BigMatrix(1.0);
        Assert.assertEquals(18, sut.toIndex(9, 9));
        
        sut = new BigMatrix(1, 2, 3, 4);
        Assert.assertEquals(27, sut.toIndex(9, 9));
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(6, sut.toIndex(3, 1));
        Assert.assertEquals(36, sut.toIndex(9, 9));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        BigMatrix reversed;
        
        //standard
        
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("4.4"), new BigDecimal("5.06"), new BigDecimal("-0.77"), new BigDecimal("8.0"), new BigDecimal("1.61"), new BigDecimal("-9.3"), new BigDecimal("1.1"), new BigDecimal("2.0"), new BigDecimal("0.884")}, reversed.getRawComponents());
        
        sut = new BigMatrix(4.4, 5.06, -0.77, 8, 1.61, -9.3, 1.1, 2, 0.884);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8.0"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")}, reversed.getRawComponents());
        
        sut = new BigMatrix(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("1.0"), new BigDecimal("0.0"), new BigDecimal("1.0"), new BigDecimal("0.0")}, reversed.getRawComponents());
        
        sut = new BigMatrix(5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501")}, reversed.getRawComponents());
        
        sut = new BigMatrix();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {}, reversed.getRawComponents());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("5.50104550454564410654564549828941987897287189798208908")}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        BigMatrix other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigMatrix(9.9);
        Assert.assertEquals(new BigDecimal("1.4"), sut.distance(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(new BigDecimal("9.5457006028892399994010021760928316222872261431602090253993433634"), sut.distance(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(new BigDecimal("12.6039834972916399800374534766039829628976848032225978653512166357"), sut.distance(other));
        
        sut = new BigMatrix();
        other = new BigMatrix();
        Assert.assertEquals(new BigDecimal("0"), sut.distance(other));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertEquals(new BigDecimal("17.2425622421743585454887599136206374345752656859328451165495011172"), sut.distance(other));
        
        //invalid
        
        sut = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final BigMatrix other1 = new BigMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new BigMatrix(8.5);
        final BigMatrix other2 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other3 = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        BigMatrix other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigMatrix(9.9);
        Assert.assertEquals(
                new BigMatrix(9.2), sut.midpoint(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigMatrix(9.2, 3.75, -2.246, 8.0), sut.midpoint(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new BigMatrix(9.2, 3.75, -2.246, 8.0, 3.05, 6.0, 3.55, 7.2, 6.6), sut.midpoint(other));
        
        sut = new BigMatrix();
        other = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.midpoint(other));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("4.639591154764864500317533151569634144"), new BigDecimal("4.273089282009695211033211987005428017"), new BigDecimal("-2.73772048859848732380088993503498507"), new BigDecimal("3.53558413004928104732340634473625508")), sut.midpoint(other));
        
        //invalid
        
        sut = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final BigMatrix other1 = new BigMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new BigMatrix(8.5);
        final BigMatrix other2 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other3 = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#average(List)
     * @see BigMatrix#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        BigMatrix other1;
        BigMatrix other2;
        BigMatrix other3;
        BigMatrix other4;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other1 = new BigMatrix(9.9);
        other2 = new BigMatrix(1.8);
        other3 = new BigMatrix(11.7);
        Assert.assertEquals(
                new BigMatrix(7.975), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigMatrix(7.975), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other1 = new BigMatrix(9.9, 6, 0.514, 4.9);
        other2 = new BigMatrix(1.8, 4.77, 0.514, 2.895);
        other3 = new BigMatrix(11.7, 0.447, 7.16, 8);
        Assert.assertEquals(
                new BigMatrix(7.975, 3.17925, 0.7955, 6.72375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigMatrix(7.975, 3.17925, 0.7955, 6.72375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other1 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        other2 = new BigMatrix(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1);
        other3 = new BigMatrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5);
        Assert.assertEquals(
                new BigMatrix(7.975, 3.17925, 0.7955, 6.72375, 2.975, 7.225, 3.4675, 5.45, 6.325), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigMatrix(7.975, 3.17925, 0.7955, 6.72375, 2.975, 7.225, 3.4675, 5.45, 6.325), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new BigMatrix();
        other1 = new BigMatrix();
        other2 = new BigMatrix();
        other3 = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigMatrix(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other1 = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        other2 = new BigMatrix(new BigDecimal("-4.9808941892654987824898978109784289484084"), new BigDecimal("3.140650987988972817842549879872884659872871889789"), new BigDecimal("7.1059790875459424984087804084565492845408"), new BigDecimal("4.1449080800840084549048489428425982798272"));
        other3 = new BigMatrix(new BigDecimal("9.0001650857987892749879821782987804519541981408"), new BigDecimal("1.1590498177787828768798789798278778982"), new BigDecimal("2.101509540840845465045425629817897850484184098"), new BigDecimal("-0.045640928948489489789782798729872817985982484284"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("3.324613301515754873283287667614904948"), new BigDecimal("3.211469842446786529197213208427904648"), new BigDecimal("0.933011912797453328963106542051119249"), new BigDecimal("2.792608852808520264940469708396308905")), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("3.324613301515754873283287667614904948"), new BigDecimal("3.211469842446786529197213208427904648"), new BigDecimal("0.933011912797453328963106542051119249"), new BigDecimal("2.792608852808520264940469708396308905")), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final BigMatrix otherF11 = new BigMatrix(9.9, 6, 0.514, 4.9);
        final BigMatrix otherF12 = new BigMatrix(1.8, 4.77, 0.514, 2.895);
        final BigMatrix otherF13 = new BigMatrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix otherF21 = new BigMatrix(9.9, 6, 0.514, 4.9);
        final BigMatrix otherF22 = new BigMatrix(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1);
        final BigMatrix otherF23 = new BigMatrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix otherF31 = new BigMatrix(9.9, 6, 0.514, 4.9);
        final BigMatrix otherF33 = new BigMatrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5);
        Assert.assertEquals(new BigDecimal("8.5"), sut.sum());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(new BigDecimal("16.094"), sut.sum());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(new BigDecimal("46.494"), sut.sum());
        
        sut = new BigMatrix();
        Assert.assertEquals(new BigDecimal("0"), sut.sum());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(new BigDecimal("14.27930330576381653738281831944605481736151229278208908"), sut.sum());
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5);
        Assert.assertEquals(new BigDecimal("72.25"), sut.squareSum());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(new BigDecimal("222.770036"), sut.squareSum());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(new BigDecimal("445.350036"), sut.squareSum());
        
        sut = new BigMatrix();
        Assert.assertEquals(new BigDecimal("0"), sut.squareSum());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(new BigDecimal("105.9718866350571232109486269962437550295981324607529420086544148987"), sut.squareSum());
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        BigMatrix other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigMatrix(9.9);
        Assert.assertEquals(
                new BigMatrix(18.4), sut.plus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigMatrix(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new BigMatrix(18.4, 7.5, -4.492, 16.0, 6.1, 12.0, 7.1, 14.4, 13.2), sut.plus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new BigMatrix(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new BigMatrix();
        other = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.plus(other));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("9.279182309529729000635066303139268288"), new BigDecimal("8.546178564019390422066423974010856034"), new BigDecimal("-5.475440977196974647601779870069970139"), new BigDecimal("7.071168260098562094646812689472510159")), sut.plus(other));
        
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other1 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigMatrix other2 = new BigMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new BigMatrix(8.5);
        final BigMatrix other3 = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        BigMatrix other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigMatrix(9.9);
        Assert.assertEquals(
                new BigMatrix(-1.4), sut.minus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigMatrix(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new BigMatrix(-1.4, -4.5, -5.52, 6.2, 2.5, 6.0, -3.9, 0.2, 3.2), sut.minus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new BigMatrix(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new BigMatrix();
        other = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.minus(other));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("1.72290869956155921245622469343957147"), new BigDecimal("7.550018873926369323569547474320045934"), new BigDecimal("10.82554072117153428339583781361571383"), new BigDecimal("-10.960949839582536614402496439035885941")), sut.minus(other));
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other1 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigMatrix other2 = new BigMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new BigMatrix(8.5);
        final BigMatrix other3 = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#times(BigMatrix)
     */
    @Test
    public void testTimes() throws Exception {
        BigMatrix other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigMatrix(9.9);
        Assert.assertEquals(
                new BigMatrix(84.15), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigMatrix(84.921, 58.35, -43.854, 24.354), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new BigMatrix(63.967, 18.1574, -16.161, 180.46, 138.24, 63.6054, 96.71, 80.96, 63.7224), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new BigMatrix(10.0, 10.0, 6.094, 6.094), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new BigMatrix();
        other = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.times(other));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("-44.812252475703835130777587440638230359"), new BigDecimal("75.302093181703463606164411381045225691"), new BigDecimal("25.958518960977911715428105113244257536"), new BigDecimal("-16.202861780073665095120030687483350713")), sut.times(other));
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other1 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigMatrix other2 = new BigMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new BigMatrix(8.5);
        final BigMatrix other3 = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((BigMatrix) null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigVector(9.9);
        Assert.assertEquals(
                new BigVector(84.15), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(9.9, 6);
        Assert.assertEquals(
                new BigVector(93.15, 17.0406), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new BigVector(90.576916, 140.316, 63.8548), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(1, 1);
        Assert.assertEquals(
                new BigVector(10.0, 6.094), sut.times(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(0, 0);
        Assert.assertEquals(
                new BigVector(0.0, 0.0), sut.times(other));
        
        sut = new BigMatrix();
        other = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.times(other));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("24.792298249481175137629568140333008391"), new BigDecimal("9.137993473336032126550645122341844238")), sut.times(other));
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigVector other1 = new BigVector(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigVector other2 = new BigVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new BigMatrix(8.5);
        final BigVector other3 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((BigVector) null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5);
        Assert.assertEquals(
                new BigMatrix(34.0), sut.scale(4));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigMatrix(138.04, 24.36, -81.29744, 180.264), sut.scale(16.24));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new BigMatrix(8.5391, 1.5069, -5.0290276, 11.15106, 4.31978, 9.0414, 1.60736, 7.33358, 8.23772), sut.scale(1.0046));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.scale(7.1));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("101.390319798831221092153446743522126731"), new BigDecimal("148.335312299261046223895576680665843633"), new BigDecimal("49.304111695584753051641930681642978264"), new BigDecimal("-35.846476634813541385534491378288767684")), sut.scale(18.4311));
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("27397871616.16087133534968454506643305790272453"), new BigDecimal("40083430554.137886091045110334138108646310798055"), new BigDecimal("13323044301.119350543858350404964162853621741571"), new BigDecimal("-9686498343.046484487932779880446087514195154182")), sut.scale(new BigDecimal("4980484454.00449087891782817687198879187892871987498279")));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#scale(MatrixInterface)
     */
    @Test
    public void testMatrixScale() throws Exception {
        BigMatrix other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigMatrix(9.9);
        Assert.assertEquals(
                new BigMatrix(84.15), sut.scale(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigMatrix(84.15, 9.0, -2.573084, 54.39), sut.scale(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new BigMatrix(84.15, 9.0, -2.573084, 54.39, 7.74, 27.0, 8.8, 51.83, 41.0), sut.scale(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.scale(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0), sut.scale(other));
        
        sut = new BigMatrix();
        other = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.scale(other));
        
        //big
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("20.783702486616143079547685178307984465"), new BigDecimal("4.008595762865032058081882962025023926"), new BigDecimal("-21.802969502743835484517794316892403533"), new BigDecimal("-17.535250205804777307602903917497197558")), sut.scale(other));
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other1 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.scale(other1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigMatrix other2 = new BigMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.scale(other2));
        
        sut = new BigMatrix(8.5);
        final BigMatrix other3 = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.scale(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.scale((BigMatrix) null));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        BigMatrix other;
        
        //standard
        
        sut = new BigMatrix(8.5);
        other = new BigMatrix(9.9);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.858585858585858585858585858585858586")), sut.dividedBy(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.858585858585858585858585858585858586"), new BigDecimal("0.25"), new BigDecimal("-9.739299610894941634241245136186770428"), new BigDecimal("2.26530612244897959183673469387755102")), sut.dividedBy(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.858585858585858585858585858585858586"), new BigDecimal("0.25"), new BigDecimal("-9.739299610894941634241245136186770428"), new BigDecimal("2.26530612244897959183673469387755102"), new BigDecimal("2.388888888888888888888888888888888889"), new BigDecimal("3"), new BigDecimal("0.290909090909090909090909090909090909"), new BigDecimal("1.02816901408450704225352112676056338"), new BigDecimal("1.64")), sut.dividedBy(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        other = new BigMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.dividedBy(other));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other0 = new BigMatrix(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new BigMatrix();
        other = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.dividedBy(other));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        other = new BigMatrix(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("1.456020728865273800124750920734388921"), new BigDecimal("16.158250125984019258126821898163003137"), new BigDecimal("-0.328207211257100348118432359060412355"), new BigDecimal("-0.215714069638483901402554783412964343")), sut.dividedBy(other));
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix other1 = new BigMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigMatrix other2 = new BigMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new BigMatrix(8.5);
        final BigMatrix other3 = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5);
        Assert.assertEquals(
                new BigMatrix(9.0), sut.round());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigMatrix(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new BigMatrix(9.0, 2.0, -5.0, 11.0, 4.0, 9.0, 2.0, 7.0, 8.0), sut.round());
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.round());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("6"), new BigDecimal("8"), new BigDecimal("3"), new BigDecimal("-2")), sut.round());
    }
    
    /**
     * JUnit test of movePointLeft.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#movePointLeft(int)
     */
    @Test
    public void testMovePointLeft() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5);
        Assert.assertEquals(
                new BigMatrix(0.0085), sut.movePointLeft(3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.movePointLeft(0));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new BigMatrix(85000, 15000, -50060, 111000, 43000, 90000, 16000, 73000, 82000), sut.movePointLeft(-4));
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.movePointLeft(3));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.000000000005501045504545644106545645"), new BigDecimal("0.000000000008048098718972879872817986"), new BigDecimal("0.000000000002675049871987279817897029"), new BigDecimal("-0.000000000001944890789741987259877842")), sut.movePointLeft(12));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5501045504545.644106545645498289419878972871897982"), new BigDecimal("8048098718972.8798728179857241654509840987187948"), new BigDecimal("2675049871987.27981789702897177287184508797"), new BigDecimal("-1944890789741.9872598778418747816878907980484")), sut.movePointLeft(-12));
    }
    
    /**
     * JUnit test of movePointRight.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#movePointRight(int)
     */
    @Test
    public void testMovePointRight() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5);
        Assert.assertEquals(
                new BigMatrix(8500.0), sut.movePointRight(3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.movePointRight(0));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new BigMatrix(0.00085, 0.00015, -0.0005006, 0.00111, 0.00043, 0.0009, 0.00016, 0.00073, 0.00082), sut.movePointRight(-4));
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.movePointRight(3));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5501045504545.644106545645498289419878972871897982"), new BigDecimal("8048098718972.8798728179857241654509840987187948"), new BigDecimal("2675049871987.27981789702897177287184508797"), new BigDecimal("-1944890789741.9872598778418747816878907980484")), sut.movePointRight(12));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.000000000005501045504545644106545645"), new BigDecimal("0.000000000008048098718972879872817986"), new BigDecimal("0.000000000002675049871987279817897029"), new BigDecimal("-0.000000000001944890789741987259877842")), sut.movePointRight(-12));
    }
    
    /**
     * JUnit test of stripTrailingZeros.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#stripTrailingZeros()
     */
    @Test
    public void testStripTrailingZeros() throws Exception {
        //standard
        
        sut = new BigMatrix(8.50);
        Assert.assertEquals(
                new BigMatrix(8.5), sut.stripTrailingZeros());
        
        sut = new BigMatrix(8.500000, 1.500000, -5.0060, 11.1000);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut.stripTrailingZeros());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new BigMatrix(8.500000, 1.500000, -5.0060, 11.1000, 4.30, 9.0000, 1.600, 7.30000000000, 8.20000), sut.stripTrailingZeros());
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.stripTrailingZeros());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.501045504545644106545645498289419878972871897982"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")), sut.stripTrailingZeros());
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //standard
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(new BigDecimal("8.6"), sut.determinant());
        
        sut = new BigMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(new BigDecimal("105.804"), sut.determinant());
        
        sut = new BigMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(new BigDecimal("-293.3228"), sut.determinant());
        
        sut = new BigMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(new BigDecimal("301.7"), sut.determinant());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(new BigDecimal("1240.397376"), sut.determinant());
        
        sut = new BigMatrix();
        Assert.assertEquals(new BigDecimal("0"), sut.determinant());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(new BigDecimal("-32.227998183671779527239009252971604812424750513901537679698605267"), sut.determinant());
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#minor(int, int)
     * @see BigMatrix#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //standard
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(new BigDecimal("0"), sut.minor(0, 0));
        Assert.assertEquals(new BigDecimal("0"), sut.minor(0));
        
        sut = new BigMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(new BigDecimal("9.0"), sut.minor(0, 0));
        Assert.assertEquals(new BigDecimal("-7.101"), sut.minor(1, 0));
        Assert.assertEquals(new BigDecimal("8.6"), sut.minor(1, 1));
        Assert.assertEquals(new BigDecimal("9.0"), sut.minor(0));
        Assert.assertEquals(new BigDecimal("-7.101"), sut.minor(1));
        Assert.assertEquals(new BigDecimal("8.6"), sut.minor(3));
        
        sut = new BigMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(new BigDecimal("-17.62"), sut.minor(0, 0));
        Assert.assertEquals(new BigDecimal("-19.23"), sut.minor(1, 0));
        Assert.assertEquals(new BigDecimal("97.449"), sut.minor(1, 2));
        Assert.assertEquals(new BigDecimal("-18.8"), sut.minor(2, 2));
        Assert.assertEquals(new BigDecimal("-17.62"), sut.minor(0));
        Assert.assertEquals(new BigDecimal("-19.23"), sut.minor(1));
        Assert.assertEquals(new BigDecimal("97.449"), sut.minor(7));
        Assert.assertEquals(new BigDecimal("-18.8"), sut.minor(8));
        
        sut = new BigMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(new BigDecimal("20.62"), sut.minor(0, 0));
        Assert.assertEquals(new BigDecimal("21.4"), sut.minor(1, 0));
        Assert.assertEquals(new BigDecimal("34.5"), sut.minor(1, 2));
        Assert.assertEquals(new BigDecimal("85.5"), sut.minor(2, 2));
        Assert.assertEquals(new BigDecimal("20.62"), sut.minor(0));
        Assert.assertEquals(new BigDecimal("21.4"), sut.minor(1));
        Assert.assertEquals(new BigDecimal("34.5"), sut.minor(7));
        Assert.assertEquals(new BigDecimal("85.5"), sut.minor(8));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"), sut.minor(0, 0));
        Assert.assertEquals(new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), sut.minor(1, 0));
        Assert.assertEquals(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), sut.minor(1, 1));
        Assert.assertEquals(new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"), sut.minor(0));
        Assert.assertEquals(new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), sut.minor(2));
        
        //invalid
        
        sut = new BigMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 0, 0), () ->
                sut.minor(0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.minor(0));
        
        sut = new BigMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 51, 6), () ->
                sut.minor(51, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, -1), () ->
                sut.minor(2, -1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 189), () ->
                sut.minor(189));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.minor(-1));
    }
    
    /**
     * JUnit test of minors.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //standard
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.minors());
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(
                new BigMatrix(0.0), sut.minors());
        
        sut = new BigMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(
                new BigMatrix(9, -7.101, 4, 8.6), sut.minors());
        
        sut = new BigMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(
                new BigMatrix(-17.62, -19.23, 30.8, 39.265, 58.5011, 14.6, 29.802, 97.449, -18.8), sut.minors());
        
        sut = new BigMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(
                new BigMatrix(20.62, 21.4, 26.8, 33.4, 61.0, 20.0, -23.15, 34.5, 85.5), sut.minors());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("-1.944890789741987259877841874781687891"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("5.501045504545644106545645498289419879")), sut.minors());
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#cofactorScalar(int, int)
     * @see BigMatrix#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //standard
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0, 0));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0));
        
        sut = new BigMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0, 0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1, 0));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(1, 1));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(3));
        
        sut = new BigMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0, 0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1, 0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1, 2));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(2, 2));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(7));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(8));
        
        sut = new BigMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0, 0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1, 0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1, 2));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(2, 2));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(7));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(8));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0, 0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1, 0));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(1, 1));
        Assert.assertEquals(new BigDecimal("1"), sut.cofactorScalar(0));
        Assert.assertEquals(new BigDecimal("-1"), sut.cofactorScalar(1));
        
        //invalid
        
        sut = new BigMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 0, 0), () ->
                sut.cofactorScalar(0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.cofactorScalar(0));
        
        sut = new BigMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 51, 6), () ->
                sut.cofactorScalar(51, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, -1), () ->
                sut.cofactorScalar(2, -1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 189), () ->
                sut.cofactorScalar(189));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.cofactorScalar(-1));
    }
    
    /**
     * JUnit test of cofactor.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //standard
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.cofactor());
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(
                new BigMatrix(8.6), sut.cofactor());
        
        sut = new BigMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(
                new BigMatrix(8.6, -4, 7.101, 9), sut.cofactor());
        
        sut = new BigMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(
                new BigMatrix(8.6, -4, -7.101, -9, 2, -3.9, 7.1, -5, 0.94), sut.cofactor());
        
        sut = new BigMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(
                new BigMatrix(15, -11, 8.5, -3, 7.9, -4, -4, 1.6, 1.8), sut.cofactor());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.501045504545644106545645498289419878972871897982"), new BigDecimal("-8.0480987189728798728179857241654509840987187948"), new BigDecimal("-2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")), sut.cofactor());
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //standard
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.transpose());
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(
                new BigMatrix(8.6), sut.transpose());
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0);
        Assert.assertEquals(
                new BigMatrix(8.6, -7.101, 4.0, 9.0), sut.transpose());
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        Assert.assertEquals(
                new BigMatrix(8.6, 9.0, 7.1, 4.0, 2.0, 5.0, -7.101, 3.9, 0.94), sut.transpose());
        
        sut = new BigMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        Assert.assertEquals(
                new BigMatrix(15.0, 3.0, -4.0, 11.0, 7.9, -1.6, 8.5, 4.0, 1.8), sut.transpose());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("-1.944890789741987259877841874781687891")), sut.transpose());
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //standard
        
        sut = new BigMatrix();
        Assert.assertEquals(
                new BigMatrix(), sut.adjoint());
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(
                new BigMatrix(0.0), sut.adjoint());
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0);
        Assert.assertEquals(
                new BigMatrix(9.0, -4.0, 7.101, 8.6), sut.adjoint());
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        Assert.assertEquals(
                new BigMatrix(-17.62, -39.265, 29.802, 19.23, 58.5011, -97.449, 30.8, -14.6, -18.8), sut.adjoint());
        
        sut = new BigMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        Assert.assertEquals(
                new BigMatrix(20.62, -33.4, -23.15, -21.4, 61.0, -34.5, 26.8, -20.0, 85.5), sut.adjoint());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("-1.944890789741987259877841874781687891"), new BigDecimal("-8.048098718972879872817985724165450984"), new BigDecimal("-2.675049871987279817897028971772871845"), new BigDecimal("5.501045504545644106545645498289419879")), sut.adjoint());
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //standard
        
        sut = new BigMatrix(8.6);
        Assert.assertEquals(
                new BigMatrix(0.0), sut.inverse());
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.085062946580469547465124191902007486"), new BigDecimal("-0.037805754035764243317832974178669994"), new BigDecimal("0.067114664851990472949982987410683906"), new BigDecimal("0.081282371176893123133340894484140486")), sut.inverse());
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.060070338889441939051447756533075506"), new BigDecimal("0.133862761435524275644443595929126546"), new BigDecimal("-0.101601375685763261498935643598111023"), new BigDecimal("-0.06555917235209809806806698967826572"), new BigDecimal("-0.199442729988940511954747465931731185"), new BigDecimal("0.33222442987725468323635257811530505"), new BigDecimal("-0.105003770589943911622280981907986696"), new BigDecimal("0.049774514630298087976795530384954732"), new BigDecimal("0.064093210619835894107106573372407464")), sut.inverse());
        
        sut = new BigMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.068346039111700364600596619158104077"), new BigDecimal("-0.11070599933708982432880344713291349"), new BigDecimal("-0.076731852833941000994365263506794829"), new BigDecimal("-0.070931388796818031156778256546237985"), new BigDecimal("0.202187603579714948624461385482267153"), new BigDecimal("-0.114352005303281405369572422936692078"), new BigDecimal("0.088829963539940338084189592310241962"), new BigDecimal("-0.066291017567119655286708650977792509"), new BigDecimal("0.283394100099436526350679482930062976")), sut.inverse());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("0.060347862087424358150208693319737779"), new BigDecimal("0.24972381694654635462964007350400828"), new BigDecimal("0.083003910349684268202173398604422857"), new BigDecimal("-0.170691504734313055431250319063813212")), sut.inverse());
        
        //invalid
        
        sut = new BigMatrix();
        TestUtils.assertException(ArithmeticException.class, "The Big Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
        
        sut = new BigMatrix(3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0);
        TestUtils.assertException(ArithmeticException.class, "The Big Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        BigVector solution;
        
        //standard
        
        sut = new BigMatrix(8.6);
        solution = new BigVector(4.0);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.0")), sut.solveSystem(solution));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0);
        solution = new BigVector(4.0, 19.6);
        Assert.assertEquals(
                new BigVector(new BigDecimal("-0.400740992779100979169029526293901932"), new BigDecimal("1.861593134475067105213413481531889154")), sut.solveSystem(solution));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        solution = new BigVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new BigVector(new BigDecimal("2.683750639227499532937773674600133368"), new BigDecimal("-3.581948058589376618524028817398442944"), new BigDecimal("0.669266760033655754002075529075816813")), sut.solveSystem(solution));
        
        sut = new BigMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        solution = new BigVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new BigVector(new BigDecimal("-2.032575737487570434206165064633742128"), new BigDecimal("3.476291017567119655286708650977792509"), new BigDecimal("-0.44124295657938349353662578720583361")), sut.solveSystem(solution));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        solution = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.35238487890376062605005327998549412"), new BigDecimal("0.228582130420919169332006187412338122")), sut.solveSystem(solution));
        
        //invalid
        
        sut = new BigMatrix();
        final BigVector solution1 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, "The Big Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution1));
        
        sut = new BigMatrix(3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0);
        final BigVector solution2 = new BigVector(4.0, 19.6, 1.774);
        TestUtils.assertException(ArithmeticException.class, "The Big Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution2));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final BigVector solution3 = new BigVector(4.0, 19.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution3), () ->
                sut.solveSystem(solution3));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final BigVector solution4 = new BigVector(4.0, 19.6, 1.774, 0.4951);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution4), () ->
                sut.solveSystem(solution4));
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        BigVector vector;
        
        //standard
        
        sut = new BigMatrix(8.6);
        vector = new BigVector(4.0);
        Assert.assertEquals(
                new BigVector(34.4), sut.transform(vector));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0);
        vector = new BigVector(4.0, 19.6);
        Assert.assertEquals(
                new BigVector(-104.7796, 192.4), sut.transform(vector));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        vector = new BigVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new BigVector(223.3954, 64.07, 49.70356), sut.transform(vector));
        
        sut = new BigMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        vector = new BigVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new BigVector(111.704, 196.0016, 115.5932), sut.transform(vector));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"));
        vector = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("22.116090912347255292030558408321831311"), new BigDecimal("29.438107077109628357595041941416848791")), sut.transform(vector));
        
        //invalid
        
        sut = new BigMatrix();
        final BigVector solution1 = new BigVector();
        TestUtils.assertNoException(() ->
                sut.transform(solution1));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final BigVector solution3 = new BigVector(4.0, 19.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution3), () ->
                sut.transform(solution3));
        
        sut = new BigMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final BigVector solution4 = new BigVector(4.0, 19.6, 1.774, 0.4951);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution4), () ->
                sut.transform(solution4));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        BigMatrix copy;
        
        //standard
        
        sut = new BigMatrix(8.5);
        copy = new BigMatrix(1);
        sut.copy(copy);
        Assert.assertEquals(
                new BigMatrix(8.5), copy);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        copy = new BigMatrix(2);
        sut.copy(copy);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        copy = new BigMatrix(3);
        sut.copy(copy);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), copy);
        
        sut = new BigMatrix();
        copy = new BigMatrix();
        sut.copy(copy);
        Assert.assertEquals(
                new BigMatrix(), copy);
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        copy = new BigMatrix(2);
        sut.copy(copy);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")), copy);
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix copy1 = new BigMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigMatrix copy2 = new BigMatrix(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#copyMeta(BigMatrix)
     */
    @Test
    public void testCopyMeta() throws Exception {
        MathContext mathContext = new MathContext(42, RoundingMode.DOWN);
        
        //standard
        
        BigMatrix component1 = new BigMatrix(8.1, 6.6, 7.0, 2.6);
        Assert.assertEquals(2, component1.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.1"), new BigDecimal("6.6"), new BigDecimal("7.0"), new BigDecimal("2.6")}, component1.getRawComponents());
        
        BigMatrix component2 = new BigMatrix(9.1, 6.3, 1.7, 8.2);
        component1.copyMeta(component2);
        Assert.assertEquals(2, component2.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("9.1"), new BigDecimal("6.3"), new BigDecimal("1.7"), new BigDecimal("8.2")}, component2.getRawComponents());
        
        //big
        
        BigMatrix component3 = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        component3.setMathContext(mathContext);
        Assert.assertEquals(2, component3.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")}, component3.getRawComponents());
        
        BigMatrix component4 = new BigMatrix(9.1, 6.3, 1.7, 0.8);
        component3.copyMeta(component4);
        Assert.assertEquals(2, component4.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("9.1"), new BigDecimal("6.3"), new BigDecimal("1.7"), new BigDecimal("0.8")}, component4.getRawComponents());
        Assert.assertEquals(mathContext.getPrecision(), component4.getMathContext().getPrecision());
        Assert.assertEquals(mathContext.getRoundingMode(), component4.getMathContext().getRoundingMode());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(3);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, 0, -5.006, 11.1, 0, 0, 0, 0), sut);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, 0, 0, -5.006, 11.1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), sut);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(2);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new BigMatrix(8.5), sut);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new BigMatrix(), sut);
        
        sut = new BigMatrix();
        sut.redim(3);
        Assert.assertEquals(
                new BigMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), sut);
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        sut.redim(3);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("0"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")), sut);
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new BigMatrix(), sut);
        
        sut = Mockito.spy(BigMatrix.class);
        sut.setComponents(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
        //standard
        
        BigMatrix subMatrix;
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        
        subMatrix = sut.subMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new BigMatrix(1.0), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 1, 1);
        Assert.assertEquals(
                new BigMatrix(1, 2, 6, 7), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 2, 2);
        Assert.assertEquals(
                new BigMatrix(1, 2, 3, 6, 7, 8, 11, 12, 13), subMatrix);
        
        subMatrix = sut.subMatrix(2, 1, 4, 3);
        Assert.assertEquals(
                new BigMatrix(8, 9, 10, 13, 14, 15, 18, 19, 20), subMatrix);
        
        subMatrix = sut.subMatrix(1, 1, 4, 4);
        Assert.assertEquals(
                new BigMatrix(7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 4, 4);
        Assert.assertEquals(
                new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25), subMatrix);
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        
        subMatrix = sut.subMatrix(0, 0, 1, 1);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.501045504545644106545645498289419879")), subMatrix);
        
        //dimensionality
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        
        subMatrix = sut.subMatrix(0, 0, 1);
        Assert.assertEquals(
                new BigMatrix(1.0), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 2);
        Assert.assertEquals(
                new BigMatrix(1, 2, 6, 7), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 3);
        Assert.assertEquals(
                new BigMatrix(1, 2, 3, 6, 7, 8, 11, 12, 13), subMatrix);
        
        subMatrix = sut.subMatrix(2, 1, 3);
        Assert.assertEquals(
                new BigMatrix(8, 9, 10, 13, 14, 15, 18, 19, 20), subMatrix);
        
        subMatrix = sut.subMatrix(1, 1, 4);
        Assert.assertEquals(
                new BigMatrix(7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 5);
        Assert.assertEquals(
                new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25), subMatrix);
        
        //invalid
        
        sut = new BigMatrix(4);
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 0, 0), () ->
                sut.subMatrix(1, 1, 0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 2, 2, 3, 1), () ->
                sut.subMatrix(2, 2, 3, 1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, -1, -1), () ->
                sut.subMatrix(1, 1, -1, -1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 6, 6), () ->
                sut.subMatrix(1, 1, 6, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, -2, -2, 2, 2), () ->
                sut.subMatrix(-2, -2, 2, 2));
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Number[] {0, 0, 0, 0, 0, 0}), () ->
                sut.subMatrix(0, 0, 2, 1));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Number[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}), () ->
                sut.subMatrix(0, 0, 2, 3));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 0, 0), () ->
                sut.subMatrix(1, 1, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 2, 2, 11, 11), () ->
                sut.subMatrix(2, 2, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 3, 3, 2, 2), () ->
                sut.subMatrix(3, 3, -1));
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dimensionalityToLength(int)
     * @see BigMatrix#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        Assert.assertEquals(9, sut.dimensionalityToLength());
        Assert.assertEquals(9, sut.dimensionalityToLength(3));
        Assert.assertEquals(25, sut.dimensionalityToLength(5));
        Assert.assertEquals(1, sut.dimensionalityToLength(1));
        Assert.assertEquals(0, sut.dimensionalityToLength(0));
        
        //big
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(4, sut.dimensionalityToLength());
        Assert.assertEquals(9, sut.dimensionalityToLength(3));
        Assert.assertEquals(25, sut.dimensionalityToLength(5));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToLength(-1));
        Assert.assertEquals(0, sut.dimensionalityToLength(-3));
    }
    
    /**
     * JUnit test of dimensionalityToWidth.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dimensionalityToWidth(int)
     * @see BigMatrix#dimensionalityToWidth()
     */
    @Test
    public void testDimensionalityToWidth() throws Exception {
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        Assert.assertEquals(3, sut.dimensionalityToWidth());
        Assert.assertEquals(3, sut.dimensionalityToWidth(3));
        Assert.assertEquals(5, sut.dimensionalityToWidth(5));
        Assert.assertEquals(1, sut.dimensionalityToWidth(1));
        Assert.assertEquals(0, sut.dimensionalityToWidth(0));
        
        //big
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(2, sut.dimensionalityToWidth());
        Assert.assertEquals(3, sut.dimensionalityToWidth(3));
        Assert.assertEquals(5, sut.dimensionalityToWidth(5));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToWidth(-1));
        Assert.assertEquals(0, sut.dimensionalityToWidth(-3));
    }
    
    /**
     * JUnit test of dimensionalityToHeight.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dimensionalityToHeight(int)
     * @see BigMatrix#dimensionalityToHeight()
     */
    @Test
    public void testDimensionalityToHeight() throws Exception {
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        Assert.assertEquals(3, sut.dimensionalityToHeight());
        Assert.assertEquals(3, sut.dimensionalityToHeight(3));
        Assert.assertEquals(5, sut.dimensionalityToHeight(5));
        Assert.assertEquals(1, sut.dimensionalityToHeight(1));
        Assert.assertEquals(0, sut.dimensionalityToHeight(0));
        
        //big
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(2, sut.dimensionalityToHeight());
        Assert.assertEquals(3, sut.dimensionalityToHeight(3));
        Assert.assertEquals(5, sut.dimensionalityToHeight(5));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToHeight(-1));
        Assert.assertEquals(0, sut.dimensionalityToHeight(-3));
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#lengthToDimensionality(int)
     * @see BigMatrix#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        Assert.assertEquals(3, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(9));
        Assert.assertEquals(5, sut.lengthToDimensionality(25));
        Assert.assertEquals(1, sut.lengthToDimensionality(1));
        Assert.assertEquals(0, sut.lengthToDimensionality(0));
        
        //big
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(2, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(9));
        Assert.assertEquals(5, sut.lengthToDimensionality(25));
        
        //invalid
        Assert.assertEquals(0, sut.lengthToDimensionality(-1));
        Assert.assertEquals(0, sut.lengthToDimensionality(-3));
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        Assert.assertEquals(3, sut.getDimensionality());
        TestUtils.setField(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(3, sut.getDimensionality());
        
        //big
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(2, sut.getDimensionality());
        TestUtils.setField(sut, "dimensionality", 3);
        Assert.assertEquals(3, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(2, sut.getDimensionality());
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //standard
        
        List<String> print;
        
        sut = new BigMatrix();
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(1, print.size());
        Assert.assertEquals("[]", print.get(0));
        
        sut = new BigMatrix(1.0);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(1, print.size());
        Assert.assertEquals("[  1  ]", print.get(0));
        
        sut = new BigMatrix(1, 2, 3, 4);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(2, print.size());
        Assert.assertEquals("┌  1   2  ┐", print.get(0));
        Assert.assertEquals("└  3   4  ┘", print.get(1));
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  1   2   3  ┐", print.get(0));
        Assert.assertEquals("│  4   5   6  │", print.get(1));
        Assert.assertEquals("└  7   8   9  ┘", print.get(2));
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(4, print.size());
        Assert.assertEquals("┌  1    2    3    4   ┐", print.get(0));
        Assert.assertEquals("│  5    6    7    8   │", print.get(1));
        Assert.assertEquals("│  9    10   11   12  │", print.get(2));
        Assert.assertEquals("└  13   14   15   16  ┘", print.get(3));
        
        sut = new BigMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(5, print.size());
        Assert.assertEquals("┌  1    2    3    4    5   ┐", print.get(0));
        Assert.assertEquals("│  6    7    8    9    10  │", print.get(1));
        Assert.assertEquals("│  11   12   13   14   15  │", print.get(2));
        Assert.assertEquals("│  16   17   18   19   20  │", print.get(3));
        Assert.assertEquals("└  21   22   23   24   25  ┘", print.get(4));
        
        sut = new BigMatrix(8.154119, 3, 1.1033, 8.5, 4.4465740112, 9.915422012, 16.112, 45.2947, 7);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  8.154119   3              1.1033       ┐", print.get(0));
        Assert.assertEquals("│  8.5        4.4465740112   9.915422012  │", print.get(1));
        Assert.assertEquals("└  16.112     45.2947        7            ┘", print.get(2));
        
        //big
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(2, print.size());
        Assert.assertEquals("┌  5.501045504545644106545645498289419879   8.048098718972879872817985724165450984  ┐", print.get(0));
        Assert.assertEquals("└  2.675049871987279817897028971772871845  -1.944890789741987259877841874781687891  ┘", print.get(1));
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        BigMatrix component;
        
        //standard
        component = new BigMatrix(new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getRawComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getComponents());
        
        //big
        component = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")}, component.getRawComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"),}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        BigMatrix component;
        
        //standard
        
        component = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")}, component.getComponents());
        
        component = new BigMatrix(8.5);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5")}, component.getComponents());
        
        component = new BigMatrix();
        Assert.assertArrayEquals(new BigDecimal[] {}, component.getComponents());
        
        component = new BigMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.500000000001"), new BigDecimal("1.499999999996"), new BigDecimal("-5.005999999999"), new BigDecimal("11.099999999999")}, component.getComponents());
        
        component = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")}, component.getComponents());
        
        //big
        
        component = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"),}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        BigMatrix component;
        
        //standard
        component = new BigMatrix(new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getPrimitiveComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getComponents());
        
        //big
        component = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"),}, component.getPrimitiveComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"),}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getRaw(int)
     * @see BigMatrix#getRaw(int, int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(new BigDecimal("8.5"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.getRaw(2));
        Assert.assertEquals(new BigDecimal("11.1"), sut.getRaw(3));
        Assert.assertEquals(new BigDecimal("1.5"), sut.getRaw(1, 0));
        Assert.assertEquals(new BigDecimal("11.1"), sut.getRaw(1, 1));
        
        sut = new BigMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertEquals(new BigDecimal("8.500000000001"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("1.499999999996"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("-5.005999999999"), sut.getRaw(2));
        Assert.assertEquals(new BigDecimal("11.099999999999"), sut.getRaw(3));
        Assert.assertEquals(new BigDecimal("1.499999999996"), sut.getRaw(1, 0));
        Assert.assertEquals(new BigDecimal("11.099999999999"), sut.getRaw(1, 1));
        
        sut = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(new BigDecimal("8.5"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.getRaw(2));
        Assert.assertEquals(new BigDecimal("11.1"), sut.getRaw(3));
        Assert.assertEquals(new BigDecimal("1.5"), sut.getRaw(1, 0));
        Assert.assertEquals(new BigDecimal("11.1"), sut.getRaw(1, 1));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("8.0480987189728798728179857241654509840987187948"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("2.67504987198727981789702897177287184508797"), sut.getRaw(2));
        Assert.assertEquals(new BigDecimal("-1.9448907897419872598778418747816878907980484"), sut.getRaw(3));
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.getRaw(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 3), () ->
                sut.getRaw(1, 3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, 2), () ->
                sut.getRaw(2, 2));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.getRaw(8, 9));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.getRaw(-1, -2));
        
        sut = new BigMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#get(int)
     * @see BigMatrix#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(new BigDecimal("8.5"), sut.get(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.get(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.get(2));
        Assert.assertEquals(new BigDecimal("11.1"), sut.get(3));
        Assert.assertEquals(new BigDecimal("1.5"), sut.get(1, 0));
        Assert.assertEquals(new BigDecimal("11.1"), sut.get(1, 1));
        
        sut = new BigMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertEquals(new BigDecimal("8.500000000001"), sut.get(0));
        Assert.assertEquals(new BigDecimal("1.499999999996"), sut.get(1));
        Assert.assertEquals(new BigDecimal("-5.005999999999"), sut.get(2));
        Assert.assertEquals(new BigDecimal("11.099999999999"), sut.get(3));
        Assert.assertEquals(new BigDecimal("1.499999999996"), sut.get(1, 0));
        Assert.assertEquals(new BigDecimal("11.099999999999"), sut.get(1, 1));
        
        sut = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(new BigDecimal("8.5"), sut.get(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.get(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.get(2));
        Assert.assertEquals(new BigDecimal("11.1"), sut.get(3));
        Assert.assertEquals(new BigDecimal("1.5"), sut.get(1, 0));
        Assert.assertEquals(new BigDecimal("11.1"), sut.get(1, 1));
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(new BigDecimal("5.501045504545644106545645498289419879"), sut.get(0));
        Assert.assertEquals(new BigDecimal("8.048098718972879872817985724165450984"), sut.get(1));
        Assert.assertEquals(new BigDecimal("2.675049871987279817897028971772871845"), sut.get(2));
        Assert.assertEquals(new BigDecimal("-1.944890789741987259877841874781687891"), sut.get(3));
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.get(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 3), () ->
                sut.get(1, 3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, 2), () ->
                sut.get(2, 2));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.get(8, 9));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.get(-1, -2));
        
        sut = new BigMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getMathContext()
     */
    @Test
    public void testGetMathContext() throws Exception {
        BigMatrix component = new BigMatrix(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        TestUtils.setField(component.getHandler(), "mathContext", newMathContext);
        Assert.assertEquals(newMathContext, component.getMathContext());
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new BigMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new BigMatrix(5.501);
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(3, sut.getDimensionality());
        
        sut = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new BigMatrix();
        Assert.assertEquals(0, sut.getDimensionality());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(2, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //standard
        
        sut = new BigMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(2, sut.getHeight());
        
        sut = new BigMatrix(5.501);
        Assert.assertEquals(1, sut.getHeight());
        
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(3, sut.getHeight());
        
        sut = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(2, sut.getHeight());
        
        sut = new BigMatrix();
        Assert.assertEquals(0, sut.getHeight());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(2, sut.getHeight());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //standard
        
        sut = new BigMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(2, sut.getWidth());
        
        sut = new BigMatrix(5.501);
        Assert.assertEquals(1, sut.getWidth());
        
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(3, sut.getWidth());
        
        sut = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(2, sut.getWidth());
        
        sut = new BigMatrix();
        Assert.assertEquals(0, sut.getWidth());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(2, sut.getWidth());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new BigMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new BigMatrix(5.501);
        Assert.assertEquals(1, sut.getLength());
        
        sut = new BigMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(9, sut.getLength());
        
        sut = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new BigMatrix();
        Assert.assertEquals(0, sut.getLength());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertEquals(4, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new BigMatrix();
        Assert.assertEquals(BigMatrix.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new BigMatrix();
        Assert.assertEquals(BigDecimal.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new BigMatrix();
        Assert.assertTrue(sut.getHandler() instanceof BigComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new BigMatrix();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new BigMatrix();
        Assert.assertEquals("Big Matrix", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new BigMatrix();
        Assert.assertEquals("Big Matrices", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new BigMatrix();
        Assert.assertEquals(new BigDecimal("0.000000000000000000000000000000000001"), sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new BigMatrix();
        Assert.assertTrue(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        BigMatrix component;
        BigDecimal[] newComponents;
        
        //standard
        
        component = new BigMatrix(5.501, 2.67, -1.944, 8.5);
        newComponents = new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8"), new BigDecimal("8.9")};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8"), new BigDecimal("8.9")}, component.getRawComponents());
        Assert.assertEquals(2, component.getDimensionality());
        
        component = new BigMatrix(5.501, 2.67, -1.944, 8.5);
        newComponents = new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8.0"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1"), new BigDecimal("-9.3"), new BigDecimal("1.61"), new BigDecimal("8.0"), new BigDecimal("-0.77"), new BigDecimal("5.06"), new BigDecimal("4.4")}, component.getRawComponents());
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new BigMatrix(5.501, 2.67, -1.944, 8.5);
        newComponents = new BigDecimal[] {};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {}, component.getRawComponents());
        Assert.assertEquals(0, component.getDimensionality());
        
        //big
        
        component = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        newComponents = new BigDecimal[] {new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842")};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842")}, component.getRawComponents());
        Assert.assertEquals(2, component.getDimensionality());
        
        //invalid
        
        final BigMatrix component2 = new BigMatrix(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component2.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#set(int, Number)
     * @see BigMatrix#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("5.77"));
        sut.set(1, new BigDecimal("3.0"));
        sut.set(2, new BigDecimal("0.997"));
        sut.set(3, new BigDecimal("4.5"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.77"), new BigDecimal("3.0"), new BigDecimal("0.997"), new BigDecimal("4.5")}, sut.getRawComponents());
        
        sut = new BigMatrix(8.5);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("5.77"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.77")}, sut.getRawComponents());
        
        sut = new BigMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.500000000001"), new BigDecimal("1.499999999996"), new BigDecimal("-5.005999999999"), new BigDecimal("11.099999999999")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("5.769999999996"));
        sut.set(1, new BigDecimal("3.000000000001"));
        sut.set(2, new BigDecimal("8.300000000001"));
        sut.set(3, new BigDecimal("5.2269999999996"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.769999999996"), new BigDecimal("3.000000000001"), new BigDecimal("8.300000000001"), new BigDecimal("5.2269999999996")}, sut.getRawComponents());
        
        sut = new BigMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("6.5000000000000000000000001"));
        sut.set(1, new BigDecimal("-1.49999999999999999999996"));
        sut.set(2, new BigDecimal("3.0059999999999999999999"));
        sut.set(3, new BigDecimal("8.1059999999999999999999"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("6.5000000000000000000000001"), new BigDecimal("-1.49999999999999999999996"), new BigDecimal("3.0059999999999999999999"), new BigDecimal("8.1059999999999999999999")}, sut.getRawComponents());
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")}, sut.getRawComponents());
        sut.set(0, 0, new BigDecimal("5.77"));
        sut.set(1, 0, new BigDecimal("3.0"));
        sut.set(0, 1, new BigDecimal("0.997"));
        sut.set(1, 1, new BigDecimal("4.5"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.77"), new BigDecimal("3.0"), new BigDecimal("0.997"), new BigDecimal("4.5")}, sut.getRawComponents());
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("3.77813680498408489408942080484984840898488404"));
        sut.set(1, new BigDecimal("0.498079845046510549248438249845405049804"));
        sut.set(2, new BigDecimal("-8.15049084918425446549880884184284198442084984"));
        sut.set(3, new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842")}, sut.getRawComponents());
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.set(4, new BigDecimal("5.5")));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, new BigDecimal("5.5")));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, new BigDecimal("5.5")));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 3), () ->
                sut.set(1, 3, new BigDecimal("5.0")));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, 2), () ->
                sut.set(2, 2, new BigDecimal("5.0")));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.set(8, 9, new BigDecimal("5.0")));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.set(-1, -2, new BigDecimal("5.0")));
        
        sut = new BigMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, new BigDecimal("5.5")));
    }
    
    /**
     * JUnit test of setMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#setMathContext(MathContext)
     */
    @Test
    public void testSetMathContext() throws Exception {
        BigMatrix component = new BigMatrix(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        component.setMathContext(newMathContext);
        MathContext mathContext = (MathContext) TestUtils.getField(component.getHandler(), "mathContext");
        Assert.assertEquals(newMathContext, mathContext);
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        BigMatrix copy;
        
        //standard
        
        sut = new BigMatrix(8.5);
        copy = new BigMatrix(1);
        BigMatrix.copy(sut, copy);
        Assert.assertEquals(
                new BigMatrix(8.5), copy);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        copy = new BigMatrix(2);
        BigMatrix.copy(sut, copy);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        copy = new BigMatrix(3);
        BigMatrix.copy(sut, copy);
        Assert.assertEquals(
                new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), copy);
        
        sut = new BigMatrix();
        copy = new BigMatrix();
        BigMatrix.copy(sut, copy);
        Assert.assertEquals(
                new BigMatrix(), copy);
        
        //big
        
        sut = new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"));
        copy = new BigMatrix(2);
        BigMatrix.copy(sut, copy);
        Assert.assertEquals(
                new BigMatrix(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484")), copy);
        
        //invalid
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix copy1 = new BigMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                BigMatrix.copy(sut, copy1));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final BigMatrix copy2 = new BigMatrix(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                BigMatrix.copy(sut, copy2));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final Vector copy3 = new Vector(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                BigMatrix.copy(sut, copy3));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        final Matrix copy4 = new Matrix(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                BigMatrix.copy(sut, copy4));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                BigMatrix.copy(sut, null));
        
        sut = new BigMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                BigMatrix.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.createInstance(0));
        Assert.assertEquals(
                new BigMatrix(0.0), BigMatrix.createInstance(1));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0), BigMatrix.createInstance(2));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), BigMatrix.createInstance(3));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), BigMatrix.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.identity(0));
        Assert.assertEquals(
                new BigMatrix(1.0), BigMatrix.identity(1));
        Assert.assertEquals(
                new BigMatrix(1.0, 0.0, 0.0, 1.0), BigMatrix.identity(2));
        Assert.assertEquals(
                new BigMatrix(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), BigMatrix.identity(3));
        Assert.assertEquals(
                new BigMatrix(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), BigMatrix.identity(4));
        
        //invalid
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.origin(0));
        Assert.assertEquals(
                new BigMatrix(0.0), BigMatrix.origin(1));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0), BigMatrix.origin(2));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), BigMatrix.origin(3));
        Assert.assertEquals(
                new BigMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), BigMatrix.origin(4));
        
        //invalid
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.origin(-1));
    }
    
    /**
     * JUnit test of signChart.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#signChart(int)
     */
    @Test
    public void testSignChart() throws Exception {
        //standard
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.signChart(0));
        Assert.assertEquals(
                new BigMatrix(1.0), BigMatrix.signChart(1));
        Assert.assertEquals(
                new BigMatrix(1.0, -1.0, -1.0, 1.0), BigMatrix.signChart(2));
        Assert.assertEquals(
                new BigMatrix(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), BigMatrix.signChart(3));
        Assert.assertEquals(
                new BigMatrix(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), BigMatrix.signChart(4));
        
        //invalid
        Assert.assertEquals(
                new BigMatrix(), BigMatrix.signChart(-1));
    }
    
}
