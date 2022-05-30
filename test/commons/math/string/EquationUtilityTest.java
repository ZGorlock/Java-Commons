/*
 * File:    EquationUtilityTest.java
 * Package: commons.math.string
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.string;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
 * JUnit test of EquationUtility.
 *
 * @see EquationUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({EquationUtility.class})
public class EquationUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(EquationUtilityTest.class);
    
    
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
     * @see EquationUtility#OPERATORS
     * @see EquationUtility#SYMBOLS
     * @see EquationUtility#NUMBER_PATTERN
     * @see EquationUtility#VARIABLE_PATTERN
     * @see EquationUtility#ELEMENT_PATTERN
     */
    @Test
    public void testConstants() throws Exception {
        //constants
        TestUtils.assertArrayEquals(
                EquationUtility.OPERATORS.toArray(Character[]::new),
                new Character[] {'^', '~', '*', '/', '%', '+', '-'});
        Assert.assertEquals("^~*/%+-`()", EquationUtility.SYMBOLS);
        
        //patterns
        Assert.assertEquals("(?:\\d+\\.?\\d*)", EquationUtility.NUMBER_PATTERN.pattern());
        Assert.assertEquals("(?:[a-zA-Z]+)", EquationUtility.VARIABLE_PATTERN.pattern());
        Assert.assertEquals("(?:(?:\\d+\\.?\\d*)|(?:[a-zA-Z]+))", EquationUtility.ELEMENT_PATTERN.pattern());
    }
    
    /**
     * JUnit test of Operation.
     *
     * @throws Exception When there is an exception.
     * @see EquationUtility.Operation
     */
    @Test
    public void testOperation() throws Exception {
        Assert.assertEquals(7, EquationUtility.Operation.values().length);
        Assert.assertEquals(EquationUtility.Operation.POWER, EquationUtility.Operation.values()[0]);
        Assert.assertEquals(EquationUtility.Operation.ROOT, EquationUtility.Operation.values()[1]);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, EquationUtility.Operation.values()[2]);
        Assert.assertEquals(EquationUtility.Operation.DIVIDE, EquationUtility.Operation.values()[3]);
        Assert.assertEquals(EquationUtility.Operation.MODULUS, EquationUtility.Operation.values()[4]);
        Assert.assertEquals(EquationUtility.Operation.ADD, EquationUtility.Operation.values()[5]);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, EquationUtility.Operation.values()[6]);
        
        //getSymbol
        Assert.assertEquals('^', EquationUtility.Operation.POWER.getSymbol());
        Assert.assertEquals('~', EquationUtility.Operation.ROOT.getSymbol());
        Assert.assertEquals('*', EquationUtility.Operation.MULTIPLY.getSymbol());
        Assert.assertEquals('/', EquationUtility.Operation.DIVIDE.getSymbol());
        Assert.assertEquals('%', EquationUtility.Operation.MODULUS.getSymbol());
        Assert.assertEquals('+', EquationUtility.Operation.ADD.getSymbol());
        Assert.assertEquals('-', EquationUtility.Operation.SUBTRACT.getSymbol());
        
        //getOperation
        Assert.assertEquals(EquationUtility.Operation.POWER, EquationUtility.Operation.getOperation('^'));
        Assert.assertEquals(EquationUtility.Operation.ROOT, EquationUtility.Operation.getOperation('~'));
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, EquationUtility.Operation.getOperation('*'));
        Assert.assertEquals(EquationUtility.Operation.DIVIDE, EquationUtility.Operation.getOperation('/'));
        Assert.assertEquals(EquationUtility.Operation.MODULUS, EquationUtility.Operation.getOperation('%'));
        Assert.assertEquals(EquationUtility.Operation.ADD, EquationUtility.Operation.getOperation('+'));
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, EquationUtility.Operation.getOperation('-'));
        Assert.assertNull(EquationUtility.Operation.getOperation('$'));
    }
    
    /**
     * JUnit test of OrderOfOperations.
     *
     * @throws Exception When there is an exception.
     * @see EquationUtility.OrderOfOperations
     */
    @Test
    public void testOrderOfOperations() throws Exception {
        Assert.assertEquals(3, EquationUtility.OrderOfOperations.values().length);
        Assert.assertEquals(EquationUtility.OrderOfOperations.FIRST, EquationUtility.OrderOfOperations.values()[0]);
        Assert.assertEquals(EquationUtility.OrderOfOperations.SECOND, EquationUtility.OrderOfOperations.values()[1]);
        Assert.assertEquals(EquationUtility.OrderOfOperations.THIRD, EquationUtility.OrderOfOperations.values()[2]);
        
        //getSymbols
        Assert.assertEquals("^~", EquationUtility.OrderOfOperations.FIRST.getSymbols());
        Assert.assertEquals("*/%", EquationUtility.OrderOfOperations.SECOND.getSymbols());
        Assert.assertEquals("+-", EquationUtility.OrderOfOperations.THIRD.getSymbols());
    }
    
    /**
     * JUnit test of parseMath.
     *
     * @throws Exception When there is an exception.
     * @see EquationUtility#parseMath(String)
     */
    @Test
    public void testParseMath() throws Exception {
        EquationUtility.MathOperation operation;
        
        //basic case
        
        operation = EquationUtility.parseMath("5 + 3 - 4");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("(5+3)-4", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operand1.op.operation);
        Assert.assertEquals("5", operation.operand1.op.operand1.n);
        Assert.assertEquals("3", operation.operand1.op.operand2.n);
        Assert.assertEquals("4", operation.operand2.n);
        Assert.assertEquals(4, operation.evaluate().intValue());
        
        operation = EquationUtility.parseMath("five plus three minus four");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("(5+3)-4", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operand1.op.operation);
        Assert.assertEquals("5", operation.operand1.op.operand1.n);
        Assert.assertEquals("3", operation.operand1.op.operand2.n);
        Assert.assertEquals("4", operation.operand2.n);
        Assert.assertEquals(4, operation.evaluate().intValue());
        
        //order of operations
        
        operation = EquationUtility.parseMath("-9 * 3 ^ 5 + 3 / 4");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("((0-9)*(3^5))+(3/4)", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operand1.op.operand1.op.operation);
        Assert.assertEquals("0", operation.operand1.op.operand1.op.operand1.n);
        Assert.assertEquals("9", operation.operand1.op.operand1.op.operand2.n);
        Assert.assertNotNull(operation.operand1.op.operand2.op);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.POWER, operation.operand1.op.operand2.op.operation);
        Assert.assertEquals("3", operation.operand1.op.operand2.op.operand1.n);
        Assert.assertEquals("5", operation.operand1.op.operand2.op.operand2.n);
        Assert.assertNotNull(operation.operand2.op);
        Assert.assertNotNull(operation.operand2.op.operand1);
        Assert.assertNotNull(operation.operand2.op.operand2);
        Assert.assertNotNull(operation.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.DIVIDE, operation.operand2.op.operation);
        Assert.assertEquals("3", operation.operand2.op.operand1.n);
        Assert.assertEquals("4", operation.operand2.op.operand2.n);
        Assert.assertEquals(-2186.25, operation.evaluate().doubleValue(), TestUtils.DELTA);
        
        operation = EquationUtility.parseMath("-9 ( 3 ^ 5 ) + 3 / 4");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("((0-9)*(3^5))+(3/4)", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operand1.op.operand1.op.operation);
        Assert.assertEquals("0", operation.operand1.op.operand1.op.operand1.n);
        Assert.assertEquals("9", operation.operand1.op.operand1.op.operand2.n);
        Assert.assertNotNull(operation.operand1.op.operand2.op);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.POWER, operation.operand1.op.operand2.op.operation);
        Assert.assertEquals("3", operation.operand1.op.operand2.op.operand1.n);
        Assert.assertEquals("5", operation.operand1.op.operand2.op.operand2.n);
        Assert.assertNotNull(operation.operand2.op);
        Assert.assertNotNull(operation.operand2.op.operand1);
        Assert.assertNotNull(operation.operand2.op.operand2);
        Assert.assertNotNull(operation.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.DIVIDE, operation.operand2.op.operation);
        Assert.assertEquals("3", operation.operand2.op.operand1.n);
        Assert.assertEquals("4", operation.operand2.op.operand2.n);
        Assert.assertEquals(-2186.25, operation.evaluate().doubleValue(), TestUtils.DELTA);
        
        operation = EquationUtility.parseMath("((-9 ( 3 ^ 5 )) + (3 / 4))");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("(((0-9)*(3^5))+0)+(3/4)", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operand1.op.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operand1.op.operand1.op.operand1.op.operation);
        Assert.assertEquals("0", operation.operand1.op.operand1.op.operand1.op.operand1.n);
        Assert.assertEquals("9", operation.operand1.op.operand1.op.operand1.op.operand2.n);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2.op);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.POWER, operation.operand1.op.operand1.op.operand2.op.operation);
        Assert.assertEquals("3", operation.operand1.op.operand1.op.operand2.op.operand1.n);
        Assert.assertEquals("5", operation.operand1.op.operand1.op.operand2.op.operand2.n);
        Assert.assertEquals("0", operation.operand1.op.operand2.n);
        Assert.assertNotNull(operation.operand2.op);
        Assert.assertNotNull(operation.operand2.op.operand1);
        Assert.assertNotNull(operation.operand2.op.operand2);
        Assert.assertNotNull(operation.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.DIVIDE, operation.operand2.op.operation);
        Assert.assertEquals("3", operation.operand2.op.operand1.n);
        Assert.assertEquals("4", operation.operand2.op.operand2.n);
        Assert.assertEquals(-2186.25, operation.evaluate().doubleValue(), TestUtils.DELTA);
        
        operation = EquationUtility.parseMath("negative nine times three to the fifth power plus three divided by four");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("((0-9)*(3^5))+(3/4)", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operand1.op.operand1.op.operation);
        Assert.assertEquals("0", operation.operand1.op.operand1.op.operand1.n);
        Assert.assertEquals("9", operation.operand1.op.operand1.op.operand2.n);
        Assert.assertNotNull(operation.operand1.op.operand2.op);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.POWER, operation.operand1.op.operand2.op.operation);
        Assert.assertEquals("3", operation.operand1.op.operand2.op.operand1.n);
        Assert.assertEquals("5", operation.operand1.op.operand2.op.operand2.n);
        Assert.assertNotNull(operation.operand2.op);
        Assert.assertNotNull(operation.operand2.op.operand1);
        Assert.assertNotNull(operation.operand2.op.operand2);
        Assert.assertNotNull(operation.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.DIVIDE, operation.operand2.op.operation);
        Assert.assertEquals("3", operation.operand2.op.operand1.n);
        Assert.assertEquals("4", operation.operand2.op.operand2.n);
        Assert.assertEquals(-2186.25, operation.evaluate().doubleValue(), TestUtils.DELTA);
        
        operation = EquationUtility.parseMath("negative nine open parenthesis three to the fifth power close parenthesis plus three divided by four");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("((0-9)*(3^5))+(3/4)", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operand1.op.operand1.op.operation);
        Assert.assertEquals("0", operation.operand1.op.operand1.op.operand1.n);
        Assert.assertEquals("9", operation.operand1.op.operand1.op.operand2.n);
        Assert.assertNotNull(operation.operand1.op.operand2.op);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.POWER, operation.operand1.op.operand2.op.operation);
        Assert.assertEquals("3", operation.operand1.op.operand2.op.operand1.n);
        Assert.assertEquals("5", operation.operand1.op.operand2.op.operand2.n);
        Assert.assertNotNull(operation.operand2.op);
        Assert.assertNotNull(operation.operand2.op.operand1);
        Assert.assertNotNull(operation.operand2.op.operand2);
        Assert.assertNotNull(operation.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.DIVIDE, operation.operand2.op.operation);
        Assert.assertEquals("3", operation.operand2.op.operand1.n);
        Assert.assertEquals("4", operation.operand2.op.operand2.n);
        Assert.assertEquals(-2186.25, operation.evaluate().doubleValue(), TestUtils.DELTA);
        
        //variables
        
        operation = EquationUtility.parseMath("5 + varA - varB + varC");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("((5+varA)-varB)+varC", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operand1.op.operand1.op.operation);
        Assert.assertEquals("5", operation.operand1.op.operand1.op.operand1.n);
        Assert.assertEquals("varA", operation.operand1.op.operand1.op.operand2.var);
        Assert.assertEquals("varB", operation.operand1.op.operand2.var);
        Assert.assertEquals("varC", operation.operand2.var);
        
        Map<String, Number> vars = new HashMap<>();
        vars.put("varA", 3);
        vars.put("varB", 4);
        vars.put("varC", 0);
        Assert.assertEquals(4, operation.evaluate(vars).intValue());
        
        for (int i = 1; i < 100; i++) {
            vars.put("varC", i);
            Assert.assertEquals(4 + i, operation.evaluate(vars).intValue());
        }
        
        operation = EquationUtility.parseMath("five plus varA minus varB plus varC");
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("((5+varA)-varB)+varC", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.SUBTRACT, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operand1.op.operand1.op.operation);
        Assert.assertEquals("5", operation.operand1.op.operand1.op.operand1.n);
        Assert.assertEquals("varA", operation.operand1.op.operand1.op.operand2.var);
        Assert.assertEquals("varB", operation.operand1.op.operand2.var);
        Assert.assertEquals("varC", operation.operand2.var);
        
        vars = new HashMap<>();
        vars.put("varA", 3);
        vars.put("varB", 4);
        vars.put("varC", 0);
        Assert.assertEquals(4, operation.evaluate(vars).intValue());
        
        for (int i = 1; i < 100; i++) {
            vars.put("varC", i);
            Assert.assertEquals(4 + i, operation.evaluate(vars).intValue());
        }
        
        //larger cases
        
        operation = EquationUtility.parseMath(
                "five octodecillion nine hundred sixty seven septendecillion nine hundred forty five sexdecillion six hundred eighty nine quindecillion seven hundred ninety four quattuordecillion four hundred thirty six tredecillion eight hundred seventy seven duodecillion one hundred ninety eight undecillion eight hundred eighty seven decillion eight hundred twenty eight nonillion six hundred eighty nine octillion five hundred eighty two septillion one hundred sixty eight sextillion three hundred sixty six quintillion nine hundred two quadrillion seven hundred ninety five trillion three hundred twenty two billion seven hundred twenty five million eight hundred fifty eight thousand eight hundred twenty nine" +
                        " plus " +
                        "zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"
        );
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829+0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829", operation.operand1.n);
        Assert.assertEquals("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001", operation.operand2.n);
        Assert.assertEquals(new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"), operation.evaluate());
        
        operation = EquationUtility.parseMath(
                "5967945689794436877198887828689582168366902795322725858829" +
                        " + " +
                        "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"
        );
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829+0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.ADD, operation.operation);
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829", operation.operand1.n);
        Assert.assertEquals("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001", operation.operand2.n);
        Assert.assertEquals(new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"), operation.evaluate());
        
        //overflow cases
        
        operation = EquationUtility.parseMath(
                "ten milliauntillion" +
                        " times " +
                        "one hundred and fifty seven"
        );
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("(1*(10^3007))*157", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand2.op);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.POWER, operation.operand1.op.operand2.op.operation);
        Assert.assertEquals("1", operation.operand1.op.operand1.n);
        Assert.assertEquals("10", operation.operand1.op.operand2.op.operand1.n);
        Assert.assertEquals("3007", operation.operand1.op.operand2.op.operand2.n);
        Assert.assertEquals("157", operation.operand2.n);
        Assert.assertEquals("one milliaduotillion five hundred seventy milliauntillion", NumberNameUtility.numberToNumberPhrase(operation.evaluate()));
        
        operation = EquationUtility.parseMath(
                "fourteen milliaquintillion seven hundred eighty six milliaquattuortillion eight thousand nine hundred seventy four and sixteen thousandths six hundred millionths" +
                        " times " +
                        "sixty seven"
        );
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("(1.4786*(10^3019))*67", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operation);
        Assert.assertNotNull(operation.operand1.op);
        Assert.assertNotNull(operation.operand1.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operand1.op.operation);
        Assert.assertNotNull(operation.operand1.op.operand2.op);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand1);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operand2);
        Assert.assertNotNull(operation.operand1.op.operand2.op.operation);
        Assert.assertEquals(EquationUtility.Operation.POWER, operation.operand1.op.operand2.op.operation);
        Assert.assertEquals("1.4786", operation.operand1.op.operand1.n);
        Assert.assertEquals("10", operation.operand1.op.operand2.op.operand1.n);
        Assert.assertEquals("3019", operation.operand1.op.operand2.op.operand2.n);
        Assert.assertEquals("67", operation.operand2.n);
        Assert.assertEquals("nine hundred ninety milliaquintillion six hundred sixty two milliaquattuortillion", NumberNameUtility.numberToNumberPhrase(operation.evaluate()));
        
        operation = EquationUtility.parseMath(
                "one hundred and fifty trillion forty eight million and one" +
                        " times " +
                        "two"
        );
        
        Assert.assertNotNull(operation);
        Assert.assertEquals("150000048000001*2", operation.toString());
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertNotNull(operation.operation);
        Assert.assertEquals(EquationUtility.Operation.MULTIPLY, operation.operation);
        Assert.assertNotNull(operation.operand1);
        Assert.assertNotNull(operation.operand2);
        Assert.assertEquals("150000048000001", operation.operand1.n);
        Assert.assertEquals("2", operation.operand2.n);
        Assert.assertEquals("three hundred trillion ninety six million two", NumberNameUtility.numberToNumberPhrase(operation.evaluate()));
        
        //malformed equation
        
        Assert.assertNull(EquationUtility.parseMath("5 + var1 - var2 + var3")); //numbers in variables
        Assert.assertNull(EquationUtility.parseMath("* 5 + 3 / 4")); //invalid operator at beginning
        Assert.assertNull(EquationUtility.parseMath("5 + 3 / 4 -")); //invalid operator at end
        
        Assert.assertNull(EquationUtility.parseMath("five plus var1 minus var2 plus var3")); //numbers in variables
        Assert.assertNull(EquationUtility.parseMath("times five plus three divided by four")); //invalid operator at beginning
        Assert.assertNull(EquationUtility.parseMath("five plus three divided by four minus")); //invalid operator at end
    }
    
    /**
     * JUnit test of cleanEquation.
     *
     * @throws Exception When there is an exception.
     * @see EquationUtility#cleanEquation(String)
     */
    @Test
    public void testCleanEquation() throws Exception {
        //add
        
        Assert.assertEquals("18+45", EquationUtility.cleanEquation("18+45"));
        Assert.assertEquals("18+45", EquationUtility.cleanEquation("18 + 45"));
        Assert.assertEquals("18+45", EquationUtility.cleanEquation("18 plus 45"));
        Assert.assertEquals("18+45+1", EquationUtility.cleanEquation("18 add 45 add 1"));
        Assert.assertEquals("18+45+1+3", EquationUtility.cleanEquation("18 sum 45 add 1 plus 3"));
        Assert.assertEquals("18.48+45.0788", EquationUtility.cleanEquation("18.48 plus 45.0788"));
        Assert.assertEquals("18.48+45.0788+1.9744", EquationUtility.cleanEquation("18.48 add 45.0788 add 1.9744"));
        Assert.assertEquals("18.48+45.0788+1.9744+3.432", EquationUtility.cleanEquation("18.48 sum 45.0788 add 1.9744 plus 3.4320"));
        Assert.assertEquals("18.4+45.07+1.001+3.432731", EquationUtility.cleanEquation("18.4 sum 45.07 add 1.001 plus 3.432731"));
        
        Assert.assertEquals("18+45", EquationUtility.cleanEquation("eighteen plus forty five"));
        Assert.assertEquals("18+45+1", EquationUtility.cleanEquation("eighteen add forty five add one"));
        Assert.assertEquals("18+45+1+3", EquationUtility.cleanEquation("eighteen sum forty five add one plus three"));
        Assert.assertEquals("18.48+45.0788", EquationUtility.cleanEquation("eighteen point four eight plus forty five point zero seven eight eight"));
        Assert.assertEquals("18.48+45.0788+1.9744", EquationUtility.cleanEquation("eighteen point four eight add forty five point zero seven eight eight add one point nine seven four four"));
        Assert.assertEquals("18.4+45.07+1.001+3.432731", EquationUtility.cleanEquation("eighteen and four tenths sum forty five and seven hundredths add one and one thousandth plus three point four three two seven three one"));
        Assert.assertEquals("18.4+45.07+1.001+3.432731", EquationUtility.cleanEquation("eighteen and four tenths sum forty five and seven hundredths add one and one thousandth plus three and four hundred and thirty two thousandths seven hundred and thirty one millionths"));
        
        //subtract
        
        Assert.assertEquals("18-45", EquationUtility.cleanEquation("18-45"));
        Assert.assertEquals("18-45", EquationUtility.cleanEquation("18 - 45"));
        Assert.assertEquals("18-45", EquationUtility.cleanEquation("18 minus 45"));
        Assert.assertEquals("18-45-1", EquationUtility.cleanEquation("18 subtract 45 subtract 1"));
        Assert.assertEquals("18-45-1-3", EquationUtility.cleanEquation("18 sub 45 subtract 1 minus 3"));
        Assert.assertEquals("18-45-1-3-11", EquationUtility.cleanEquation("18 sub 45 subtract 1 minus 3 less 11"));
        Assert.assertEquals("18.48-45.0788", EquationUtility.cleanEquation("18.48 minus 45.0788"));
        Assert.assertEquals("18.48-45.0788-1.9744", EquationUtility.cleanEquation("18.48 subtract 45.0788 subtract 1.9744"));
        Assert.assertEquals("18.48-45.0788-1.9744-3.432", EquationUtility.cleanEquation("18.48 sub 45.0788 subtract 1.9744 minus 3.4320"));
        Assert.assertEquals("18.48-45.0788-1.9744-3.432-11.4", EquationUtility.cleanEquation("18.48 sub 45.0788 subtract 1.9744 minus 3.4320 less 11.4"));
        Assert.assertEquals("18.4-45.07-1.001-3.432731", EquationUtility.cleanEquation("18.4 sub 45.07 subtract 1.001 minus 3.432731"));
        
        Assert.assertEquals("18-45", EquationUtility.cleanEquation("eighteen minus forty five"));
        Assert.assertEquals("18-45-1", EquationUtility.cleanEquation("eighteen subtract forty five subtract one"));
        Assert.assertEquals("18-45-1-3", EquationUtility.cleanEquation("eighteen sub forty five subtract one minus three"));
        Assert.assertEquals("18-45-1-3-11", EquationUtility.cleanEquation("eighteen sub forty five subtract one minus three less eleven"));
        Assert.assertEquals("18.48-45.0788", EquationUtility.cleanEquation("eighteen point four eight minus forty five point zero seven eight eight"));
        Assert.assertEquals("18.48-45.0788-1.9744", EquationUtility.cleanEquation("eighteen point four eight subtract forty five point zero seven eight eight subtract one point nine seven four four"));
        Assert.assertEquals("18.48-45.0788-1.9744-3.432", EquationUtility.cleanEquation("eighteen point four eight sub forty five point zero seven eight eight subtract one point nine seven four four minus three point four three two zero"));
        Assert.assertEquals("18.48-45.0788-1.9744-3.432-11.4", EquationUtility.cleanEquation("eighteen point four eight sub forty five point zero seven eight eight subtract one point nine seven four four minus three point four three two zero less eleven point four"));
        Assert.assertEquals("18.4-45.07-1.001-3.432731", EquationUtility.cleanEquation("eighteen and four tenths sub forty five and seven hundredths subtract one and one thousandth minus three point four three two seven three one"));
        Assert.assertEquals("18.4-45.07-1.001-3.432731", EquationUtility.cleanEquation("eighteen and four tenths sub forty five and seven hundredths subtract one and one thousandth minus three and four hundred and thirty two thousandths seven hundred and thirty one millionths"));
        
        //multiply
        
        Assert.assertEquals("18*45", EquationUtility.cleanEquation("18*45"));
        Assert.assertEquals("18*45", EquationUtility.cleanEquation("18 * 45"));
        Assert.assertEquals("18*45", EquationUtility.cleanEquation("18 times 45"));
        Assert.assertEquals("18*45*1", EquationUtility.cleanEquation("18 multiply 45 multiply 1"));
        Assert.assertEquals("18*45*1*3", EquationUtility.cleanEquation("18 mult 45 multiply 1 times 3"));
        Assert.assertEquals("18*45*1*3", EquationUtility.cleanEquation("18 mult by 45 multiply by 1 times by 3"));
        Assert.assertEquals("18.48*45.0788", EquationUtility.cleanEquation("18.48 times 45.0788"));
        Assert.assertEquals("18.48*45.0788*1.9744", EquationUtility.cleanEquation("18.48 multiply 45.0788 multiply 1.9744"));
        Assert.assertEquals("18.48*45.0788*1.9744*3.432", EquationUtility.cleanEquation("18.48 mult 45.0788 multiply 1.9744 times 3.4320"));
        Assert.assertEquals("18.48*45.0788*1.9744*3.432", EquationUtility.cleanEquation("18.48 mult by 45.0788 multiply by 1.9744 times by 3.4320"));
        Assert.assertEquals("18.4*45.07*1.001*3.432731", EquationUtility.cleanEquation("18.4 mult 45.07 multiply 1.001 times 3.432731"));
        
        Assert.assertEquals("18*45", EquationUtility.cleanEquation("eighteen times forty five"));
        Assert.assertEquals("18*45*1", EquationUtility.cleanEquation("eighteen multiply forty five multiply one"));
        Assert.assertEquals("18*45*1*3", EquationUtility.cleanEquation("eighteen mult forty five multiply one times three"));
        Assert.assertEquals("18*45*1*3", EquationUtility.cleanEquation("eighteen mult by forty five multiply by one times by three"));
        Assert.assertEquals("18.48*45.0788", EquationUtility.cleanEquation("eighteen point four eight times forty five point zero seven eight eight"));
        Assert.assertEquals("18.48*45.0788*1.9744", EquationUtility.cleanEquation("eighteen point four eight multiply forty five point zero seven eight eight multiply one point nine seven four four"));
        Assert.assertEquals("18.48*45.0788*1.9744*3.432", EquationUtility.cleanEquation("eighteen point four eight mult forty five point zero seven eight eight multiply one point nine seven four four times three point four three two zero"));
        Assert.assertEquals("18.48*45.0788*1.9744*3.432", EquationUtility.cleanEquation("eighteen point four eight mult by forty five point zero seven eight eight multiply by one point nine seven four four times by three point four three two zero"));
        Assert.assertEquals("18.4*45.07*1.001*3.432731", EquationUtility.cleanEquation("eighteen and four tenths mult forty five and seven hundredths multiply one and one thousandth times three point four three two seven three one"));
        Assert.assertEquals("18.4*45.07*1.001*3.432731", EquationUtility.cleanEquation("eighteen and four tenths mult forty five and seven hundredths multiply one and one thousandth times three and four hundred and thirty two thousandths seven hundred and thirty one millionths"));
        
        //divide
        
        Assert.assertEquals("18/45", EquationUtility.cleanEquation("18/45"));
        Assert.assertEquals("18/45", EquationUtility.cleanEquation("18 / 45"));
        Assert.assertEquals("18/45", EquationUtility.cleanEquation("18 divided by 45"));
        Assert.assertEquals("18/45/1", EquationUtility.cleanEquation("18 divide 45 divide 1"));
        Assert.assertEquals("18/45/1/3", EquationUtility.cleanEquation("18 div 45 divide 1 divided 3"));
        Assert.assertEquals("18/45/1/3", EquationUtility.cleanEquation("18 div by 45 divide by 1 divided by 3"));
        Assert.assertEquals("18.48/45.0788", EquationUtility.cleanEquation("18.48 divided by 45.0788"));
        Assert.assertEquals("18.48/45.0788/1.9744", EquationUtility.cleanEquation("18.48 divide 45.0788 divide 1.9744"));
        Assert.assertEquals("18.48/45.0788/1.9744/3.432", EquationUtility.cleanEquation("18.48 div 45.0788 divide 1.9744 divided 3.4320"));
        Assert.assertEquals("18.48/45.0788/1.9744/3.432", EquationUtility.cleanEquation("18.48 div by 45.0788 divide by 1.9744 divided by 3.4320"));
        Assert.assertEquals("18.4/45.07/1.001/3.432731", EquationUtility.cleanEquation("18.4 div 45.07 divide 1.001 divided by 3.432731"));
        
        Assert.assertEquals("18/45", EquationUtility.cleanEquation("eighteen divided by forty five"));
        Assert.assertEquals("18/45/1", EquationUtility.cleanEquation("eighteen divide forty five divide one"));
        Assert.assertEquals("18/45/1/3", EquationUtility.cleanEquation("eighteen div forty five divide one divided three"));
        Assert.assertEquals("18/45/1/3", EquationUtility.cleanEquation("eighteen div by forty five divide by one divided by three"));
        Assert.assertEquals("18.48/45.0788", EquationUtility.cleanEquation("eighteen point four eight divided by forty five point zero seven eight eight"));
        Assert.assertEquals("18.48/45.0788/1.9744", EquationUtility.cleanEquation("eighteen point four eight divide forty five point zero seven eight eight divide one point nine seven four four"));
        Assert.assertEquals("18.48/45.0788/1.9744/3.432", EquationUtility.cleanEquation("eighteen point four eight div forty five point zero seven eight eight divide one point nine seven four four divided three point four three two zero"));
        Assert.assertEquals("18.48/45.0788/1.9744/3.432", EquationUtility.cleanEquation("eighteen point four eight div by forty five point zero seven eight eight divide by one point nine seven four four divided by three point four three two zero"));
        Assert.assertEquals("18.4/45.07/1.001/3.432731", EquationUtility.cleanEquation("eighteen and four tenths div forty five and seven hundredths divide one and one thousandth divided by three point four three two seven three one"));
        Assert.assertEquals("18.4/45.07/1.001/3.432731", EquationUtility.cleanEquation("eighteen and four tenths div forty five and seven hundredths divide one and one thousandth divided by three and four hundred and thirty two thousandths seven hundred and thirty one millionths"));
        
        //modulus
        
        Assert.assertEquals("18%45", EquationUtility.cleanEquation("18%45"));
        Assert.assertEquals("18%45", EquationUtility.cleanEquation("18 % 45"));
        Assert.assertEquals("18%45", EquationUtility.cleanEquation("18 modulus 45"));
        Assert.assertEquals("18%45%1", EquationUtility.cleanEquation("18 modulo 45 modulo 1"));
        Assert.assertEquals("18%45%1%3", EquationUtility.cleanEquation("18 modular 45 modulo 1 modulus 3"));
        Assert.assertEquals("18%45%1%3%11", EquationUtility.cleanEquation("18 modular 45 modulo 1 modulus 3 mod 11"));
        Assert.assertEquals("18.48%45.0788", EquationUtility.cleanEquation("18.48 modulus 45.0788"));
        Assert.assertEquals("18.48%45.0788%1.9744", EquationUtility.cleanEquation("18.48 modulo 45.0788 modulo 1.9744"));
        Assert.assertEquals("18.48%45.0788%1.9744%3.432", EquationUtility.cleanEquation("18.48 modular 45.0788 modulo 1.9744 modulus 3.4320"));
        Assert.assertEquals("18.48%45.0788%1.9744%3.432%11.4", EquationUtility.cleanEquation("18.48 modular 45.0788 modulo 1.9744 modulus 3.4320 mod 11.4"));
        Assert.assertEquals("18.4%45.07%1.001%3.432731", EquationUtility.cleanEquation("18.4 modular 45.07 modulo 1.001 modulus 3.432731"));
        
        Assert.assertEquals("18%45", EquationUtility.cleanEquation("eighteen modulus forty five"));
        Assert.assertEquals("18%45%1", EquationUtility.cleanEquation("eighteen modulo forty five modulo one"));
        Assert.assertEquals("18%45%1%3", EquationUtility.cleanEquation("eighteen modular forty five modulo one modulus three"));
        Assert.assertEquals("18%45%1%3%11", EquationUtility.cleanEquation("eighteen modular forty five modulo one modulus three mod eleven"));
        Assert.assertEquals("18.48%45.0788", EquationUtility.cleanEquation("eighteen point four eight modulus forty five point zero seven eight eight"));
        Assert.assertEquals("18.48%45.0788%1.9744", EquationUtility.cleanEquation("eighteen point four eight modulo forty five point zero seven eight eight modulo one point nine seven four four"));
        Assert.assertEquals("18.48%45.0788%1.9744%3.432", EquationUtility.cleanEquation("eighteen point four eight modular forty five point zero seven eight eight modulo one point nine seven four four modulus three point four three two zero"));
        Assert.assertEquals("18.48%45.0788%1.9744%3.432%11.4", EquationUtility.cleanEquation("eighteen point four eight modular forty five point zero seven eight eight modulo one point nine seven four four modulus three point four three two zero mod eleven point four"));
        Assert.assertEquals("18.4%45.07%1.001%3.432731", EquationUtility.cleanEquation("eighteen and four tenths modular forty five and seven hundredths modulo one and one thousandth modulus three point four three two seven three one"));
        Assert.assertEquals("18.4%45.07%1.001%3.432731", EquationUtility.cleanEquation("eighteen and four tenths modular forty five and seven hundredths modulo one and one thousandth modulus three and four hundred and thirty two thousandths seven hundred and thirty one millionths"));
        
        //common roots
        
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("square root of 49"));
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("square root 49"));
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("squared root of 49"));
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("squared root 49"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cube root of 81"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cube root 81"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cubed root of 81"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cubed root 81"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("square root of 49.41"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("square root 49.41"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("squared root of 49.41"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("squared root 49.41"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cube root of 81.784"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cube root 81.784"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cubed root of 81.784"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cubed root 81.784"));
        
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("square root of forty nine"));
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("square root forty nine"));
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("squared root of forty nine"));
        Assert.assertEquals("2~49", EquationUtility.cleanEquation("squared root forty nine"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cube root of eighty one"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cube root eighty one"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cubed root of eighty one"));
        Assert.assertEquals("3~81", EquationUtility.cleanEquation("cubed root eighty one"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("square root of forty nine point four one"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("square root forty nine point four one"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("squared root of forty nine point four one"));
        Assert.assertEquals("2~49.41", EquationUtility.cleanEquation("squared root forty nine point four one"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cube root of eighty one point seven eight four"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cube root eighty one point seven eight four"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cubed root of eighty one point seven eight four"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cubed root eighty one point seven eight four"));
        Assert.assertEquals("3~81.784", EquationUtility.cleanEquation("cubed root eighty one and seven hundred eighty four thousandths"));
        
        //roots
        
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4~256"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4 ~ 256"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4 root of 256"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4 root 256"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4th root 256"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4th~256"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4th ~ 256"));
        Assert.assertEquals("21~123456789", EquationUtility.cleanEquation("21st root of 123456789"));
        Assert.assertEquals("2~4", EquationUtility.cleanEquation("2nd root 4"));
        Assert.assertEquals("3~8", EquationUtility.cleanEquation("3rd root 8"));
        Assert.assertEquals("4.9~256.144", EquationUtility.cleanEquation("4.9 root of 256.144"));
        Assert.assertEquals("4.9~256.144", EquationUtility.cleanEquation("4.9 root 256.144"));
        Assert.assertEquals("4.9~256.144", EquationUtility.cleanEquation("4.9th root 256.144"));
        Assert.assertEquals("21.541~123456789.411", EquationUtility.cleanEquation("21.541st root of 123456789.411"));
        Assert.assertEquals("2.8722~4.04877", EquationUtility.cleanEquation("2.8722nd root 4.04877"));
        Assert.assertEquals("3.893~8.42458", EquationUtility.cleanEquation("3.893rd root 8.42458"));
        
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("four root of two hundred and fifty six"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("four root two hundred and fifty six"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4 root two hundred and fifty six"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("4th root two hundred and fifty six"));
        Assert.assertEquals("4~256", EquationUtility.cleanEquation("fourth root of two hundred and fifty six"));
        Assert.assertEquals("21~123456789", EquationUtility.cleanEquation("twenty first root of one hundred and twenty three million four hundred and fifty six thousand seven hundred and eighty nine"));
        Assert.assertEquals("2~4", EquationUtility.cleanEquation("second root four"));
        Assert.assertEquals("3~8", EquationUtility.cleanEquation("third root eight"));
        Assert.assertEquals("4.9~256.144", EquationUtility.cleanEquation("four point nine root of two hundred and fifty six point one four four"));
        Assert.assertEquals("4.9~256.144", EquationUtility.cleanEquation("four point nine root two hundred and fifty six point one four four"));
        Assert.assertEquals("4.9~256.144", EquationUtility.cleanEquation("four point ninth root two hundred and fifty six point one four four"));
        Assert.assertEquals("21.541~123456789.411", EquationUtility.cleanEquation("twenty one point five four first root of one hundred and twenty three million four hundred and fifty six thousand seven hundred and eighty nine point four one one"));
        Assert.assertEquals("2.8722~4.04877", EquationUtility.cleanEquation("two point eight seven two second root four point zero four eight seven seven"));
        Assert.assertEquals("3.893~8.42458", EquationUtility.cleanEquation("three point eight nine third root eight point four two four five eight"));
        Assert.assertEquals("3.893~8.42458", EquationUtility.cleanEquation("three and eight hundred ninety three thousandths root eight and four hundred twenty four thousandths five hundred eighty millionths"));
        Assert.assertEquals("3.893~8.42458", EquationUtility.cleanEquation("three and eight hundred ninety third thousandths root eight and four hundred twenty four thousandths five hundred eighty millionths"));
        
        //powers
        
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4^3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 ^ 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 to the power of 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 to the power 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4 power of 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4th power of 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4th^3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4th ^ 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4 power 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4th power 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4 pow of 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4th pow of 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4 pow 3"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4th pow 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 raised to the power of 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 raised to the 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 raised to the 3rd"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 raised to the 3rd power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 raised to 3rd"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 raised to 3rd power"));
        Assert.assertEquals("4^2", EquationUtility.cleanEquation("4 squared"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("4 cubed"));
        Assert.assertEquals("5^3^6", EquationUtility.cleanEquation("5 raised to the 6 power of 3"));
        Assert.assertEquals("5^3^6", EquationUtility.cleanEquation("5 raised to the 6th power of 3"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 to the power of 3.9"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 to the power 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6 power of 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th power of 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6 power 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th power 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6 pow of 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th pow of 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6 pow 3.9"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th pow 3.9"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 raised to the power of 3.9"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 raised to the 3.9"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 raised to the 3.9rd"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 raised to the 3.9rd power"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 raised to 3.9rd"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("4.6 raised to 3.9rd power"));
        Assert.assertEquals("4.6^2", EquationUtility.cleanEquation("4.6 squared"));
        Assert.assertEquals("4.6^3", EquationUtility.cleanEquation("4.6 cubed"));
        Assert.assertEquals("5.2^3.87^6.04", EquationUtility.cleanEquation("5.2 raised to the 6.04 power of 3.87"));
        Assert.assertEquals("5.2^3.87^6.04", EquationUtility.cleanEquation("5.2 raised to the 6.04th power of 3.87"));
        
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four to the power of three"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four to the power three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4 power of three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("4th power of three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("four power of three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("fourth power of three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("four power three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("fourth power three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("four pow of three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("fourth pow of three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("four pow three"));
        Assert.assertEquals("3^4", EquationUtility.cleanEquation("fourth pow three"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the power of three"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the power of 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the power of 3rd"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the power of third"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the three"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the 3rd"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the third"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the third"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the third power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the three power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the 3 power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to the 3rd power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to third"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to three"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to 3"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to 3rd"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to third power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to three power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to 3 power"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four raised to 3rd power"));
        Assert.assertEquals("4^2", EquationUtility.cleanEquation("four squared"));
        Assert.assertEquals("4^3", EquationUtility.cleanEquation("four cubed"));
        Assert.assertEquals("5^3^6", EquationUtility.cleanEquation("five raised to the six power of three"));
        Assert.assertEquals("5^3^6", EquationUtility.cleanEquation("five raised to the sixth power of three"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("four point six to the power of three point nine"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("four point six to the power three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("four point six power of three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th power of three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("four point six power three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th power three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("four point six pow of three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th pow of three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("four point six pow three point nine"));
        Assert.assertEquals("3.9^4.6", EquationUtility.cleanEquation("4.6th pow three point nine"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("four point six raised to the power of three point nine"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("four point six raised to the three point ninth"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("four point six raised to the three point ninth power"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("four point six raised to three point ninth"));
        Assert.assertEquals("4.6^3.9", EquationUtility.cleanEquation("four point six raised to three point ninth power"));
        Assert.assertEquals("4.6^2", EquationUtility.cleanEquation("four point six squared"));
        Assert.assertEquals("4.6^3", EquationUtility.cleanEquation("four point six cubed"));
        Assert.assertEquals("5.2^3.87^6.04", EquationUtility.cleanEquation("five point two raised to the six point zero four power of three point eight seven"));
        Assert.assertEquals("5.2^3.87^6.04", EquationUtility.cleanEquation("five point two raised to the six point zero fourth power of three point eight seven"));
        Assert.assertEquals("5.2^3.87^6.04", EquationUtility.cleanEquation("five and two tenths raised to the six and four hundredths power of three and eighty seven hundredths"));
        
        //parenthesis
        
        Assert.assertEquals("(4+2)/3", EquationUtility.cleanEquation("(4+2)/3"));
        Assert.assertEquals("(4+2)/3", EquationUtility.cleanEquation("(4 + 2) / 3"));
        Assert.assertEquals("(4+2)/3", EquationUtility.cleanEquation("(4 plus 2) divided by 3"));
        Assert.assertEquals("(4.6+2.1)/3.9", EquationUtility.cleanEquation("(4.6 plus 2.1) divided by 3.9"));
        
        Assert.assertEquals("(4+2)/3", EquationUtility.cleanEquation("open paren four plus two close paren divided by three"));
        Assert.assertEquals("(4.6+2.1)/3.9", EquationUtility.cleanEquation("open paren four point six plus two point one close paren divided by three point nine"));
        Assert.assertEquals("(4+2)/3", EquationUtility.cleanEquation("open parenthesis four plus two close parenthesis divided by three"));
        Assert.assertEquals("(4.6+2.1)/3.9", EquationUtility.cleanEquation("open parenthesis four point six plus two point one close parenthesis divided by three point nine"));
        Assert.assertEquals("(4+2)/3", EquationUtility.cleanEquation("begin paren four plus two end paren divided by three"));
        Assert.assertEquals("(4.6+2.1)/3.9", EquationUtility.cleanEquation("begin paren four point six plus two point one end paren divided by three point nine"));
        Assert.assertEquals("(4+2)/3", EquationUtility.cleanEquation("begin parenthesis four plus two end parenthesis divided by three"));
        Assert.assertEquals("(4.6+2.1)/3.9", EquationUtility.cleanEquation("begin parenthesis four point six plus two point one end parenthesis divided by three point nine"));
        Assert.assertEquals("(4.6+2.1)/3.9", EquationUtility.cleanEquation("begin parenthesis four and six tenths plus two and one tenth end parenthesis divided by three and nine tenths"));
        
        //complex
        
        Assert.assertEquals("45+34-5^43.56/3~8+1", EquationUtility.cleanEquation("45 plus 34 minus 5 to the power of 43.56 divided by the 3rd root of 8 add 1"));
        Assert.assertEquals("(5+6)/2.4^2-5~90", EquationUtility.cleanEquation("(5 plus 6) divided by 2.4 squared minus the 5th root of 90"));
        Assert.assertEquals("((5+6)/2.4)^2-5~90", EquationUtility.cleanEquation("((5 plus 6) divided by 2.4) squared minus the 5th root of 90"));
        
        Assert.assertEquals("45+34-5^43.56/3~8+1", EquationUtility.cleanEquation("forty five plus thirty four minus five to the power of forty three point five six divided by the third root of eight add one"));
        Assert.assertEquals("(5+6)/2.4^2-5~90", EquationUtility.cleanEquation("(five plus six) divided by two point four squared minus the fifth root of ninety"));
        Assert.assertEquals("((5+6)/2.4)^2-5~90", EquationUtility.cleanEquation("begin parenthesis open parenthesis five plus six end paren divided by two point four close paren squared minus the fifth root of ninety"));
        Assert.assertEquals("((5+6)/2.4)^2-5~90", EquationUtility.cleanEquation("begin parenthesis open parenthesis five plus six end paren divided by two and four tenths close paren squared minus the fifth root of ninety"));
    }
    
    /**
     * JUnit test of MathOperand.
     *
     * @throws Exception When there is an exception.
     * @see EquationUtility.MathOperand
     */
    @Test
    public void testMathOperand() throws Exception {
        EquationUtility.MathOperand operand = new EquationUtility.MathOperand();
        
        //number
        
        operand.n = "456.12";
        Assert.assertEquals("456.12", operand.toString());
        operand.n = null;
        
        //variable
        
        operand.var = "x";
        Assert.assertEquals("x", operand.toString());
        operand.var = null;
        
        //operation
        
        operand.op = EquationUtility.parseMath("5 + 3");
        Assert.assertEquals("(5+3)", operand.toString());
        operand.op = null;
        
        //order of operations
        
        operand.op = EquationUtility.parseMath("5 + 3 - 4");
        Assert.assertEquals("((5+3)-4)", operand.toString());
        operand.op = null;
        
        operand.op = EquationUtility.parseMath("5 + 3 / 4");
        Assert.assertEquals("(5+(3/4))", operand.toString());
        operand.op = null;
        
        operand.op = EquationUtility.parseMath("9 * 3 ^ 5 + 3 / 4");
        Assert.assertEquals("((9*(3^5))+(3/4))", operand.toString());
        operand.op = null;
    }
    
    /**
     * JUnit test of MathOperation.
     *
     * @throws Exception When there is an exception.
     * @see EquationUtility.MathOperation
     */
    @Test
    public void testMathOperation() throws Exception {
        final EquationUtility.MathOperation operation = new EquationUtility.MathOperation();
        final EquationUtility.MathOperation operation2 = new EquationUtility.MathOperation();
        
        //basic case
        
        operation.operand1 = new EquationUtility.MathOperand();
        operation.operand2 = new EquationUtility.MathOperand();
        operation.operation = EquationUtility.Operation.SUBTRACT;
        
        EquationUtility.MathOperation subOperation = new EquationUtility.MathOperation();
        subOperation.operand1 = new EquationUtility.MathOperand();
        subOperation.operand2 = new EquationUtility.MathOperand();
        subOperation.operation = EquationUtility.Operation.ADD;
        subOperation.operand1.n = "5";
        subOperation.operand2.n = "3";
        operation.operand1.op = subOperation;
        
        operation.operand2.n = "4";
        
        Assert.assertEquals("(5+3)-4", operation.toString());
        Assert.assertEquals(4.0, operation.evaluate().doubleValue(), TestUtils.DELTA);
        
        //order of operations
        
        operation2.operand1 = new EquationUtility.MathOperand();
        operation2.operand2 = new EquationUtility.MathOperand();
        operation2.operation = EquationUtility.Operation.ADD;
        
        EquationUtility.MathOperation subOperationA = new EquationUtility.MathOperation();
        subOperationA.operand1 = new EquationUtility.MathOperand();
        subOperationA.operand2 = new EquationUtility.MathOperand();
        subOperationA.operation = EquationUtility.Operation.MULTIPLY;
        
        subOperationA.operand1.n = "9";
        
        EquationUtility.MathOperation subOperationA1 = new EquationUtility.MathOperation();
        subOperationA1.operand1 = new EquationUtility.MathOperand();
        subOperationA1.operand2 = new EquationUtility.MathOperand();
        subOperationA1.operation = EquationUtility.Operation.POWER;
        
        subOperationA1.operand1.n = "3";
        subOperationA1.operand2.n = "5";
        
        subOperationA.operand2.op = subOperationA1;
        operation2.operand1.op = subOperationA;
        
        EquationUtility.MathOperation subOperationB = new EquationUtility.MathOperation();
        subOperationB.operand1 = new EquationUtility.MathOperand();
        subOperationB.operand2 = new EquationUtility.MathOperand();
        subOperationB.operation = EquationUtility.Operation.DIVIDE;
        
        subOperationB.operand1.n = "3";
        subOperationB.operand2.n = "4";
        
        operation2.operand2.op = subOperationB;
        
        Assert.assertEquals("(9*(3^5))+(3/4)", operation2.toString());
        Assert.assertEquals(2187.75, operation2.evaluate().doubleValue(), TestUtils.DELTA);
        
        //variables
        
        subOperationA.operand1.n = null;
        subOperationB.operand2.n = null;
        subOperationA1.operand2.n = null;
        
        subOperationA.operand1.var = "x";
        subOperationA1.operand2.var = "y";
        subOperationB.operand2.var = "z";
        
        Assert.assertEquals("(x*(3^y))+(3/z)", operation2.toString());
        
        final Map<String, Number> vars = new HashMap<>();
        vars.put("x", 6);
        vars.put("y", 2.4);
        vars.put("z", Math.sin(Math.PI * 3 / 4));
        
        Assert.assertEquals(new BigDecimal("88.0423016785487071035707696631600004229391971494347347553572409381"), operation2.evaluate(vars));
        
        //invalid variables
        
        vars.clear();
        vars.put("x", 6);
        vars.put("y", 2.4);
        vars.put("d", Math.sin(Math.PI * 3 / 4)); //missing z
        
        TestUtils.assertException(NullPointerException.class, () ->
                operation2.evaluate(vars));
    }
    
}
