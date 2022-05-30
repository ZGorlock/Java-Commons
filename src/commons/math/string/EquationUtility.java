/*
 * File:    EquationUtility.java
 * Package: commons.math.string
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.string;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import commons.object.string.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional equation functionality.<br>
 * Allows the solving of standard equations.<br>
 * This cannot solve integrals, derivatives, trigonometric functions, or factorials.<br>
 * You can, however, pass the result of those functions at particular values in as variables to a standard equation.
 */
public final class EquationUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(EquationUtility.class);
    
    
    //Constants
    
    /**
     * The list of operators for the Operations that can be applied to operands in the equation.
     */
    public final static List<Character> OPERATORS = Arrays.stream(Operation.values()).map(Operation::getSymbol).collect(Collectors.toList());
    
    /**
     * A string containing the valid symbols for equations.
     */
    public final static String SYMBOLS = Stream.of(OPERATORS, List.of('`', '(', ')')).flatMap(List::stream).map(String::valueOf).collect(Collectors.joining());
    
    /**
     * The regex pattern to match a number.
     */
    public static final Pattern NUMBER_PATTERN = Pattern.compile("(?:\\d+\\.?\\d*)");
    
    /**
     * The regex pattern to match a number.
     */
    public static final Pattern VARIABLE_PATTERN = Pattern.compile("(?:[a-zA-Z]+)");
    
    /**
     * The regex pattern to match a number or variable.
     */
    public static final Pattern ELEMENT_PATTERN = Pattern.compile("(?:" + NUMBER_PATTERN.pattern() + '|' + VARIABLE_PATTERN.pattern() + ')');
    
    
    //Enums
    
    /**
     * An enumeration of equation Operations.
     */
    public enum Operation {
        
        //Values
        
        POWER('^'),
        ROOT('~'),
        MULTIPLY('*'),
        DIVIDE('/'),
        MODULUS('%'),
        ADD('+'),
        SUBTRACT('-');
        
        
        //Fields
        
        /**
         * The symbol of the Operation.
         */
        private final char symbol;
        
        
        //Constructors
        
        /**
         * Constructs an Operation.
         *
         * @param symbol The symbol of the Operation.
         */
        Operation(char symbol) {
            this.symbol = symbol;
        }
        
        
        //Getters
        
        /**
         * Returns the symbol of the Operation.
         *
         * @return The symbol of the Operation.
         */
        public char getSymbol() {
            return symbol;
        }
        
        
        //Static Methods
        
        /**
         * Retrieves an Operation by its symbol.
         *
         * @param symbol The symbol of the Operation.
         * @return The Operation.
         */
        public static Operation getOperation(char symbol) {
            return Arrays.stream(Operation.values()).filter(e -> e.getSymbol() == symbol).findFirst().orElse(null);
        }
        
    }
    
    /**
     * An enumeration representing the Order of Operations.
     */
    public enum OrderOfOperations {
        
        //Values
        
        FIRST("^~"),
        SECOND("*/%"),
        THIRD("+-");
        
        
        //Fields
        
        /**
         * The string of symbols corresponding to the stage of the Order of Operations.
         */
        private final String symbols;
        
        
        //Constructors
        
        /**
         * Constructs an Order of Operations.
         *
         * @param symbols The string of symbols corresponding to the stage of the Order of Operations.
         */
        OrderOfOperations(String symbols) {
            this.symbols = symbols;
        }
        
        
        //Getters
        
        /**
         * Returns the string of symbols corresponding to the stage of the Order of Operations.
         *
         * @return The string of symbols corresponding to the stage of the Order of Operations.
         */
        public String getSymbols() {
            return symbols;
        }
        
    }
    
    
    //Static Methods
    
    /**
     * Parses a mathematical equation into a series of Operations on Operands.
     *
     * @param equation The mathematical equation.
     * @return A series of Operations on Operands that represents the mathematical equation.
     * @see #cleanEquation(String)
     * @see #parseElements(String)
     * @see #ingestElements(List)
     */
    public static MathOperation parseMath(String equation) {
        try {
            return ingestElements(parseElements(cleanEquation(equation)));
        } catch (ParseException e) {
            logger.trace("Equation: {} is malformed: {}", equation, e.getMessage());
            return null;
        }
    }
    
    /**
     * Tokenizes the elements from a mathematical equation.
     *
     * @param equation The mathematical equation.
     * @return The tokens parsed from the mathematical equation.
     * @throws ParseException When there is an error parsing the mathematical equation.
     */
    @SuppressWarnings("UnnecessaryContinue")
    private static List<String> parseElements(String equation) throws ParseException {
        if (equation.startsWith("(") && equation.endsWith(")")) {
            int depth = 1;
            for (int i = 1; i < equation.length(); i++) {
                char c2 = equation.charAt(i);
                if (c2 == '(') {
                    depth++;
                } else if (c2 == ')') {
                    depth--;
                    if (depth == 0) {
                        if (i == equation.length() - 1) {
                            equation = StringUtility.skin(equation, 1);
                        }
                        break;
                    }
                }
                if (i == equation.length() - 1) {
                    throw new ParseException("Parentheses in equation are not lined up", i);
                }
            }
        }
        
        List<String> elements = new ArrayList<>();
        StringBuilder element = new StringBuilder();
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (OPERATORS.contains(c)) {
                if (element.length() > 0) {
                    elements.add(element.toString());
                    element = new StringBuilder();
                }
                elements.add(String.valueOf(c));
            } else if (c == '(') {
                if (element.length() > 0) {
                    elements.add(element.toString());
                    element = new StringBuilder();
                }
                
                int depth = 1;
                element.append(c);
                for (i = i + 1; i < equation.length(); i++) {
                    char c2 = equation.charAt(i);
                    element.append(c2);
                    if (c2 == '(') {
                        depth++;
                    } else if (c2 == ')') {
                        depth--;
                        if (depth == 0) {
                            break;
                        }
                    }
                    if (i == equation.length() - 1) {
                        throw new ParseException("Parentheses in equation are not lined up", i);
                    }
                }
                elements.add(element.toString());
                element = new StringBuilder();
            } else if (c == ')') {
                throw new ParseException("Parentheses in equation are not lined up", i);
            } else {
                element.append(c);
                if ((i == equation.length() - 1) && (element.length() > 0)) {
                    elements.add(element.toString());
                    element = new StringBuilder();
                }
            }
        }
        
        for (int i = 0; i < elements.size() - 1; i++) {
            String e = elements.get(i);
            String e2 = elements.get(i + 1);
            
            if (e.equals("()")) {
                elements.remove(i);
                if (i > 0) {
                    elements.remove(i - 1);
                }
                i = -1;
                continue;
            }
            
            if ((e.length() == 1) && OPERATORS.contains(e.charAt(0)) && (e.charAt(0) == Operation.SUBTRACT.getSymbol())) {
                if (i == 0) {
                    elements.set(i, "(0-" + e2 + ')');
                    elements.remove(i + 1);
                    i = -1;
                    continue;
                } else {
                    String e3 = elements.get(i - 1);
                    if (OPERATORS.contains(e3.charAt(0))) {
                        elements.set(i, "(0-" + e2 + ')');
                        elements.remove(i + 1);
                        i = -1;
                        continue;
                    }
                }
            }
            
            if ((e.length() == 1) && OPERATORS.contains(e.charAt(0)) && (e.charAt(0) == Operation.ADD.getSymbol())) {
                if (i == 0) {
                    elements.remove(i);
                    i = -1;
                    continue;
                } else {
                    String e3 = elements.get(i - 1);
                    if (OPERATORS.contains(e3.charAt(0))) {
                        elements.remove(i);
                        i = -1;
                        continue;
                    }
                }
            }
            
            if ((ELEMENT_PATTERN.matcher(e).matches() || e.startsWith("(")) && e2.startsWith("(")) {
                elements.set(i, '(' + e + '*' + e2 + ')');
                elements.remove(i + 1);
                i = -1;
                continue;
            }
        }
        
        for (int i = 0; i < elements.size() - 1; i++) {
            String e = elements.get(i);
            String e2 = elements.get(i + 1);
            
            if ((e.length() == 1) && OPERATORS.contains(e.charAt(0)) && (e2.length() == 1) && OPERATORS.contains(e2.charAt(0)) && (e2.charAt(0) != Operation.SUBTRACT.getSymbol())) {
                throw new ParseException("Stacked operators in equation", i);
            }
        }
        
        return elements;
    }
    
    /**
     * Ingests the tokens parsed from the mathematical equation into a series of operations representing the mathematical equation.
     *
     * @param elements The tokens that were parsed from the mathematical equation.
     * @return A series of operations representing the mathematical equation.
     * @throws ParseException When there is an error parsing the mathematical equation.
     */
    private static MathOperation ingestElements(List<String> elements) throws ParseException {
        List<MathOperand> operands = new ArrayList<>();
        int o = 0;
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            
            if (element.startsWith("(")) {
                List<String> subElements;
                subElements = parseElements(element);
                MathOperation operation = ingestElements(subElements);
                MathOperand operand = new MathOperand();
                operand.op = operation;
                operands.add(operand);
                elements.set(i, String.valueOf(o++));
                
            } else if ((element.length() != 1) || !OPERATORS.contains(element.charAt(0))) {
                MathOperand operand = new MathOperand();
                try {
                    Number isNumber = NumberNameUtility.numberValueOf(element);
                    operand.n = element;
                } catch (Exception ignored) {
                    if (VARIABLE_PATTERN.matcher(element).matches()) {
                        operand.var = element;
                    } else {
                        throw new ParseException("Error parsing operand: " + element, o);
                    }
                }
                operands.add(operand);
                elements.set(i, String.valueOf(o++));
            }
        }
        
        if (operands.size() < 2) {
            MathOperation operation = new MathOperation();
            operation.operand1 = operands.get(Integer.parseInt(elements.get(0)));
            operation.operand2 = new MathOperand();
            operation.operand2.n = "0";
            operation.operation = Operation.ADD;
            
            MathOperand op = new MathOperand();
            op.op = operation;
            operands.set(0, op);
            o = 1;
            
        } else {
            for (OrderOfOperations oop : OrderOfOperations.values()) {
                if (elements.size() == 1) {
                    break;
                }
                
                for (int i = 0; i < elements.size(); i++) {
                    String element = elements.get(i);
                    if ((element.length() == 1) && OPERATORS.contains(element.charAt(0))) {
                        if (i == 0) {
                            throw new ParseException("Invalid symbol at start of equation", i);
                        } else if (i == elements.size() - 1) {
                            throw new ParseException("Invalid symbol at end of equation", i);
                        } else {
                            if (oop.getSymbols().contains(element)) {
                                MathOperation operation = new MathOperation();
                                operation.operand1 = operands.get(Integer.parseInt(elements.get(i - 1)));
                                operation.operand2 = operands.get(Integer.parseInt(elements.get(i + 1)));
                                operation.operation = Operation.getOperation(element.charAt(0));
                                
                                MathOperand operand = new MathOperand();
                                operand.op = operation;
                                
                                operands.add(operand);
                                elements.set(i - 1, String.valueOf(o++));
                                elements.remove(i);
                                elements.remove(i);
                                i = -1;
                            }
                        }
                    }
                }
            }
        }
        
        return operands.get(o - 1).op;
    }
    
    /**
     * Converts the equation string into a mathematical equation.
     *
     * @param equation The equation string.
     * @return A mathematical equation.
     */
    public static String cleanEquation(String equation) {
        equation = equation.replaceAll("(?i)\\sthe\\s", " ");
        equation = equation.replaceAll("(?i)((?:to|of|point)\\s[^\\s]+(?=[^\\s]{2}))(?:st|nd|rd|th)(?=\\s|$)", "$1");
        equation = equation.replaceAll("(?i)(?:st|nd|rd|th)\\spow", " pow");
        equation = equation.replaceAll("(?i)(?:st|nd|rd|th)\\sroot", " root");
        equation = equation.replaceAll("(?i)(?:st|nd|rd|th)\\s?\\^", " ^");
        equation = equation.replaceAll("(?i)(?:st|nd|rd|th)\\s?~", " ~");
        
        equation = equation.replaceAll("(?i)(?:open|begin)\\sparen(?:thesis)?", "(");
        equation = equation.replaceAll("(?i)(?:close|end)\\sparen(?:thesis)?", ")");
        
        equation = equation.replaceAll("(?i)plus|add|sum", "+");
        equation = equation.replaceAll("(?i)minus|sub(tract)?|less", "-");
        equation = equation.replaceAll("(?i)(?:times|mult(iply)?)(?:\\sby)?", "*");
        equation = equation.replaceAll("(?i)div(?:ided?)?(?:\\sby)?", "/");
        equation = equation.replaceAll("(?i)mod(?:ulus|ulo|ular)?", "%");
        
        equation = equation.replaceAll("(?i)squared?\\sroot(?:\\sof)?", "2 ~");
        equation = equation.replaceAll("(?i)cubed?\\sroot(?:\\sof)?", "3 ~");
        equation = equation.replaceAll("(?i)root(?:\\sof)?", "~");
        
        equation = equation.replaceAll("(?i)(?:(?:raised)?\\sto\\s(?:pow(?:er)?(?:\\sof)?)?)|(?:\\sto\\spow(?:er)?(?:\\sof)?)", "^");
        equation = equation.replaceAll("(?i)pow(?:er)?(?:\\sof)?", "`");
        
        equation = equation.replaceAll("(?i)squared", "^ 2");
        equation = equation.replaceAll("(?i)cubed", "^ 3");
        
        equation = equation.replaceAll("(?i)(?<=[\\d\\s])E-(?=[\\d\\s])", "*10^-");
        equation = equation.replaceAll("(?i)(?<=[\\d\\s])E\\+?(?=[\\d\\s])", "*10^");
        
        List<String> equationParts = new ArrayList<>();
        StringBuilder equationPartBuilder = new StringBuilder();
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (StringUtility.containsChar(SYMBOLS, c)) {
                if (!StringUtility.trim(equationPartBuilder.toString()).isEmpty()) {
                    equationParts.add(StringUtility.trim(equationPartBuilder.toString()));
                    equationPartBuilder = new StringBuilder();
                }
                equationParts.add(String.valueOf(c));
            } else {
                equationPartBuilder.append(c);
            }
        }
        if (!StringUtility.trim(equationPartBuilder.toString()).isEmpty()) {
            equationParts.add(StringUtility.trim(equationPartBuilder.toString()));
        }
        
        List<String> finalEquationParts = new ArrayList<>();
        for (String equationPart : equationParts) {
            if (SYMBOLS.contains(equationPart)) {
                finalEquationParts.add(equationPart);
                continue;
            }
            
            try {
                String number = NumberNameUtility.numberPhraseToNumberString(equationPart);
                finalEquationParts.add(number);
            } catch (NumberFormatException e) {
                finalEquationParts.add(equationPart);
            }
        }
        
        for (int i = 0; i < finalEquationParts.size() - 2; i++) {
            String first = finalEquationParts.get(i);
            String second = finalEquationParts.get(i + 1);
            String third = finalEquationParts.get(i + 2);
            
            if (second.equals("`") && NUMBER_PATTERN.matcher(first).matches()) {
                if (NUMBER_PATTERN.matcher(third).matches()) {
                    finalEquationParts.set(i, third);
                    finalEquationParts.set(i + 1, "^");
                    finalEquationParts.set(i + 2, first);
                } else {
                    finalEquationParts.remove(i + 1);
                    i--;
                }
            }
        }
        if (finalEquationParts.get(finalEquationParts.size() - 1).equals("`")) {
            finalEquationParts.remove(finalEquationParts.size() - 1);
        }
        
        StringBuilder finalEquation = new StringBuilder();
        for (String finalEquationPart : finalEquationParts) {
            finalEquation.append(finalEquationPart);
        }
        equation = StringUtility.removeWhiteSpace(finalEquation.toString());
        
        equation = equation.replaceAll("(?i)(?<=[\\d\\s])E-(?=[\\d\\s])", "*10^-");
        equation = equation.replaceAll("(?i)(?<=[\\d\\s])E\\+?(?=[\\d\\s])", "*10^");
        
        return equation;
    }
    
    
    //Inner Classes
    
    /**
     * An operand in a MathOperand.
     */
    public static class MathOperand {
        
        //Fields
        
        /**
         * The number string representing the operand, if it is a number.
         */
        public String n;
        
        /**
         * The variable representing the operand, if it is a variable.
         */
        public String var;
        
        /**
         * The MathOperation representing the operand, if it is a MathOperation.
         */
        public MathOperation op;
        
        
        //Methods
        
        /**
         * Provides a string representing the MathOperand.
         *
         * @return A string representing the MathOperand.
         */
        @Override
        public String toString() {
            return (n != null) ? n : (var != null) ? var : "(" + op + ')';
        }
        
    }
    
    /**
     * An operation in a mathematical equation.
     */
    public static class MathOperation {
        
        //Fields
        
        /**
         * The first operand.
         */
        public MathOperand operand1;
        
        /**
         * The second operand.
         */
        public MathOperand operand2;
        
        /**
         * The operation to be applied on the two operands.
         */
        public Operation operation;
        
        
        //Methods
        
        /**
         * Provides a string representing the MathOperation.
         *
         * @return A string representing the MathOperation.
         */
        @Override
        public String toString() {
            return operand1 + String.valueOf(operation.getSymbol()) + operand2;
        }
        
        /**
         * Evaluates the MathOperation.
         *
         * @param vars A map of variable values to be used in the equation, if needed.
         * @return The result of the evaluation.
         */
        public Number evaluate(Map<String, Number> vars) {
            String n1;
            String n2;
            
            if (operand1.n != null) {
                n1 = operand1.n;
            } else if (operand1.var != null) {
                if (vars.containsKey(operand1.var)) {
                    n1 = vars.get(operand1.var).toString();
                } else {
                    logger.trace("Variable: {} was not found in the provided var map", operand1.var);
                    return null;
                }
            } else if (operand1.op != null) {
                n1 = operand1.op.evaluate(vars).toString();
            } else {
                logger.trace("The MathOperation is not valid");
                return null;
            }
            
            if (operand2.n != null) {
                n2 = operand2.n;
            } else if (operand2.var != null) {
                if (vars.containsKey(operand2.var)) {
                    n2 = vars.get(operand2.var).toString();
                } else {
                    logger.trace("Variable: {} was not found in the provided var map", operand2.var);
                    return null;
                }
            } else if (operand2.op != null) {
                n2 = operand2.op.evaluate(vars).toString();
            } else {
                logger.trace("The MathOperation is not valid");
                return null;
            }
            
            Number result;
            switch (operation) {
                case POWER:
                    result = NumberNameUtility.numberValueOf(StringMathUtility.power(n1, n2));
                    break;
                case ROOT:
                    result = NumberNameUtility.numberValueOf(StringMathUtility.root(n1, n2));
                    break;
                case MULTIPLY:
                    result = NumberNameUtility.numberValueOf(StringMathUtility.multiply(n1, n2));
                    break;
                case DIVIDE:
                    result = NumberNameUtility.numberValueOf(StringMathUtility.divide(n1, n2));
                    break;
                case MODULUS:
                    result = NumberNameUtility.numberValueOf(StringMathUtility.mod(n1, n2));
                    break;
                case ADD:
                    result = NumberNameUtility.numberValueOf(StringMathUtility.add(n1, n2));
                    break;
                case SUBTRACT:
                    result = NumberNameUtility.numberValueOf(StringMathUtility.subtract(n1, n2));
                    break;
                default:
                    result = null;
            }
            
            return result;
        }
        
        /**
         * Evaluates the MathOperation.
         *
         * @return The result of the evaluation.
         * @see #evaluate(Map)
         */
        public Number evaluate() {
            return evaluate(new HashMap<>());
        }
        
    }
    
}
