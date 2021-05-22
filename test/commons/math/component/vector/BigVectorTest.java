/*
 * File:    BigVectorTest.java
 * Package: commons.math.component.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.vector;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import commons.math.MathUtility;
import commons.math.component.BaseComponent;
import commons.math.component.ComponentInterface;
import commons.math.component.handler.error.ComponentErrorHandlerInterface;
import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import commons.math.component.handler.math.BigComponentMathHandler;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.matrix.IntMatrix;
import commons.math.component.matrix.Matrix;
import commons.math.component.matrix.RawMatrix;
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
 * JUnit test of BigVector.
 *
 * @see BigVector
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigVector.class})
public class BigVectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigVectorTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private BigVector sut;
    
    
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
     * @see BigVector#BigVector(BigDecimal...)
     * @see BigVector#BigVector(double...)
     * @see BigVector#BigVector(String...)
     * @see BigVector#BigVector(List)
     * @see BigVector#BigVector(BigVector)
     * @see BigVector#BigVector(BigVector)
     * @see BigVector#BigVector(BigVector, BigDecimal...)
     * @see BigVector#BigVector(BigVector, double...)
     * @see BigVector#BigVector(BigVector, String...)
     * @see BigVector#BigVector(BigVector, BigDecimal...)
     * @see BigVector#BigVector(BigVector, double...)
     * @see BigVector#BigVector(BigVector, String...)
     * @see BigVector#BigVector(int)
     * @see BigVector#BigVector()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        BigVector vector = new BigVector(new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1")}, vector.getRawComponents());
        Assert.assertEquals(3, vector.getDimensionality());
        
        //double components
        BigVector vector2 = new BigVector(0.884, 2, 1.1);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1")}, vector2.getRawComponents());
        Assert.assertEquals(3, vector2.getDimensionality());
        
        //string components
        BigVector vector3 = new BigVector("0.884", "2", "1.1");
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1")}, vector3.getRawComponents());
        Assert.assertEquals(3, vector3.getDimensionality());
        
        //list of components
        List<BigDecimal> values = Arrays.asList(new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("8.4"), new BigDecimal("9.0"));
        BigVector vector4 = new BigVector(values);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("8.4"), new BigDecimal("9.0")}, vector4.getRawComponents());
        Assert.assertEquals(5, vector4.getDimensionality());
        
        //big vector
        BigVector vector5 = new BigVector(new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1")}, vector5.getRawComponents());
        Assert.assertEquals(3, vector5.getDimensionality());
        
        //big vector and component
        BigVector vector6 = new BigVector(new BigVector(new BigDecimal("0.884"), new BigDecimal("2")), new BigDecimal("1.1"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1")}, vector6.getRawComponents());
        Assert.assertEquals(3, vector6.getDimensionality());
        
        //big vector and components
        BigVector vector7 = new BigVector(new BigVector(new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1")), new BigDecimal("8.4"), new BigDecimal("9"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1"), new BigDecimal("8.4"), new BigDecimal("9")}, vector7.getRawComponents());
        Assert.assertEquals(5, vector7.getDimensionality());
        
        //big vector and double component
        BigVector vector8 = new BigVector(new BigVector(new BigDecimal("0.884"), new BigDecimal("2")), 1.1);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1")}, vector8.getRawComponents());
        Assert.assertEquals(3, vector8.getDimensionality());
        
        //big vector and string component
        BigVector vector9 = new BigVector(new BigVector(new BigDecimal("0.884"), new BigDecimal("2")), "1.1");
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2"), new BigDecimal("1.1")}, vector9.getRawComponents());
        Assert.assertEquals(3, vector9.getDimensionality());
        
        //vector
        BigVector vector10 = new BigVector(new BigVector(0.884, 2, 1.1));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1")}, vector10.getRawComponents());
        Assert.assertEquals(3, vector10.getDimensionality());
        
        //vector and component
        BigVector vector11 = new BigVector(new BigVector(0.884, 2), new BigDecimal("1.1"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1")}, vector11.getRawComponents());
        Assert.assertEquals(3, vector11.getDimensionality());
        
        //vector and components
        BigVector vector12 = new BigVector(new BigVector(0.884, 2, 1.1), new BigDecimal("8.4"), new BigDecimal("9"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1"), new BigDecimal("8.4"), new BigDecimal("9")}, vector12.getRawComponents());
        Assert.assertEquals(5, vector12.getDimensionality());
        
        //vector and double component
        BigVector vector13 = new BigVector(new BigVector(0.884, 2), 1.1);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1")}, vector13.getRawComponents());
        Assert.assertEquals(3, vector13.getDimensionality());
        
        //vector and string component
        BigVector vector14 = new BigVector(new BigVector(0.884, 2), "1.1");
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.884"), new BigDecimal("2.0"), new BigDecimal("1.1")}, vector14.getRawComponents());
        Assert.assertEquals(3, vector14.getDimensionality());
        
        //dimensionality
        BigVector vectorDimensionality = new BigVector(6);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")}, vectorDimensionality.getRawComponents());
        Assert.assertEquals(6, vectorDimensionality.getDimensionality());
        
        //empty
        BigVector vectorDefault = new BigVector();
        Assert.assertArrayEquals(new BigDecimal[] {}, vectorDefault.getRawComponents());
        Assert.assertEquals(0, vectorDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(vector, vector2);
        Assert.assertEquals(vector2, vector3);
        Assert.assertEquals(vector3, vector5);
        Assert.assertEquals(vector5, vector6);
        Assert.assertEquals(vector6, vector8);
        Assert.assertEquals(vector8, vector9);
        Assert.assertEquals(vector9, vector10);
        Assert.assertEquals(vector10, vector11);
        Assert.assertEquals(vector11, vector13);
        Assert.assertEquals(vector13, vector14);
        Assert.assertEquals(vector4, vector7);
        Assert.assertEquals(vector7, vector12);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new BigVector(Arrays.asList(0.884, null, 1.1)));
        TestUtils.assertException(NullPointerException.class, () ->
                new BigVector((BigVector) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new BigVector((BigVector) null, 8.4, 9));
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#vectorString()
     */
    @Test
    public void testBigVectorString() throws Exception {
        //standard
        
        sut = new BigVector(1.0);
        Assert.assertEquals("<1>", sut.vectorString());
        
        sut = new BigVector(1, 2);
        Assert.assertEquals("<1, 2>", sut.vectorString());
        
        sut = new BigVector(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("<1, 2, 3, 4, 5, 6, 7, 8, 9>", sut.vectorString());
        
        sut = new BigVector(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("<8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455>", sut.vectorString());
        
        //big
        
        sut = new BigVector(new BigDecimal("1.0405650498540941874980498641878019451498248105484"));
        Assert.assertEquals("<1.040565049854094187498049864187801945>", sut.vectorString());
        
        sut = new BigVector(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"));
        Assert.assertEquals("<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705>", sut.vectorString());
        
        sut = new BigVector(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"), new BigDecimal("3.049879819842684284189481749807987819878971978971"), new BigDecimal("4.45642136569789098404109190470454984849845156"), new BigDecimal("5.1312156941890181498428720078407484450904908"), new BigDecimal("6.674650596084657409841510212131065151984"), new BigDecimal("7.6098789408415640894804540564321065154980747848"), new BigDecimal("8.4549807546543213215818048482848429842854988"), new BigDecimal("9.9870808798056051210515913121904848490879879845046"));
        Assert.assertEquals("<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705, 3.04987981984268428418948174980798782, 4.456421365697890984041091904704549848, 5.131215694189018149842872007840748445, 6.674650596084657409841510212131065152, 7.609878940841564089480454056432106515, 8.454980754654321321581804848284842984, 9.987080879805605121051591312190484849>", sut.vectorString());
        
        sut = new BigVector(new BigDecimal("8.150000000000000000000000000000000000000001"), new BigDecimal("77.16540000000000000000000000000000000000000001"), new BigDecimal("3.0000000000000000000000000000000000000001"), new BigDecimal("3.660000000000000000000000000000000000000001"), new BigDecimal("7.150000000000000000000000000000000000000001"), new BigDecimal("890.10000000000000000000000000000000000000001"), new BigDecimal("11.0000000000000000000000000000000000000001"), new BigDecimal("7.98880000000000000000000000000000000000000001"), new BigDecimal("0.794550000000000000000000000000000000000000001"));
        Assert.assertEquals("<8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455>", sut.vectorString());
        
        //empty
        
        sut = new BigVector();
        Assert.assertEquals("<>", sut.vectorString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new BigVector(8.5, -1.944, 2.67, 8.13, 5.501);
        Assert.assertEquals("<8.5, -1.944, 2.67, 8.13, 5.501>", sut.toString());
        
        sut = new BigVector(0, 8.5, -1.944, 2.67, 8.13, 5.501, 0);
        Assert.assertEquals("<0, 8.5, -1.944, 2.67, 8.13, 5.501, 0>", sut.toString());
        
        sut = new BigVector(8.5);
        Assert.assertEquals("<8.5>", sut.toString());
        
        //big
        
        sut = new BigVector(new BigDecimal("1.0405650498540941874980498641878019451498248105484"));
        Assert.assertEquals("<1.040565049854094187498049864187801945>", sut.toString());
        
        sut = new BigVector(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"));
        Assert.assertEquals("<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705>", sut.toString());
        
        sut = new BigVector(new BigDecimal("1.0405650498540941874980498641878019451498248105484"), new BigDecimal("2.97089846519804503050310553018079870546"), new BigDecimal("3.049879819842684284189481749807987819878971978971"), new BigDecimal("4.45642136569789098404109190470454984849845156"), new BigDecimal("5.1312156941890181498428720078407484450904908"), new BigDecimal("6.674650596084657409841510212131065151984"), new BigDecimal("7.6098789408415640894804540564321065154980747848"), new BigDecimal("8.4549807546543213215818048482848429842854988"), new BigDecimal("9.9870808798056051210515913121904848490879879845046"));
        Assert.assertEquals("<1.040565049854094187498049864187801945, 2.970898465198045030503105530180798705, 3.04987981984268428418948174980798782, 4.456421365697890984041091904704549848, 5.131215694189018149842872007840748445, 6.674650596084657409841510212131065152, 7.609878940841564089480454056432106515, 8.454980754654321321581804848284842984, 9.987080879805605121051591312190484849>", sut.toString());
        
        sut = new BigVector(new BigDecimal("8.150000000000000000000000000000000000000001"), new BigDecimal("77.16540000000000000000000000000000000000000001"), new BigDecimal("3.0000000000000000000000000000000000000001"), new BigDecimal("3.660000000000000000000000000000000000000001"), new BigDecimal("7.150000000000000000000000000000000000000001"), new BigDecimal("890.10000000000000000000000000000000000000001"), new BigDecimal("11.0000000000000000000000000000000000000001"), new BigDecimal("7.98880000000000000000000000000000000000000001"), new BigDecimal("0.794550000000000000000000000000000000000000001"));
        Assert.assertEquals("<8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455>", sut.toString());
        
        //empty
        
        sut = new BigVector();
        Assert.assertEquals("<>", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new BigVector(0, 8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 8, 5.501, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 2, 8, 5);
        Assert.assertFalse(sut.equals(other));
        
        sut = new BigVector(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new Matrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        sut = new BigVector(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new RawMatrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new BigVector().equals(new BigVector()));
        
        //big
        
        sut = new BigVector(new BigDecimal("8.5"), new BigDecimal("-1.944"), new BigDecimal("2.67"), new BigDecimal("8.0"), new BigDecimal("5.501"));
        
        other = new BigVector(new BigDecimal("8.5"), new BigDecimal("-1.944"), new BigDecimal("2.67"), new BigDecimal("8.0"), new BigDecimal("5.501"));
        Assert.assertTrue(sut.equals(other));
        
        other = new BigVector(new BigDecimal("8.500000000000000000000000000000000001"), new BigDecimal("-1.944000000000000000000000000000000001"), new BigDecimal("2.670000000000000000000000000000000001"), new BigDecimal("8.000000000000000000000000000000000001"), new BigDecimal("5.501000000000000000000000000000000001"));
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(new BigDecimal("8.5000000000000000000000000000000000001"), new BigDecimal("-1.9440000000000000000000000000000000001"), new BigDecimal("2.6700000000000000000000000000000000001"), new BigDecimal("8.0000000000000000000000000000000000001"), new BigDecimal("5.5010000000000000000000000000000000001"));
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
     * @see BigVector#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new BigVector().dimensionalityEqual(new BigVector()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigVector(5.501);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.lengthEqual(other));
        
        sut = new BigVector(8.5, -1.944, 2.67, 8.13);
        other = new Matrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        Assert.assertTrue(new BigVector().lengthEqual(new BigVector()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new BigVector().componentTypeEqual(new BigVector()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#cloned()
     */
    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new BigVector(8.1, 6.6, 5, 1.09);
        BigVector clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), clone.hashCode());
        
        //big
        sut = new BigVector(new BigDecimal("8.1798906456468046140621074678418708"), new BigDecimal("6.68987846515405151504957982720704"), new BigDecimal("5.08917984982428719878814854359782987184"), new BigDecimal("1.097084949814687678164861560484198265140"));
        clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), clone.hashCode());
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new BigVector(8.1, 6.6, 5, 1.09);
        BigVector emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(BigVector.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), emptyCopy.hashCode());
        
        //big
        sut = new BigVector(new BigDecimal("8.1798906456468046140621074678418708"), new BigDecimal("6.68987846515405151504957982720704"), new BigDecimal("5.08917984982428719878814854359782987184"), new BigDecimal("1.097084949814687678164861560484198265140"));
        emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(BigVector.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), emptyCopy.hashCode());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new BigVector();
        
        //standard
        Assert.assertEquals(
                new BigVector(), sut.createNewInstance(0));
        Assert.assertEquals(
                new BigVector(0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new BigVector(0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new BigVector(), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        BigVector reversed;
        
        //standard
        
        sut = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501"), new BigDecimal("8.0"), new BigDecimal("2.67"), new BigDecimal("-1.944"), new BigDecimal("8.5")}, reversed.getRawComponents());
        
        sut = new BigVector(5.501, 8, 2.67, -1.944, 8.5);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("-1.944"), new BigDecimal("2.67"), new BigDecimal("8.0"), new BigDecimal("5.501")}, reversed.getRawComponents());
        
        sut = new BigVector(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("1.0"), new BigDecimal("0.0"), new BigDecimal("1.0"), new BigDecimal("0.0")}, reversed.getRawComponents());
        
        sut = new BigVector(5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501")}, reversed.getRawComponents());
        
        sut = new BigVector();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {}, reversed.getRawComponents());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.598779040509846549575492484052654"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("5.50104550454564410654564549828941987897287189798208908")}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigVector(8.5);
        other = new BigVector(9.9);
        Assert.assertEquals(new BigDecimal("1.4"), sut.distance(other));
        
        sut = new BigVector(8.5, 1.5);
        other = new BigVector(9.9, 6);
        Assert.assertEquals(new BigDecimal("4.7127486671792715069657524054412117227780635486379903630213092315"), sut.distance(other));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        other = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(new BigDecimal("7.2581264800222378048170569686763655348893768242478283722433956171"), sut.distance(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(new BigDecimal("9.5457006028892399994010021760928316222872261431602090253993433634"), sut.distance(other));
        
        sut = new BigVector();
        other = new BigVector();
        Assert.assertEquals(new BigDecimal("0"), sut.distance(other));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertEquals(new BigDecimal("17.5916621843528494656365835051863401844401450749631292147385190639"), sut.distance(other));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector other1 = new BigVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector other2 = new BigVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new BigVector(8.5, 1.5);
        final BigVector other3 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigVector(8.5);
        other = new BigVector(9.9);
        Assert.assertEquals(
                new BigVector(9.2), sut.midpoint(other));
        
        sut = new BigVector(8.5, 1.5);
        other = new BigVector(9.9, 6);
        Assert.assertEquals(
                new BigVector(9.2, 3.75), sut.midpoint(other));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        other = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new BigVector(9.2, 3.75, -2.246), sut.midpoint(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigVector(9.2, 3.75, -2.246, 8.0), sut.midpoint(other));
        
        sut = new BigVector();
        other = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.midpoint(other));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("4.639591154764864500317533151569634144"), new BigDecimal("4.273089282009695211033211987005428017"), new BigDecimal("-2.73772048859848732380088993503498507"), new BigDecimal("3.53558413004928104732340634473625508"), new BigDecimal("10.342383477669635979536828951275569127")), sut.midpoint(other));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector other1 = new BigVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector other2 = new BigVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new BigVector(8.5, 1.5);
        final BigVector other3 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#average(List)
     * @see BigVector#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        BigVector other1;
        BigVector other2;
        BigVector other3;
        BigVector other4;
        
        //standard
        
        sut = new BigVector(8.5);
        other1 = new BigVector(9.9);
        other2 = new BigVector(1.8);
        other3 = new BigVector(11.7);
        Assert.assertEquals(
                new BigVector(7.975), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigVector(7.975), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new BigVector(8.5, 1.5);
        other1 = new BigVector(9.9, 6);
        other2 = new BigVector(1.8, 4.77);
        Assert.assertEquals(
                new BigVector(new BigDecimal("6.733333333333333333333333333333333333"), new BigDecimal("4.09")), sut.average(other1, other2));
        Assert.assertEquals(
                new BigVector(new BigDecimal("6.733333333333333333333333333333333333"), new BigDecimal("4.09")), sut.average(Arrays.asList(other1, other2)));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        other1 = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new BigVector(9.2, 3.75, -2.246), sut.average(other1));
        Assert.assertEquals(
                new BigVector(9.2, 3.75, -2.246), sut.average(Collections.singletonList(other1)));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other1 = new BigVector(9.9, 6, 0.514, 4.9);
        other2 = new BigVector(1.8, 4.77, 0.514, 2.895);
        other3 = new BigVector(11.7, 0.447, 7.16, 8);
        Assert.assertEquals(
                new BigVector(7.975, 3.17925, 0.7955, 6.72375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigVector(7.975, 3.17925, 0.7955, 6.72375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new BigVector();
        other1 = new BigVector();
        other2 = new BigVector();
        other3 = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigVector(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other1 = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        other2 = new BigVector(new BigDecimal("-4.9808941892654987824898978109784289484084"), new BigDecimal("3.140650987988972817842549879872884659872871889789"), new BigDecimal("7.1059790875459424984087804084565492845408"), new BigDecimal("4.1449080800840084549048489428425982798272"), new BigDecimal("15.0580980564054254854214987104848408486408904540654205419"));
        other3 = new BigVector(new BigDecimal("9.0001650857987892749879821782987804519541981408"), new BigDecimal("1.1590498177787828768798789798278778982"), new BigDecimal("2.101509540840845465045425629817897850484184098"), new BigDecimal("-0.045640928948489489789782798729872817985982484284"), new BigDecimal("-17.06498048942824524494289842841084204840984549"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("3.324613301515754873283287667614904948"), new BigDecimal("3.211469842446786529197213208427904648"), new BigDecimal("0.933011912797453328963106542051119249"), new BigDecimal("2.792608852808520264940469708396308905"), new BigDecimal("4.669471130579113049888064546156284263")), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new BigVector(new BigDecimal("3.324613301515754873283287667614904948"), new BigDecimal("3.211469842446786529197213208427904648"), new BigDecimal("0.933011912797453328963106542051119249"), new BigDecimal("2.792608852808520264940469708396308905"), new BigDecimal("4.669471130579113049888064546156284263")), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector otherF11 = new BigVector(9.9, 6, 0.514, 4.9);
        final BigVector otherF12 = new BigVector(1.8, 4.77, 0.514, 2.895);
        final BigVector otherF13 = new BigVector(11.7, 0.447, 7.16);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector otherF21 = new BigVector(9.9, 6, 0.514, 4.9);
        final BigVector otherF22 = new BigVector(1.8, 4.77, 0.514, 2.895, 9.11);
        final BigVector otherF23 = new BigVector(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector otherF31 = new BigVector(9.9, 6, 0.514, 4.9);
        final BigVector otherF33 = new BigVector(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new BigVector(8.5);
        Assert.assertEquals(new BigDecimal("8.5"), sut.sum());
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(new BigDecimal("10"), sut.sum());
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(new BigDecimal("4.994"), sut.sum());
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(new BigDecimal("16.094"), sut.sum());
        
        sut = new BigVector();
        Assert.assertEquals(new BigDecimal("0"), sut.sum());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("22.87808234627366308695831080349870881736151229278208908"), sut.sum());
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new BigVector(8.5);
        Assert.assertEquals(new BigDecimal("72.25"), sut.squareSum());
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(new BigDecimal("74.5"), sut.squareSum());
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(new BigDecimal("99.560036"), sut.squareSum());
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(new BigDecimal("222.770036"), sut.squareSum());
        
        sut = new BigVector();
        Assert.assertEquals(new BigDecimal("0"), sut.squareSum());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("179.9108876225685604594207018394567810880130104123361282271622593424"), sut.squareSum());
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigVector(8.5);
        other = new BigVector(9.9);
        Assert.assertEquals(
                new BigVector(18.4), sut.plus(other));
        
        sut = new BigVector(8.5, 1.5);
        other = new BigVector(9.9, 6);
        Assert.assertEquals(
                new BigVector(18.4, 7.5), sut.plus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        other = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new BigVector(18.4, 7.5, -4.492), sut.plus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigVector(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(1, 1, 1, 1);
        Assert.assertEquals(
                new BigVector(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(0, 0, 0, 0);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new BigVector();
        other = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.plus(other));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("9.279182309529729000635066303139268288"), new BigDecimal("8.546178564019390422066423974010856034"), new BigDecimal("-5.475440977196974647601779870069970139"), new BigDecimal("7.071168260098562094646812689472510159"), new BigDecimal("20.684766955339271959073657902551138253")), sut.plus(other));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector other1 = new BigVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector other2 = new BigVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new BigVector(8.5, 1.5);
        final BigVector other3 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigVector(8.5);
        other = new BigVector(9.9);
        Assert.assertEquals(
                new BigVector(-1.4), sut.minus(other));
        
        sut = new BigVector(8.5, 1.5);
        other = new BigVector(9.9, 6);
        Assert.assertEquals(
                new BigVector(-1.4, -4.5), sut.minus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        other = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new BigVector(-1.4, -4.5, -5.52), sut.minus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigVector(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(1, 1, 1, 1);
        Assert.assertEquals(
                new BigVector(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(0, 0, 0, 0);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new BigVector();
        other = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.minus(other));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("1.72290869956155921245622469343957147"), new BigDecimal("7.550018873926369323569547474320045934"), new BigDecimal("10.82554072117153428339583781361571383"), new BigDecimal("-10.960949839582536614402496439035885941"), new BigDecimal("-3.487208874319578859922672934445830253")), sut.minus(other));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector other1 = new BigVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector other2 = new BigVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new BigVector(8.5, 1.5);
        final BigVector other3 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigVector(8.5);
        other = new BigVector(9.9);
        Assert.assertEquals(
                new BigVector(84.15), sut.times(other));
        
        sut = new BigVector(8.5, 1.5);
        other = new BigVector(9.9, 6);
        Assert.assertEquals(
                new BigVector(84.15, 9.0), sut.times(other));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        other = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new BigVector(84.15, 9.0, -2.573084), sut.times(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigVector(84.15, 9.0, -2.573084, 54.39), sut.times(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(1, 1, 1, 1);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut.times(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(0, 0, 0, 0);
        Assert.assertEquals(
                new BigVector(0, 0, 0, 0), sut.times(other));
        
        sut = new BigVector();
        other = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.times(other));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("20.783702486616143079547685178307984465"), new BigDecimal("4.008595762865032058081882962025023926"), new BigDecimal("-21.802969502743835484517794316892403533"), new BigDecimal("-17.535250205804777307602903917497197558"), new BigDecimal("103.924739565890567623023125395058688203")), sut.times(other));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector other1 = new BigVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector other2 = new BigVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new BigVector(8.5, 1.5);
        final BigVector other3 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new BigVector(8.5);
        Assert.assertEquals(
                new BigVector(42.5), sut.scale(5));
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(
                new BigVector(8.5799, 1.5141), sut.scale(1.0094));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new BigVector(139.57, 24.63, -82.19852), sut.scale(16.42));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigVector(-22.7545, -4.0155, 13.401062, -29.7147), sut.scale(-2.677));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigVector(-8.5, -1.5, 5.006, -11.1), sut.scale(-1));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigVector(0, 0, 0, 0), sut.scale(0));
        
        sut = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.scale(7.1));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("101.390319798831221092153446743522126731"), new BigDecimal("148.335312299261046223895576680665843633"), new BigDecimal("49.304111695584753051641930681642978264"), new BigDecimal("-35.846476634813541385534491378288767684"), new BigDecimal("158.484956373541032739880859522822871139")), sut.scale(18.4311));
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("27397871616.16087133534968454506643305790272453"), new BigDecimal("40083430554.137886091045110334138108646310798055"), new BigDecimal("13323044301.119350543858350404964162853621741571"), new BigDecimal("-9686498343.046484487932779880446087514195154182"), new BigDecimal("42826085334.678943049598369084947172168781516227")), sut.scale(new BigDecimal("4980484454.00449087891782817687198879187892871987498279")));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigVector(8.5);
        other = new BigVector(9.9);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.858585858585858585858585858585858586")), sut.dividedBy(other));
        
        sut = new BigVector(8.5, 1.5);
        other = new BigVector(9.9, 6);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.858585858585858585858585858585858586"), new BigDecimal("0.25")), sut.dividedBy(other));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        other = new BigVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.858585858585858585858585858585858586"), new BigDecimal("0.25"), new BigDecimal("-9.739299610894941634241245136186770428")), sut.dividedBy(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.858585858585858585858585858585858586"), new BigDecimal("0.25"), new BigDecimal("-9.739299610894941634241245136186770428"), new BigDecimal("2.26530612244897959183673469387755102")), sut.dividedBy(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        other = new BigVector(1, 1, 1, 1);
        Assert.assertEquals(
                new BigVector(new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")), sut.dividedBy(other));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector other0 = new BigVector(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new BigVector();
        other = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.dividedBy(other));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("1.456020728865273800124750920734388921"), new BigDecimal("16.158250125984019258126821898163003137"), new BigDecimal("-0.328207211257100348118432359060412355"), new BigDecimal("-0.215714069638483901402554783412964343"), new BigDecimal("0.711466791221858069030165430431072344")), sut.dividedBy(other));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector other1 = new BigVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector other2 = new BigVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new BigVector(8.5, 1.5);
        final BigVector other3 = new BigVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new BigVector(8.5);
        Assert.assertEquals(
                new BigVector(9.0), sut.round());
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(
                new BigVector(9.0, 2.0), sut.round());
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new BigVector(9.0, 2.0, -5.0), sut.round());
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigVector(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.round());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("6"), new BigDecimal("8"), new BigDecimal("3"), new BigDecimal("-2"), new BigDecimal("9")), sut.round());
    }
    
    /**
     * JUnit test of movePointLeft.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#movePointLeft(int)
     */
    @Test
    public void testMovePointLeft() throws Exception {
        //standard
        
        sut = new BigVector(8.5);
        Assert.assertEquals(
                new BigVector(0.0085), sut.movePointLeft(3));
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(
                new BigVector(0.085, 0.015), sut.movePointLeft(2));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new BigVector(85000, 15000, -50060), sut.movePointLeft(-4));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut.movePointLeft(0));
        
        sut = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.movePointLeft(3));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.000000000005501045504545644106545645"), new BigDecimal("0.000000000008048098718972879872817986"), new BigDecimal("0.000000000002675049871987279817897029"), new BigDecimal("-0.000000000001944890789741987259877842"), new BigDecimal("0.000000000008598779040509846549575492")), sut.movePointLeft(12));
        Assert.assertEquals(
                new BigVector(new BigDecimal("5501045504545.644106545645498289419878972871897982"), new BigDecimal("8048098718972.8798728179857241654509840987187948"), new BigDecimal("2675049871987.27981789702897177287184508797"), new BigDecimal("-1944890789741.9872598778418747816878907980484"), new BigDecimal("8598779040509.846549575492484052654")), sut.movePointLeft(-12));
    }
    
    /**
     * JUnit test of movePointRight.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#movePointRight(int)
     */
    @Test
    public void testMovePointRight() throws Exception {
        //standard
        
        sut = new BigVector(8.5);
        Assert.assertEquals(
                new BigVector(8500.0), sut.movePointRight(3));
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(
                new BigVector(850, 150), sut.movePointRight(2));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new BigVector(0.00085, 0.00015, -0.0005006), sut.movePointRight(-4));
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut.movePointRight(0));
        
        sut = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.movePointRight(3));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("5501045504545.644106545645498289419878972871897982"), new BigDecimal("8048098718972.8798728179857241654509840987187948"), new BigDecimal("2675049871987.27981789702897177287184508797"), new BigDecimal("-1944890789741.9872598778418747816878907980484"), new BigDecimal("8598779040509.846549575492484052654")), sut.movePointRight(12));
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.000000000005501045504545644106545645"), new BigDecimal("0.000000000008048098718972879872817986"), new BigDecimal("0.000000000002675049871987279817897029"), new BigDecimal("-0.000000000001944890789741987259877842"), new BigDecimal("0.000000000008598779040509846549575492")), sut.movePointRight(-12));
    }
    
    /**
     * JUnit test of stripTrailingZeros.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#stripTrailingZeros()
     */
    @Test
    public void testStripTrailingZeros() throws Exception {
        //standard
        
        sut = new BigVector(8.50);
        Assert.assertEquals(
                new BigVector(8.5), sut.stripTrailingZeros());
        
        sut = new BigVector(8.500, 1.5000);
        Assert.assertEquals(
                new BigVector(8.5, 1.5), sut.stripTrailingZeros());
        
        sut = new BigVector(8.50000, 1.500, -5.0060);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006), sut.stripTrailingZeros());
        
        sut = new BigVector(8.500000, 1.500000, -5.0060, 11.1000);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut.stripTrailingZeros());
        
        sut = new BigVector();
        Assert.assertEquals(
                new BigVector(), sut.stripTrailingZeros());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908000000000000000"), new BigDecimal("8.04809871897287987281798572416545098409871879480000000000000"), new BigDecimal("2.675049871987279817897028971772871845087970000000000000"), new BigDecimal("-1.944890789741987259877841874781687890798048400000000000"), new BigDecimal("8.598779040509846549575492484052654000000000000000"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("5.501045504545644106545645498289419878972871897982"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")), sut.stripTrailingZeros());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        BigVector other;
        
        //standard
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        other = new BigVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(new BigDecimal("31.23598538692"), sut.dot(other));
        
        sut = new BigVector(18.1644, 9.12154, -7.7741);
        other = new BigVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(new BigDecimal("631.65974070412"), sut.dot(other));
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        other = new BigVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(new BigDecimal("210.2965501416"), sut.dot(other));
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        other = new BigVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(new BigDecimal("-210.2965501416"), sut.dot(other));
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        other = new BigVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(new BigDecimal("0.02102965501416"), sut.dot(other));
        
        sut = new BigVector(1, -1, 3);
        other = new BigVector(3, 3, 0);
        Assert.assertEquals(new BigDecimal("0"), sut.dot(other));
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        other = BigVector.origin(3);
        Assert.assertEquals(new BigDecimal("0"), sut.dot(other));
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        other = BigVector.identity(3);
        Assert.assertEquals(new BigDecimal("25.06004"), sut.dot(other));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        other = new BigVector(new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertEquals(new BigDecimal("89.3788181068231299685319953010020955029771372385763203679403671468"), sut.dot(other));
        
        //invalid
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        final BigVector other1 = new BigVector(1.0481561, 1.655742, 0.974454, 1.5541);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        final BigVector other2 = new BigVector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dot(other2));
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        //standard
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.562999747283007145410742376683158155"), new BigDecimal("0.629002096275518225117571777302740487"), new BigDecimal("0.536085485198278605793157159200007326")), sut.normalize());
        
        sut = new BigVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(
                new BigVector(new BigDecimal("-0.562999747283007145410742376683158155"), new BigDecimal("-0.629002096275518225117571777302740487"), new BigDecimal("-0.536085485198278605793157159200007326")), sut.normalize());
        
        sut = new BigVector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.834684394815539758038555498007606482"), new BigDecimal("0.419149935846256332416099927181536567"), new BigDecimal("-0.357232826503241925577918031812828034")), sut.normalize());
        
        sut = new BigVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.152531277798652690752209069902203231"), new BigDecimal("0.907171337933659836640569343467606625"), new BigDecimal("0.392140756520874512215236423942795567")), sut.normalize());
        
        sut = new BigVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.478931075086003262092706498778076537"), new BigDecimal("0.756553624145343630766354404367641424"), new BigDecimal("0.445254577864743832296817447859428375")), sut.normalize());
        
        sut = new BigVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.562999747283007145410742376683158155"), new BigDecimal("0.629002096275518225117571777302740487"), new BigDecimal("0.536085485198278605793157159200007326")), sut.normalize());
        
        sut = new BigVector(1, -1, 3);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.301511344577763622646812066970062426"), new BigDecimal("-0.301511344577763622646812066970062426"), new BigDecimal("0.904534033733290867940436200910187277")), sut.normalize());
        
        sut = BigVector.origin(3);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")), sut.normalize());
        
        sut = BigVector.identity(3);
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.577350269189625764509148780501957456"), new BigDecimal("0.577350269189625764509148780501957456"), new BigDecimal("0.577350269189625764509148780501957456")), sut.normalize());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(
                new BigVector(new BigDecimal("0.410125255852407741431185811788092288"), new BigDecimal("0.600018404413618688290625627080354702"), new BigDecimal("0.199435818565792531323387716371121517"), new BigDecimal("-0.144999497293524978888838625239436227"), new BigDecimal("0.641073856068510680641347055215491274")), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        //standard
        
        sut = new BigVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(new BigDecimal("14.5016050884583117584269838010667928500001639480797776428313955814"), sut.hypotenuse());
        
        sut = new BigVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(new BigDecimal("14.5016050884583117584269838010667928500001639480797776428313955814"), sut.hypotenuse());
        
        sut = new BigVector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(new BigDecimal("21.76199784352530442093758218890894665969172273305238450209191823"), sut.hypotenuse());
        
        sut = new BigVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(new BigDecimal("78.9881018102008019763297036151823818916319225050996516156791906733"), sut.hypotenuse());
        
        sut = new BigVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(new BigDecimal("2.1885322429992229840316772057367822319514216308948831654278006709"), sut.hypotenuse());
        
        sut = new BigVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(new BigDecimal("0.0014501605088458311758426983801066792850000163948079777642831396"), sut.hypotenuse());
        
        sut = new BigVector(1, -1, 3);
        Assert.assertEquals(new BigDecimal("3.3166247903553998491149327366706866839270885455893535970586821461"), sut.hypotenuse());
        
        sut = BigVector.origin(3);
        Assert.assertEquals(new BigDecimal("0"), sut.hypotenuse());
        
        sut = BigVector.identity(3);
        Assert.assertEquals(new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069795"), sut.hypotenuse());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("13.4130864316371480116057243741905942545173551885278245223518318153"), sut.hypotenuse());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        BigVector copy;
        
        //standard
        
        sut = new BigVector(8.5);
        copy = new BigVector(1);
        sut.copy(copy);
        Assert.assertEquals(
                new BigVector(8.5), copy);
        
        sut = new BigVector(8.5, 1.5);
        copy = new BigVector(2);
        sut.copy(copy);
        Assert.assertEquals(
                new BigVector(8.5, 1.5), copy);
        
        sut = new BigVector(8.5, 1.5, -5.006);
        copy = new BigVector(3);
        sut.copy(copy);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006), copy);
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        copy = new BigVector(4);
        sut.copy(copy);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new BigVector();
        copy = new BigVector();
        sut.copy(copy);
        Assert.assertEquals(
                new BigVector(), copy);
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        copy = new BigVector(5);
        sut.copy(copy);
        Assert.assertEquals(
                new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")), copy);
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector copy1 = new BigVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector copy2 = new BigVector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#copyMeta(BigVector)
     */
    @Test
    public void testCopyMeta() throws Exception {
        MathContext mathContext = new MathContext(42, RoundingMode.DOWN);
        
        //standard
        
        BigVector component1 = new BigVector(8.1, 6.6, 7.0, 2.6);
        component1.setMathContext(mathContext);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.1"), new BigDecimal("6.6"), new BigDecimal("7.0"), new BigDecimal("2.6")}, component1.getRawComponents());
        
        BigVector component2 = new BigVector(9.1, 6.3, 1.7);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("9.1"), new BigDecimal("6.3"), new BigDecimal("1.7")}, component2.getRawComponents());
        Assert.assertEquals(mathContext.getPrecision(), component2.getMathContext().getPrecision());
        Assert.assertEquals(mathContext.getRoundingMode(), component2.getMathContext().getRoundingMode());
        
        //big
        
        BigVector component3 = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        component3.setMathContext(mathContext);
        Assert.assertEquals(5, component3.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, component3.getRawComponents());
        
        BigVector component4 = new BigVector(9.1, 6.3, 1.7, 0.8, 5.2);
        component3.copyMeta(component4);
        Assert.assertEquals(5, component4.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("9.1"), new BigDecimal("6.3"), new BigDecimal("1.7"), new BigDecimal("0.8"), new BigDecimal("5.2")}, component4.getRawComponents());
        Assert.assertEquals(mathContext.getPrecision(), component4.getMathContext().getPrecision());
        Assert.assertEquals(mathContext.getRoundingMode(), component4.getMathContext().getRoundingMode());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(3);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006), sut);
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(5);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1, 0), sut);
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new BigVector(8.5), sut);
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new BigVector(), sut);
        
        sut = new BigVector();
        sut.redim(3);
        Assert.assertEquals(
                new BigVector(0, 0, 0), sut);
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        sut.redim(3);
        Assert.assertEquals(
                new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797")), sut);
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new BigVector(), sut);
        
        sut = Mockito.spy(BigVector.class);
        sut.setComponents(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006"), new BigDecimal("11.1")});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of subVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#subVector(int, int)
     * @see BigVector#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        //standard
        
        BigVector subVector;
        sut = new BigVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        subVector = sut.subVector(0, 0);
        Assert.assertEquals(
                new BigVector(), subVector);
        
        subVector = sut.subVector(0, 1);
        Assert.assertEquals(
                new BigVector(1.0), subVector);
        
        subVector = sut.subVector(0, 2);
        Assert.assertEquals(
                new BigVector(1, 2), subVector);
        
        subVector = sut.subVector(2, 9);
        Assert.assertEquals(
                new BigVector(3, 4, 5, 6, 7, 8, 9), subVector);
        
        subVector = sut.subVector(8, 10);
        Assert.assertEquals(
                new BigVector(9, 10), subVector);
        
        subVector = sut.subVector(0, 10);
        Assert.assertEquals(
                new BigVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(5);
        Assert.assertEquals(
                new BigVector(6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(8);
        Assert.assertEquals(
                new BigVector(9, 10), subVector);
        
        subVector = sut.subVector(0);
        Assert.assertEquals(
                new BigVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        
        subVector = sut.subVector(1, 3);
        Assert.assertEquals(
                new BigVector(new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797")), subVector);
        
        subVector = sut.subVector(2);
        Assert.assertEquals(
                new BigVector(new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")), subVector);
        
        //invalid
        
        sut = new BigVector(10);
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 1, 0), () ->
                sut.subVector(1, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 3, 1), () ->
                sut.subVector(3, 1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 9, 0), () ->
                sut.subVector(9, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 4, -2), () ->
                sut.subVector(4, -2));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 5, 11), () ->
                sut.subVector(5, 11));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 9, 21), () ->
                sut.subVector(9, 21));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 12, 16), () ->
                sut.subVector(12, 16));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 21, 33), () ->
                sut.subVector(21, 33));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 15, 10), () ->
                sut.subVector(15));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, -3, 4), () ->
                sut.subVector(-3, 4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, -1, 1), () ->
                sut.subVector(-1, 1));
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dimensionalityToLength(int)
     * @see BigVector#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        //standard
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(3, sut.dimensionalityToLength());
        Assert.assertEquals(3, sut.dimensionalityToLength(3));
        Assert.assertEquals(5, sut.dimensionalityToLength(5));
        Assert.assertEquals(1, sut.dimensionalityToLength(1));
        Assert.assertEquals(0, sut.dimensionalityToLength(0));
        
        //big
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(5, sut.dimensionalityToLength());
        Assert.assertEquals(3, sut.dimensionalityToLength(3));
        Assert.assertEquals(5, sut.dimensionalityToLength(5));
        
        //invalid
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(0, sut.dimensionalityToLength(-1));
        Assert.assertEquals(0, sut.dimensionalityToLength(-3));
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#lengthToDimensionality(int)
     * @see BigVector#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        //standard
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(3, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(3));
        Assert.assertEquals(5, sut.lengthToDimensionality(5));
        Assert.assertEquals(1, sut.lengthToDimensionality(1));
        Assert.assertEquals(0, sut.lengthToDimensionality(0));
        
        //big
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(5, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(3));
        Assert.assertEquals(5, sut.lengthToDimensionality(5));
        
        //invalid
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(0, sut.lengthToDimensionality(-1));
        Assert.assertEquals(0, sut.lengthToDimensionality(-3));
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        //standard
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(3, sut.getDimensionality());
        TestUtils.setField(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(3, sut.getDimensionality());
        
        //big
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(5, sut.getDimensionality());
        TestUtils.setField(sut, "dimensionality", 3);
        Assert.assertEquals(3, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(5, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        BigVector component;
        
        //standard
        component = new BigVector(new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getRawComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getComponents());
        
        //big
        component = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, component.getRawComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"), new BigDecimal("8.598779040509846549575492484052654")}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        BigVector component;
        
        //standard
        
        component = new BigVector(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006")}, component.getComponents());
        
        component = new BigVector(8.5, 1.5);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5")}, component.getComponents());
        
        component = new BigVector();
        Assert.assertArrayEquals(new BigDecimal[] {}, component.getComponents());
        
        component = new BigVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.500000000001"), new BigDecimal("1.499999999996"), new BigDecimal("-5.005999999999")}, component.getComponents());
        
        component = new BigVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006")}, component.getComponents());
        
        //big
        
        component = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"), new BigDecimal("8.598779040509846549575492484052654")}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        BigVector component;
        
        //standard
        component = new BigVector(new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getPrimitiveComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.160456540859010650161"), new BigDecimal("6.64908498410841501980404"), new BigDecimal("7.04808971059084054054"), new BigDecimal("2.6908405165094841828")}, component.getComponents());
        
        //big
        component = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"), new BigDecimal("8.598779040509846549575492484052654")}, component.getPrimitiveComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.501045504545644106545645498289419879"), new BigDecimal("8.048098718972879872817985724165450984"), new BigDecimal("2.675049871987279817897028971772871845"), new BigDecimal("-1.944890789741987259877841874781687891"), new BigDecimal("8.598779040509846549575492484052654")}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(new BigDecimal("8.5"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.getRaw(2));
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(new BigDecimal("8.5"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.getRaw(1));
        
        sut = new BigVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(new BigDecimal("8.500000000001"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("1.499999999996"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("-5.005999999999"), sut.getRaw(2));
        
        sut = new BigVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(new BigDecimal("8.5"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.getRaw(2));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), sut.getRaw(0));
        Assert.assertEquals(new BigDecimal("8.0480987189728798728179857241654509840987187948"), sut.getRaw(1));
        Assert.assertEquals(new BigDecimal("2.67504987198727981789702897177287184508797"), sut.getRaw(2));
        Assert.assertEquals(new BigDecimal("-1.9448907897419872598778418747816878907980484"), sut.getRaw(3));
        Assert.assertEquals(new BigDecimal("8.598779040509846549575492484052654"), sut.getRaw(4));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.getRaw(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        sut = new BigVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertEquals(new BigDecimal("8.5"), sut.get(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.get(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.get(2));
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertEquals(new BigDecimal("8.5"), sut.get(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.get(1));
        
        sut = new BigVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(new BigDecimal("8.500000000001"), sut.get(0));
        Assert.assertEquals(new BigDecimal("1.499999999996"), sut.get(1));
        Assert.assertEquals(new BigDecimal("-5.005999999999"), sut.get(2));
        
        sut = new BigVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(new BigDecimal("8.5"), sut.get(0));
        Assert.assertEquals(new BigDecimal("1.5"), sut.get(1));
        Assert.assertEquals(new BigDecimal("-5.006"), sut.get(2));
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("5.501045504545644106545645498289419879"), sut.get(0));
        Assert.assertEquals(new BigDecimal("8.048098718972879872817985724165450984"), sut.get(1));
        Assert.assertEquals(new BigDecimal("2.675049871987279817897028971772871845"), sut.get(2));
        Assert.assertEquals(new BigDecimal("-1.944890789741987259877841874781687891"), sut.get(3));
        Assert.assertEquals(new BigDecimal("8.598779040509846549575492484052654"), sut.get(4));
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.get(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        sut = new BigVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getMathContext()
     */
    @Test
    public void testGetMathContext() throws Exception {
        BigVector component = new BigVector(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        TestUtils.setField(component.getHandler(), "mathContext", newMathContext);
        Assert.assertEquals(newMathContext, component.getMathContext());
    }
    
    /**
     * JUnit test of getRawX.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("8.15"), sut.getRawX());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("8.15"), sut.getRawX());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getRawX());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), sut.getRawX());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawX()));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("8.15"), sut.getX());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("8.15"), sut.getX());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getX());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("5.501045504545644106545645498289419879"), sut.getX());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getX()));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("77.1654"), sut.getRawY());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("77.1655"), sut.getRawY());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getRawY());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("8.0480987189728798728179857241654509840987187948"), sut.getRawY());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawY()));
        
        sut = new BigVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawY()));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("77.1654"), sut.getY());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("77.1655"), sut.getY());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getY());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("8.048098718972879872817985724165450984"), sut.getY());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getY()));
        
        sut = new BigVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getY()));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("0.79455"), sut.getRawZ());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("0.79455"), sut.getRawZ());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getRawZ());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("2.67504987198727981789702897177287184508797"), sut.getRawZ());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawZ()));
        
        sut = new BigVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawZ()));
        
        sut = new BigVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawZ()));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("0.79455"), sut.getZ());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("0.79455"), sut.getZ());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getZ());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("2.675049871987279817897028971772871845"), sut.getZ());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getZ()));
        
        sut = new BigVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getZ()));
        
        sut = new BigVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getZ()));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("3.3154"), sut.getRawW());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("3.3155"), sut.getRawW());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getRawW());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("-1.9448907897419872598778418747816878907980484"), sut.getRawW());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawW()));
        
        sut = new BigVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawW()));
        
        sut = new BigVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawW()));
        
        sut = new BigVector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getRawW()));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //standard
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(new BigDecimal("3.3154"), sut.getW());
        
        sut = new BigVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(new BigDecimal("3.3155"), sut.getW());
        
        sut = new BigVector(4);
        Assert.assertEquals(new BigDecimal("0"), sut.getW());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(new BigDecimal("-1.944890789741987259877841874781687891"), sut.getW());
        
        //invalid
        
        sut = new BigVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getW()));
        
        sut = new BigVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getW()));
        
        sut = new BigVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getW()));
        
        sut = new BigVector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(new BigDecimal("0"), sut.getW()));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new BigVector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new BigVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getDimensionality());
        
        sut = new BigVector(5.501);
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getDimensionality());
        
        sut = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new BigVector();
        Assert.assertEquals(0, sut.getDimensionality());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(5, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new BigVector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new BigVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new BigVector(5.501);
        Assert.assertEquals(1, sut.getLength());
        
        sut = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getLength());
        
        sut = new BigVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getLength());
        
        sut = new BigVector();
        Assert.assertEquals(0, sut.getLength());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertEquals(5, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new BigVector();
        Assert.assertEquals(BigVector.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new BigVector();
        Assert.assertEquals(BigDecimal.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new BigVector();
        Assert.assertTrue(sut.getHandler() instanceof BigComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new BigVector();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new BigVector();
        Assert.assertEquals("Big Vector", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new BigVector();
        Assert.assertEquals("Big Vectors", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new BigVector();
        Assert.assertEquals(new BigDecimal("0.000000000000000000000000000000000001"), sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new BigVector();
        Assert.assertTrue(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        BigVector component;
        BigDecimal[] newComponents;
        
        //standard
        
        component = new BigVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8"), new BigDecimal("8.9")};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8"), new BigDecimal("8.9")}, component.getRawComponents());
        Assert.assertEquals(4, component.getDimensionality());
        
        component = new BigVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8")};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8")}, component.getRawComponents());
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new BigVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8"), new BigDecimal("8.9"), new BigDecimal("9.0")};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.6"), new BigDecimal("6.7"), new BigDecimal("7.8"), new BigDecimal("8.9"), new BigDecimal("9.0")}, component.getRawComponents());
        Assert.assertEquals(5, component.getDimensionality());
        
        component = new BigVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new BigDecimal[] {};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {}, component.getRawComponents());
        Assert.assertEquals(0, component.getDimensionality());
        
        //big
        
        component = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        newComponents = new BigDecimal[] {new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798")};
        Assert.assertEquals(5, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798")}, component.getRawComponents());
        Assert.assertEquals(5, component.getDimensionality());
        
        //invalid
        
        final BigVector component2 = new BigVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component2.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new BigVector(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("5.77"));
        sut.set(1, new BigDecimal("3.0"));
        sut.set(2, new BigDecimal("0.997"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.77"), new BigDecimal("3.0"), new BigDecimal("0.997")}, sut.getRawComponents());
        
        sut = new BigVector(8.5, 1.5);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("5.77"));
        sut.set(1, new BigDecimal("3.0"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.77"), new BigDecimal("3.0")}, sut.getRawComponents());
        
        sut = new BigVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.500000000001"), new BigDecimal("1.499999999996"), new BigDecimal("-5.005999999999")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("5.769999999996"));
        sut.set(1, new BigDecimal("3.000000000001"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.769999999996"), new BigDecimal("3.000000000001"), new BigDecimal("-5.005999999999")}, sut.getRawComponents());
        
        sut = new BigVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.5"), new BigDecimal("1.5"), new BigDecimal("-5.006")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("6.5000000000000000000000001"));
        sut.set(1, new BigDecimal("-1.49999999999999999999996"));
        sut.set(2, new BigDecimal("3.0059999999999999999999"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("6.5000000000000000000000001"), new BigDecimal("-1.49999999999999999999996"), new BigDecimal("3.0059999999999999999999")}, sut.getRawComponents());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        sut.set(0, new BigDecimal("3.77813680498408489408942080484984840898488404"));
        sut.set(1, new BigDecimal("0.498079845046510549248438249845405049804"));
        sut.set(2, new BigDecimal("-8.15049084918425446549880884184284198442084984"));
        sut.set(3, new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        sut.set(4, new BigDecimal("12.08598791482942540949816541849848425340534906798"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("12.08598791482942540949816541849848425340534906798")}, sut.getRawComponents());
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.set(3, new BigDecimal("5.5")));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, new BigDecimal("5.5")));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, new BigDecimal("5.5")));
        
        sut = new BigVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, new BigDecimal("5.5")));
    }
    
    /**
     * JUnit test of setMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setMathContext(MathContext)
     */
    @Test
    public void testSetMathContext() throws Exception {
        BigVector component = new BigVector(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        component.setMathContext(newMathContext);
        MathContext mathContext = (MathContext) TestUtils.getField(component.getHandler(), "mathContext");
        Assert.assertEquals(newMathContext, mathContext);
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //standard
        
        sut = new BigVector(4);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")}, sut.getRawComponents());
        sut.setX(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.555"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        sut.setX(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.555"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        sut.setX(new BigDecimal("3.77813680498408489408942080484984840898488404"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("3.77813680498408489408942080484984840898488404"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        
        //invalid
        
        sut = new BigVector();
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        sut.setX(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setX(null));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //standard
        
        sut = new BigVector(4);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")}, sut.getRawComponents());
        sut.setY(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("5.555"), new BigDecimal("0"), new BigDecimal("0")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        sut.setY(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("5.555"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        sut.setY(new BigDecimal("0.498079845046510549248438249845405049804"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("0.498079845046510549248438249845405049804"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        
        //invalid
        
        sut = new BigVector();
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        sut.setY(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        
        sut = new BigVector(8.15);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15")}, sut.getRawComponents());
        sut.setY(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setY(null));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //standard
        
        sut = new BigVector(4);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")}, sut.getRawComponents());
        sut.setZ(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("5.555"), new BigDecimal("0")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        sut.setZ(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("5.555"), new BigDecimal("3.3154")}, sut.getRawComponents());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        sut.setZ(new BigDecimal("-8.15049084918425446549880884184284198442084984"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("-8.15049084918425446549880884184284198442084984"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        
        //invalid
        
        sut = new BigVector();
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        sut.setZ(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        
        sut = new BigVector(8.15);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15")}, sut.getRawComponents());
        sut.setZ(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654")}, sut.getRawComponents());
        sut.setZ(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //standard
        
        sut = new BigVector(4);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0")}, sut.getRawComponents());
        sut.setW(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("5.555")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        sut.setW(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("5.555")}, sut.getRawComponents());
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        sut.setW(new BigDecimal("9.0160590498405493545246545642541980498782429842"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("9.0160590498405493545246545642541980498782429842"), new BigDecimal("8.598779040509846549575492484052654")}, sut.getRawComponents());
        
        //invalid
        
        sut = new BigVector();
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        sut.setW(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {}, sut.getRawComponents());
        
        sut = new BigVector(8.15);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15")}, sut.getRawComponents());
        sut.setW(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654")}, sut.getRawComponents());
        sut.setW(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455")}, sut.getRawComponents());
        sut.setW(new BigDecimal("5.555"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455")}, sut.getRawComponents());
        
        sut = new BigVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setW(null));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.15"), new BigDecimal("77.1654"), new BigDecimal("0.79455"), new BigDecimal("3.3154")}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        BigVector copy;
        
        //standard
        
        sut = new BigVector(8.5);
        copy = new BigVector(1);
        BigVector.copy(sut, copy);
        Assert.assertEquals(
                new BigVector(8.5), copy);
        
        sut = new BigVector(8.5, 1.5);
        copy = new BigVector(2);
        BigVector.copy(sut, copy);
        Assert.assertEquals(
                new BigVector(8.5, 1.5), copy);
        
        sut = new BigVector(8.5, 1.5, -5.006);
        copy = new BigVector(3);
        BigVector.copy(sut, copy);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006), copy);
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        copy = new BigVector(4);
        BigVector.copy(sut, copy);
        Assert.assertEquals(
                new BigVector(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new BigVector();
        copy = new BigVector();
        BigVector.copy(sut, copy);
        Assert.assertEquals(
                new BigVector(), copy);
        
        //big
        
        sut = new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654"));
        copy = new BigVector(5);
        BigVector.copy(sut, copy);
        Assert.assertEquals(
                new BigVector(new BigDecimal("5.50104550454564410654564549828941987897287189798208908"), new BigDecimal("8.0480987189728798728179857241654509840987187948"), new BigDecimal("2.67504987198727981789702897177287184508797"), new BigDecimal("-1.9448907897419872598778418747816878907980484"), new BigDecimal("8.598779040509846549575492484052654")), copy);
        
        //invalid
        
        sut = new BigVector(8.5, 1.5, -5.006, 11.1);
        final BigVector copy1 = new BigVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                BigVector.copy(sut, copy1));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final BigVector copy2 = new BigVector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                BigVector.copy(sut, copy2));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final Vector copy3 = new Vector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                BigVector.copy(sut, copy3));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        final Matrix copy4 = new Matrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                BigVector.copy(sut, copy4));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                BigVector.copy(sut, null));
        
        sut = new BigVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                BigVector.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new BigVector(), BigVector.createInstance(0));
        Assert.assertEquals(
                new BigVector(0.0), BigVector.createInstance(1));
        Assert.assertEquals(
                new BigVector(0.0, 0.0), BigVector.createInstance(2));
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0), BigVector.createInstance(3));
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0, 0.0), BigVector.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new BigVector(), BigVector.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new BigVector(), BigVector.identity(0));
        Assert.assertEquals(
                new BigVector(1.0), BigVector.identity(1));
        Assert.assertEquals(
                new BigVector(1.0, 1.0), BigVector.identity(2));
        Assert.assertEquals(
                new BigVector(1.0, 1.0, 1.0), BigVector.identity(3));
        Assert.assertEquals(
                new BigVector(1.0, 1.0, 1.0, 1.0), BigVector.identity(4));
        
        //invalid
        Assert.assertEquals(
                new BigVector(), BigVector.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new BigVector(), BigVector.origin(0));
        Assert.assertEquals(
                new BigVector(0.0), BigVector.origin(1));
        Assert.assertEquals(
                new BigVector(0.0, 0.0), BigVector.origin(2));
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0), BigVector.origin(3));
        Assert.assertEquals(
                new BigVector(0.0, 0.0, 0.0, 0.0), BigVector.origin(4));
        
        //invalid
        Assert.assertEquals(
                new BigVector(), BigVector.origin(-1));
    }
    
}
