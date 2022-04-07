/*
 * File:    BigComponentMathHandlerTest.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.IntFunction;

import commons.math.BigMathUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of BigComponentMathHandler.
 *
 * @see BigComponentMathHandler
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigComponentMathHandler.class})
public class BigComponentMathHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigComponentMathHandlerTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private BigComponentMathHandler sut;
    
    
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
        sut = new BigComponentMathHandler();
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
     * @see BigComponentMathHandler#PRECISION
     * @see BigComponentMathHandler#SIGNIFICANT_FIGURES
     * @see BigComponentMathHandler#DEFAULT_MATH_PRECISION
     * @see BigComponentMathHandler#DEFAULT_ROUNDING_MODE
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(BigDecimal.valueOf(1E-36), BigComponentMathHandler.PRECISION);
        Assert.assertEquals(36, BigComponentMathHandler.SIGNIFICANT_FIGURES);
        Assert.assertEquals(BigMathUtility.DEFAULT_MATH_PRECISION, BigComponentMathHandler.DEFAULT_MATH_PRECISION);
        Assert.assertEquals(BigMathUtility.DEFAULT_ROUNDING_MODE, BigComponentMathHandler.DEFAULT_ROUNDING_MODE);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#BigComponentMathHandler()
     */
    @Test
    public void testConstructors() throws Exception {
        BigComponentMathHandler sut = new BigComponentMathHandler();
        Assert.assertNotNull(sut);
        Assert.assertTrue(sut instanceof ComponentMathHandlerInterface);
    }
    
    /**
     * JUnit test of zero.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#zero()
     */
    @Test
    public void testZero() throws Exception {
        Assert.assertEquals(new BigDecimal("0"), sut.zero());
    }
    
    /**
     * JUnit test of one.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#one()
     */
    @Test
    public void testOne() throws Exception {
        Assert.assertEquals(new BigDecimal("1"), sut.one());
    }
    
    /**
     * JUnit test of negativeOne.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#negativeOne()
     */
    @Test
    public void testNegativeOne() throws Exception {
        Assert.assertEquals(new BigDecimal("-1"), sut.negativeOne());
    }
    
    /**
     * JUnit test of valueOf.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#valueOf(Number)
     */
    @Test
    public void testValueOf() throws Exception {
        Assert.assertEquals(new BigDecimal("1"), sut.valueOf(1));
        Assert.assertEquals(new BigDecimal("18"), sut.valueOf(18));
        Assert.assertEquals(new BigDecimal("1616.4133"), sut.valueOf(1616.4133));
        Assert.assertEquals(new BigDecimal("-6.4"), sut.valueOf(-6.4));
        Assert.assertEquals(new BigDecimal("18"), sut.valueOf(Integer.valueOf("18")));
        Assert.assertEquals(new BigDecimal("1616.4133"), sut.valueOf(Double.valueOf("1616.4133")));
        Assert.assertEquals(new BigDecimal("18.1647506819810510351971084987"), sut.valueOf(new BigDecimal("18.1647506819810510351971084987")));
    }
    
    /**
     * JUnit test of array.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#array(int)
     */
    @Test
    public void testArray() throws Exception {
        BigDecimal[] array;
        
        //standard
        
        array = sut.array(2);
        Assert.assertNotNull(array);
        Assert.assertEquals(2, array.length);
        
        array = sut.array(8);
        Assert.assertNotNull(array);
        Assert.assertEquals(8, array.length);
        
        array = sut.array(0);
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
        
        //invalid
        
        TestUtils.assertException(NegativeArraySizeException.class, () ->
                sut.array(-1));
    }
    
    /**
     * JUnit test of arrayGenerator.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#arrayGenerator()
     */
    @Test
    public void testArrayGenerator() throws Exception {
        IntFunction<BigDecimal[]> generator;
        BigDecimal[] array;
        
        //standard
        
        generator = sut.arrayGenerator();
        Assert.assertNotNull(generator);
        array = generator.apply(2);
        Assert.assertNotNull(array);
        Assert.assertEquals(2, array.length);
        
        generator = sut.arrayGenerator();
        Assert.assertNotNull(generator);
        array = generator.apply(8);
        Assert.assertNotNull(array);
        Assert.assertEquals(8, array.length);
        
        generator = sut.arrayGenerator();
        Assert.assertNotNull(generator);
        array = generator.apply(0);
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
        
        //invalid
        
        TestUtils.assertException(NegativeArraySizeException.class, () ->
                sut.arrayGenerator().apply(-1));
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#add(BigDecimal, BigDecimal)
     */
    @Test
    public void testAdd() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("9.093"), sut.add(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("7.986"), sut.add(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("13"), sut.add(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertEquals(new BigDecimal("-1"), sut.add(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("-13"), sut.add(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("1"), sut.add(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("6"), sut.add(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("5"), sut.add(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("807262977402119866.577032582984099646264675399161486774068136951870987"),
                sut.add(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("321649848421554654.968048401554249841415490849874298789170848971870987")));
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#subtract(BigDecimal, BigDecimal)
     */
    @Test
    public void testSubtract() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1.107"), sut.subtract(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("0"), sut.subtract(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("-1"), sut.subtract(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertEquals(new BigDecimal("13"), sut.subtract(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("1"), sut.subtract(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("0.00000002"), sut.subtract(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("4"), sut.subtract(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("5"), sut.subtract(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("163963280559010556.640935779875599963433693699412889195726439008129013"),
                sut.subtract(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("321649848421554654.968048401554249841415490849874298789170848971870987")));
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#multiply(BigDecimal, BigDecimal)
     */
    @Test
    public void testMultiply() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("20.3643"), sut.multiply(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("15.944049"), sut.multiply(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("42"), sut.multiply(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertEquals(new BigDecimal("-42"), sut.multiply(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("42"), sut.multiply(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("0.2499999999999999"), sut.multiply(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("5"), sut.multiply(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("0"), sut.multiply(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("156197389328115670303474151178971635.1877657793194613874744269744969745924123050341307381570671583914"),
                sut.multiply(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("321649848421554654.968048401554249841415490849874298789170848971870987")));
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#divide(BigDecimal, BigDecimal)
     */
    @Test
    public void testDivide() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1.2772351615326821938392186326070623591284748309541697971450037566"), sut.divide(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("1"), sut.divide(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("0.8571428571428571428571428571428571428571428571428571428571428571"), sut.divide(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertEquals(new BigDecimal("-0.8571428571428571428571428571428571428571428571428571428571428571"), sut.divide(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("0.8571428571428571428571428571428571428571428571428571428571428571"), sut.divide(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("1.0000000400000008000000160000003200000064000001280000025600000512"), sut.divide(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("5"), sut.divide(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.divide(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("1.5097570583777179206967621354608279080342689920187609505307867276"),
                sut.divide(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("321649848421554654.968048401554249841415490849874298789170848971870987")));
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#power(BigDecimal, BigDecimal)
     */
    @Test
    public void testPower() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("668.848431817073492636532960683926501967889625051679943753014139834"), sut.power(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("251.7608206814401814859210428356763124933653804332271848465233926957"), sut.power(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("279936"), sut.power(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertEquals(new BigDecimal("0.0000035722450845907636031092821216278006401463191586648376771834"), sut.power(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("-0.0000035722450845907636031092821216278006401463191586648376771834"), sut.power(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("0.7071067931589059428318459879708718301228798854621009498908698584"), sut.power(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("5"), sut.power(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("1"), sut.power(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("15137579240697933796851885548315769962207923617288179196130229357790141.1517460967090599902347915137558534458062809118884366029974852813"),
                sut.power(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("3.968048401554249841415490849874298789170848971870987")));
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#root(BigDecimal, BigDecimal)
     */
    @Test
    public void testRoot() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1.5038435195204094354066757406264735936436025225636084278331885345"), sut.root(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("1.4144524660268805340910299701030003808460926560979146581159422083"), sut.root(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("1.2917083420907466068275097553654779819070687334597373628114560719"), sut.root(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertEquals(new BigDecimal("0.7741685699586097657668843632350786218172689585346645682207488143"), sut.root(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        TestUtils.assertException(ArithmeticException.class, "Result of root is imaginary", () ->
                sut.root(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(new BigDecimal("0.2500000030685281746028415171509270924772471325464193145515598802"), sut.root(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("5"), sut.root(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.root(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("786013.743768254270833882303879116136981964490156798758108407658146822"),
                sut.root(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("3")));
        Assert.assertEquals(new BigDecimal("15466433495295412499023611679537033631383377069071860256091919717954555618746195215761497736288772707382375752568911674707134443470866066103504779855486983583751316437950986008868049165095774057300631205677165355804916360902067252274690819222348061110476389209016437182982924353393295210298843002027977692102025727209864091478462962487401528904611880600824686359234237451443768697535106664024704138935506678165219668487717463368314786245112249351123975878934532980032897390671120033970530220117657359293732955044382945381806819082172887283570647280164673608220650603081142737309275105223241985180845148006045131342931929585828584139272435283956277543290281355912251531204849162399808618704370190691160030264806149893642218242519864921893234957568111856037015983761438303.8797086075973767519068093447977901303826693684151996747933700452"),
                sut.root(new BigDecimal("0.0000000578"), new BigDecimal("-.00941")));
    }
    
    /**
     * JUnit test of sqrt.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#sqrt(BigDecimal)
     */
    @Test
    public void testSqrt() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("2.2583179581272429849720499859691153953599704039739026390694602733"), sut.sqrt(BigDecimal.valueOf(5.1)));
        Assert.assertEquals(new BigDecimal("1.9982492337043444991788716544140078414685256500946084693216631785"), sut.sqrt(BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("2.4494897427831780981972840747058913919659474806566701284326925673"), sut.sqrt(BigDecimal.valueOf(6.0)));
        TestUtils.assertException(ArithmeticException.class, "Result of square root is imaginary", () ->
                sut.sqrt(BigDecimal.valueOf(-6.0)));
        Assert.assertEquals(new BigDecimal("0.7071067882576153009109809003393026141475498816369607416512016449"), sut.sqrt(BigDecimal.valueOf(0.50000001)));
        Assert.assertEquals(new BigDecimal("0.7071067741154796771800297052156341855029209514091722371867433259"), sut.sqrt(BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("1"), sut.sqrt(BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("0"), sut.sqrt(BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("696859475.7772654493969839490261321257922258246745999559386657847676363082"),
                sut.sqrt(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798")));
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#reciprocal(BigDecimal)
     */
    @Test
    public void testReciprocal() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0.196078431372549019607843137254901960784313725490196078431372549"), sut.reciprocal(BigDecimal.valueOf(5.1)));
        Assert.assertEquals(new BigDecimal("0.2504382669671925870272977710994239919859754570498372151264713248"), sut.reciprocal(BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("0.1666666666666666666666666666666666666666666666666666666666666667"), sut.reciprocal(BigDecimal.valueOf(6.0)));
        Assert.assertEquals(new BigDecimal("-0.1666666666666666666666666666666666666666666666666666666666666667"), sut.reciprocal(BigDecimal.valueOf(-6.0)));
        Assert.assertEquals(new BigDecimal("1.9999999600000007999999840000003199999936000001279999974400000512"), sut.reciprocal(BigDecimal.valueOf(0.50000001)));
        Assert.assertEquals(new BigDecimal("2.0000000400000008000000160000003200000064000001280000025600000512"), sut.reciprocal(BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("1"), sut.reciprocal(BigDecimal.valueOf(1.0)));
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.reciprocal(BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("2.0592523972720291394441972066549852468889337295E-18"),
                sut.reciprocal(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798")));
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#abs(BigDecimal)
     */
    @Test
    public void testAbs() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("5.1"), sut.abs(BigDecimal.valueOf(5.1)));
        Assert.assertEquals(new BigDecimal("3.993"), sut.abs(BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("6"), sut.abs(BigDecimal.valueOf(6.0)));
        Assert.assertEquals(new BigDecimal("6"), sut.abs(BigDecimal.valueOf(-6.0)));
        Assert.assertEquals(new BigDecimal("0.50000001"), sut.abs(BigDecimal.valueOf(0.50000001)));
        Assert.assertEquals(new BigDecimal("0.49999999"), sut.abs(BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("1"), sut.abs(BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("0"), sut.abs(BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"),
                sut.abs(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798")));
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#negate(BigDecimal)
     */
    @Test
    public void testNegate() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("-5.1"), sut.negate(BigDecimal.valueOf(5.1)));
        Assert.assertEquals(new BigDecimal("-3.993"), sut.negate(BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("-6"), sut.negate(BigDecimal.valueOf(6.0)));
        Assert.assertEquals(new BigDecimal("6"), sut.negate(BigDecimal.valueOf(-6.0)));
        Assert.assertEquals(new BigDecimal("-0.50000001"), sut.negate(BigDecimal.valueOf(0.50000001)));
        Assert.assertEquals(new BigDecimal("-0.49999999"), sut.negate(BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("-1"), sut.negate(BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("0"), sut.negate(BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("-485613128980565211.60898418142984980484918454928718798489728798"),
                sut.negate(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798")));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#round(BigDecimal)
     */
    @Test
    public void testRound() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("5"), sut.round(BigDecimal.valueOf(5.1)));
        Assert.assertEquals(new BigDecimal("4"), sut.round(BigDecimal.valueOf(3.993)));
        Assert.assertEquals(new BigDecimal("6"), sut.round(BigDecimal.valueOf(6.0)));
        Assert.assertEquals(new BigDecimal("-6"), sut.round(BigDecimal.valueOf(-6.0)));
        Assert.assertEquals(new BigDecimal("1"), sut.round(BigDecimal.valueOf(0.50000001)));
        Assert.assertEquals(new BigDecimal("0"), sut.round(BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(new BigDecimal("1"), sut.round(BigDecimal.valueOf(1.0)));
        Assert.assertEquals(new BigDecimal("0"), sut.round(BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(new BigDecimal("485613128980565212"),
                sut.round(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798")));
    }
    
    /**
     * JUnit test of compare.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#compare(BigDecimal, BigDecimal)
     */
    @Test
    public void testCompare() throws Exception {
        //standard
        Assert.assertEquals(1, sut.compare(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(0, sut.compare(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertEquals(-1, sut.compare(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertEquals(1, sut.compare(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(1, sut.compare(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertEquals(1, sut.compare(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertEquals(1, sut.compare(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        Assert.assertEquals(1, sut.compare(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertEquals(1,
                sut.compare(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("321649848421554654.968048401554249841415490849874298789170848971870987")));
    }
    
    /**
     * JUnit test of isEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#isEqual(BigDecimal, BigDecimal)
     */
    @Test
    public void testIsEqual() throws Exception {
        //standard
        Assert.assertFalse(sut.isEqual(BigDecimal.valueOf(5.1), BigDecimal.valueOf(3.993)));
        Assert.assertTrue(sut.isEqual(BigDecimal.valueOf(3.993), BigDecimal.valueOf(3.993)));
        Assert.assertFalse(sut.isEqual(BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0)));
        Assert.assertFalse(sut.isEqual(BigDecimal.valueOf(6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertFalse(sut.isEqual(BigDecimal.valueOf(-6.0), BigDecimal.valueOf(-7.0)));
        Assert.assertFalse(sut.isEqual(BigDecimal.valueOf(0.50000001), BigDecimal.valueOf(0.49999999)));
        Assert.assertFalse(sut.isEqual(BigDecimal.valueOf(5.0), BigDecimal.valueOf(1.0)));
        Assert.assertFalse(sut.isEqual(BigDecimal.valueOf(5.0), BigDecimal.valueOf(0.0)));
        
        //edge case
        Assert.assertTrue(sut.isEqual(new BigDecimal("1.0"), new BigDecimal("1.000000000000000000000000000000000001")));
        Assert.assertTrue(sut.isEqual(new BigDecimal("1.0"), new BigDecimal("0.999999999999999999999999999999999999")));
        Assert.assertFalse(sut.isEqual(new BigDecimal("1.0"), new BigDecimal("1.00000000000000000000000000000000001")));
        Assert.assertFalse(sut.isEqual(new BigDecimal("1.0"), new BigDecimal("0.99999999999999999999999999999999999")));
        Assert.assertFalse(sut.isEqual(new BigDecimal("1.0"), new BigDecimal("1.00000000000000000000000000000000002")));
        Assert.assertFalse(sut.isEqual(new BigDecimal("1.0"), new BigDecimal("0.99999999999999999999999999999999998")));
        
        //big
        Assert.assertFalse(
                sut.isEqual(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798"), new BigDecimal("321649848421554654.968048401554249841415490849874298789170848971870987")));
        Assert.assertFalse(
                sut.isEqual(new BigDecimal("1"), new BigDecimal("1.00000000000000000000000000000000001")));
        Assert.assertTrue(
                sut.isEqual(new BigDecimal("1"), new BigDecimal("1.000000000000000000000000000000000001")));
        Assert.assertFalse(
                sut.isEqual(new BigDecimal("1"), new BigDecimal("1.000000000000000000000000000000000002")));
    }
    
    /**
     * JUnit test of isZero.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#isZero(BigDecimal)
     */
    @Test
    public void testIsZero() throws Exception {
        //standard
        Assert.assertFalse(sut.isZero(BigDecimal.valueOf(5.1)));
        Assert.assertFalse(sut.isZero(BigDecimal.valueOf(3.993)));
        Assert.assertFalse(sut.isZero(BigDecimal.valueOf(6.0)));
        Assert.assertFalse(sut.isZero(BigDecimal.valueOf(-6.0)));
        Assert.assertFalse(sut.isZero(BigDecimal.valueOf(0.50000001)));
        Assert.assertFalse(sut.isZero(BigDecimal.valueOf(0.49999999)));
        Assert.assertFalse(sut.isZero(BigDecimal.valueOf(1.0)));
        Assert.assertTrue(sut.isZero(BigDecimal.valueOf(0.0)));
        
        //big
        Assert.assertFalse(
                sut.isZero(new BigDecimal("485613128980565211.60898418142984980484918454928718798489728798")));
    }
    
    /**
     * JUnit test of clean.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#clean(BigDecimal)
     */
    @Test
    public void testClean() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("5.1"), sut.clean(new BigDecimal("5.10000000000000000000000000000000000000000000000000000000000000001")));
        Assert.assertEquals(new BigDecimal("3.994"), sut.clean(new BigDecimal("3.99399999999999999999999999999999999999999999999999999999999999996")));
        Assert.assertEquals(new BigDecimal("6"), sut.clean(new BigDecimal("6.00000000000000000000000000000000000000000000000000000000000000001123")));
        Assert.assertEquals(new BigDecimal("-6.0000000000000000000000000000000000000000000000000000000000000001"), sut.clean(new BigDecimal("-6.00000000000000000000000000000000000000000000000000000000000000009711")));
        Assert.assertEquals(new BigDecimal("0.50000002"), sut.clean(new BigDecimal("0.5000000199999999999999999999999999999999999999999999999999999999999")));
        Assert.assertEquals(new BigDecimal("0.5"), sut.clean(new BigDecimal("0.499999999999999999999999999999999999999999999999999999999999999999999")));
        Assert.assertEquals(new BigDecimal("1"), sut.clean(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000001")));
        Assert.assertEquals(new BigDecimal("0"), sut.clean(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000001")));
        
        //big
        Assert.assertEquals(new BigDecimal("485613128980565211.6089841454928718798489728798142989287187984897498048491489749805"),
                sut.clean(new BigDecimal("485613128980565211.608984145492871879848972879814298928718798489749804849148974980484918454928718454928718798489728798")));
        
        //custom precision
        sut.setMathPrecision(1);
        Assert.assertEquals(new BigDecimal("5.1"), sut.clean(new BigDecimal("5.10000000000000000000000000000000000000000000000000000000000000001")));
        Assert.assertEquals(new BigDecimal("4"), sut.clean(new BigDecimal("3.99399999999999999999999999999999999999999999999999999999999999996")));
        Assert.assertEquals(new BigDecimal("6"), sut.clean(new BigDecimal("6.00000000000000000000000000000000000000000000000000000000000000001123")));
        Assert.assertEquals(new BigDecimal("-6"), sut.clean(new BigDecimal("-6.00000000000000000000000000000000000000000000000000000000000000009711")));
        Assert.assertEquals(new BigDecimal("0.5"), sut.clean(new BigDecimal("0.5000000199999999999999999999999999999999999999999999999999999999999")));
        Assert.assertEquals(new BigDecimal("0.5"), sut.clean(new BigDecimal("0.499999999999999999999999999999999999999999999999999999999999999999999")));
        Assert.assertEquals(new BigDecimal("1"), sut.clean(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000001")));
        Assert.assertEquals(new BigDecimal("0"), sut.clean(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000001")));
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        Assert.assertEquals(BigDecimal.valueOf(1E-36), sut.getPrecision());
    }
    
    /**
     * JUnit test of getSignificantFigures.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#getSignificantFigures()
     */
    @Test
    public void testGetSignificantFigures() throws Exception {
        Assert.assertEquals(36, sut.getSignificantFigures());
    }
    
    /**
     * JUnit test of getMathPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#getMathPrecision()
     */
    @Test
    public void testGetMathPrecision() throws Exception {
        MathContext mathContext = TestAccess.getFieldValue(sut, MathContext.class, "mathContext");
        Assert.assertEquals(64, sut.getMathPrecision());
        MathContext newMathContext = new MathContext(0, RoundingMode.HALF_UP);
        TestAccess.setFieldValue(sut, "mathContext", newMathContext);
        Assert.assertEquals(0, sut.getMathPrecision());
    }
    
    /**
     * JUnit test of setMathPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#setMathPrecision(int)
     */
    @Test
    public void testSetMathPrecision() throws Exception {
        MathContext mathContext = TestAccess.getFieldValue(sut, MathContext.class, "mathContext");
        Assert.assertEquals(64, mathContext.getPrecision());
        sut.setMathPrecision(128);
        mathContext = TestAccess.getFieldValue(sut, MathContext.class, "mathContext");
        Assert.assertEquals(128, mathContext.getPrecision());
    }
    
}
