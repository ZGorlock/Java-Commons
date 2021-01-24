/*
 * File:    ConsoleTest.java
 * Package: commons.console
 * Author:  Zachary Gill
 */

package commons.console;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Console.
 *
 * @see Console
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({Console.class})
public class ConsoleTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ConsoleTest.class);
    
    
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
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of ConsoleEffect.
     *
     * @throws Exception When there is an exception.
     * @see Console.ConsoleEffect
     */
    @Test
    public void testConsoleEffect() throws Exception {
        Assert.assertEquals(46, Console.ConsoleEffect.values().length);
        Assert.assertEquals(Console.ConsoleEffect.RESET, Console.ConsoleEffect.values()[0]);
        Assert.assertEquals(Console.ConsoleEffect.BOLD, Console.ConsoleEffect.values()[1]);
        Assert.assertEquals(Console.ConsoleEffect.FAINT, Console.ConsoleEffect.values()[2]);
        Assert.assertEquals(Console.ConsoleEffect.ITALIC, Console.ConsoleEffect.values()[3]);
        Assert.assertEquals(Console.ConsoleEffect.UNDERLINE, Console.ConsoleEffect.values()[4]);
        Assert.assertEquals(Console.ConsoleEffect.UNDERLINE_OFF, Console.ConsoleEffect.values()[5]);
        Assert.assertEquals(Console.ConsoleEffect.BLINK_SLOW, Console.ConsoleEffect.values()[6]);
        Assert.assertEquals(Console.ConsoleEffect.BLINK_FAST, Console.ConsoleEffect.values()[7]);
        Assert.assertEquals(Console.ConsoleEffect.REVERSE, Console.ConsoleEffect.values()[8]);
        Assert.assertEquals(Console.ConsoleEffect.REVERSE_OFF, Console.ConsoleEffect.values()[9]);
        Assert.assertEquals(Console.ConsoleEffect.CONCEAL, Console.ConsoleEffect.values()[10]);
        Assert.assertEquals(Console.ConsoleEffect.STRIKE_THROUGH, Console.ConsoleEffect.values()[11]);
        Assert.assertEquals(Console.ConsoleEffect.STRIKE_THROUGH_OFF, Console.ConsoleEffect.values()[12]);
        Assert.assertEquals(Console.ConsoleEffect.DEFAULT_COLOR, Console.ConsoleEffect.values()[13]);
        Assert.assertEquals(Console.ConsoleEffect.BLACK, Console.ConsoleEffect.values()[14]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_RED, Console.ConsoleEffect.values()[15]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_GREEN, Console.ConsoleEffect.values()[16]);
        Assert.assertEquals(Console.ConsoleEffect.ORANGE, Console.ConsoleEffect.values()[17]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_BLUE, Console.ConsoleEffect.values()[18]);
        Assert.assertEquals(Console.ConsoleEffect.PURPLE, Console.ConsoleEffect.values()[19]);
        Assert.assertEquals(Console.ConsoleEffect.LIGHT_BLUE, Console.ConsoleEffect.values()[20]);
        Assert.assertEquals(Console.ConsoleEffect.GREY, Console.ConsoleEffect.values()[21]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_GREY, Console.ConsoleEffect.values()[22]);
        Assert.assertEquals(Console.ConsoleEffect.RED, Console.ConsoleEffect.values()[23]);
        Assert.assertEquals(Console.ConsoleEffect.GREEN, Console.ConsoleEffect.values()[24]);
        Assert.assertEquals(Console.ConsoleEffect.YELLOW, Console.ConsoleEffect.values()[25]);
        Assert.assertEquals(Console.ConsoleEffect.BLUE, Console.ConsoleEffect.values()[26]);
        Assert.assertEquals(Console.ConsoleEffect.MAGENTA, Console.ConsoleEffect.values()[27]);
        Assert.assertEquals(Console.ConsoleEffect.CYAN, Console.ConsoleEffect.values()[28]);
        Assert.assertEquals(Console.ConsoleEffect.WHITE, Console.ConsoleEffect.values()[29]);
        Assert.assertEquals(Console.ConsoleEffect.BLACK_BG, Console.ConsoleEffect.values()[30]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_RED_BG, Console.ConsoleEffect.values()[31]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_GREEN_BG, Console.ConsoleEffect.values()[32]);
        Assert.assertEquals(Console.ConsoleEffect.ORANGE_BG, Console.ConsoleEffect.values()[33]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_BLUE_BG, Console.ConsoleEffect.values()[34]);
        Assert.assertEquals(Console.ConsoleEffect.PURPLE_BG, Console.ConsoleEffect.values()[35]);
        Assert.assertEquals(Console.ConsoleEffect.LIGHT_BG, Console.ConsoleEffect.values()[36]);
        Assert.assertEquals(Console.ConsoleEffect.GREY_BG, Console.ConsoleEffect.values()[37]);
        Assert.assertEquals(Console.ConsoleEffect.DARK_GREY_BG, Console.ConsoleEffect.values()[38]);
        Assert.assertEquals(Console.ConsoleEffect.RED_BG, Console.ConsoleEffect.values()[39]);
        Assert.assertEquals(Console.ConsoleEffect.GREEN_BG, Console.ConsoleEffect.values()[40]);
        Assert.assertEquals(Console.ConsoleEffect.YELLOW_BG, Console.ConsoleEffect.values()[41]);
        Assert.assertEquals(Console.ConsoleEffect.BLUE_BG, Console.ConsoleEffect.values()[42]);
        Assert.assertEquals(Console.ConsoleEffect.MAGENTA_BG, Console.ConsoleEffect.values()[43]);
        Assert.assertEquals(Console.ConsoleEffect.CYAN_BG, Console.ConsoleEffect.values()[44]);
        Assert.assertEquals(Console.ConsoleEffect.WHITE_BG, Console.ConsoleEffect.values()[45]);
        
        //getCode
        Assert.assertEquals(0, Console.ConsoleEffect.RESET.getCode());
        Assert.assertEquals(1, Console.ConsoleEffect.BOLD.getCode());
        Assert.assertEquals(2, Console.ConsoleEffect.FAINT.getCode());
        Assert.assertEquals(3, Console.ConsoleEffect.ITALIC.getCode());
        Assert.assertEquals(4, Console.ConsoleEffect.UNDERLINE.getCode());
        Assert.assertEquals(24, Console.ConsoleEffect.UNDERLINE_OFF.getCode());
        Assert.assertEquals(5, Console.ConsoleEffect.BLINK_SLOW.getCode());
        Assert.assertEquals(6, Console.ConsoleEffect.BLINK_FAST.getCode());
        Assert.assertEquals(7, Console.ConsoleEffect.REVERSE.getCode());
        Assert.assertEquals(27, Console.ConsoleEffect.REVERSE_OFF.getCode());
        Assert.assertEquals(8, Console.ConsoleEffect.CONCEAL.getCode());
        Assert.assertEquals(9, Console.ConsoleEffect.STRIKE_THROUGH.getCode());
        Assert.assertEquals(29, Console.ConsoleEffect.STRIKE_THROUGH_OFF.getCode());
        Assert.assertEquals(39, Console.ConsoleEffect.DEFAULT_COLOR.getCode());
        Assert.assertEquals(30, Console.ConsoleEffect.BLACK.getCode());
        Assert.assertEquals(31, Console.ConsoleEffect.DARK_RED.getCode());
        Assert.assertEquals(32, Console.ConsoleEffect.DARK_GREEN.getCode());
        Assert.assertEquals(33, Console.ConsoleEffect.ORANGE.getCode());
        Assert.assertEquals(34, Console.ConsoleEffect.DARK_BLUE.getCode());
        Assert.assertEquals(35, Console.ConsoleEffect.PURPLE.getCode());
        Assert.assertEquals(36, Console.ConsoleEffect.LIGHT_BLUE.getCode());
        Assert.assertEquals(37, Console.ConsoleEffect.GREY.getCode());
        Assert.assertEquals(90, Console.ConsoleEffect.DARK_GREY.getCode());
        Assert.assertEquals(91, Console.ConsoleEffect.RED.getCode());
        Assert.assertEquals(92, Console.ConsoleEffect.GREEN.getCode());
        Assert.assertEquals(93, Console.ConsoleEffect.YELLOW.getCode());
        Assert.assertEquals(94, Console.ConsoleEffect.BLUE.getCode());
        Assert.assertEquals(95, Console.ConsoleEffect.MAGENTA.getCode());
        Assert.assertEquals(96, Console.ConsoleEffect.CYAN.getCode());
        Assert.assertEquals(97, Console.ConsoleEffect.WHITE.getCode());
        Assert.assertEquals(40, Console.ConsoleEffect.BLACK_BG.getCode());
        Assert.assertEquals(41, Console.ConsoleEffect.DARK_RED_BG.getCode());
        Assert.assertEquals(42, Console.ConsoleEffect.DARK_GREEN_BG.getCode());
        Assert.assertEquals(43, Console.ConsoleEffect.ORANGE_BG.getCode());
        Assert.assertEquals(44, Console.ConsoleEffect.DARK_BLUE_BG.getCode());
        Assert.assertEquals(45, Console.ConsoleEffect.PURPLE_BG.getCode());
        Assert.assertEquals(46, Console.ConsoleEffect.LIGHT_BG.getCode());
        Assert.assertEquals(47, Console.ConsoleEffect.GREY_BG.getCode());
        Assert.assertEquals(100, Console.ConsoleEffect.DARK_GREY_BG.getCode());
        Assert.assertEquals(101, Console.ConsoleEffect.RED_BG.getCode());
        Assert.assertEquals(102, Console.ConsoleEffect.GREEN_BG.getCode());
        Assert.assertEquals(103, Console.ConsoleEffect.YELLOW_BG.getCode());
        Assert.assertEquals(104, Console.ConsoleEffect.BLUE_BG.getCode());
        Assert.assertEquals(105, Console.ConsoleEffect.MAGENTA_BG.getCode());
        Assert.assertEquals(106, Console.ConsoleEffect.CYAN_BG.getCode());
        Assert.assertEquals(107, Console.ConsoleEffect.WHITE_BG.getCode());
        
        //getKey
        Assert.assertEquals("\u001B[0m", Console.ConsoleEffect.RESET.getKey());
        Assert.assertEquals("\u001B[1m", Console.ConsoleEffect.BOLD.getKey());
        Assert.assertEquals("\u001B[2m", Console.ConsoleEffect.FAINT.getKey());
        Assert.assertEquals("\u001B[3m", Console.ConsoleEffect.ITALIC.getKey());
        Assert.assertEquals("\u001B[4m", Console.ConsoleEffect.UNDERLINE.getKey());
        Assert.assertEquals("\u001B[24m", Console.ConsoleEffect.UNDERLINE_OFF.getKey());
        Assert.assertEquals("\u001B[5m", Console.ConsoleEffect.BLINK_SLOW.getKey());
        Assert.assertEquals("\u001B[6m", Console.ConsoleEffect.BLINK_FAST.getKey());
        Assert.assertEquals("\u001B[7m", Console.ConsoleEffect.REVERSE.getKey());
        Assert.assertEquals("\u001B[27m", Console.ConsoleEffect.REVERSE_OFF.getKey());
        Assert.assertEquals("\u001B[8m", Console.ConsoleEffect.CONCEAL.getKey());
        Assert.assertEquals("\u001B[9m", Console.ConsoleEffect.STRIKE_THROUGH.getKey());
        Assert.assertEquals("\u001B[29m", Console.ConsoleEffect.STRIKE_THROUGH_OFF.getKey());
        Assert.assertEquals("\u001B[39m", Console.ConsoleEffect.DEFAULT_COLOR.getKey());
        Assert.assertEquals("\u001B[30m", Console.ConsoleEffect.BLACK.getKey());
        Assert.assertEquals("\u001B[31m", Console.ConsoleEffect.DARK_RED.getKey());
        Assert.assertEquals("\u001B[32m", Console.ConsoleEffect.DARK_GREEN.getKey());
        Assert.assertEquals("\u001B[33m", Console.ConsoleEffect.ORANGE.getKey());
        Assert.assertEquals("\u001B[34m", Console.ConsoleEffect.DARK_BLUE.getKey());
        Assert.assertEquals("\u001B[35m", Console.ConsoleEffect.PURPLE.getKey());
        Assert.assertEquals("\u001B[36m", Console.ConsoleEffect.LIGHT_BLUE.getKey());
        Assert.assertEquals("\u001B[37m", Console.ConsoleEffect.GREY.getKey());
        Assert.assertEquals("\u001B[90m", Console.ConsoleEffect.DARK_GREY.getKey());
        Assert.assertEquals("\u001B[91m", Console.ConsoleEffect.RED.getKey());
        Assert.assertEquals("\u001B[92m", Console.ConsoleEffect.GREEN.getKey());
        Assert.assertEquals("\u001B[93m", Console.ConsoleEffect.YELLOW.getKey());
        Assert.assertEquals("\u001B[94m", Console.ConsoleEffect.BLUE.getKey());
        Assert.assertEquals("\u001B[95m", Console.ConsoleEffect.MAGENTA.getKey());
        Assert.assertEquals("\u001B[96m", Console.ConsoleEffect.CYAN.getKey());
        Assert.assertEquals("\u001B[97m", Console.ConsoleEffect.WHITE.getKey());
        Assert.assertEquals("\u001B[40m", Console.ConsoleEffect.BLACK_BG.getKey());
        Assert.assertEquals("\u001B[41m", Console.ConsoleEffect.DARK_RED_BG.getKey());
        Assert.assertEquals("\u001B[42m", Console.ConsoleEffect.DARK_GREEN_BG.getKey());
        Assert.assertEquals("\u001B[43m", Console.ConsoleEffect.ORANGE_BG.getKey());
        Assert.assertEquals("\u001B[44m", Console.ConsoleEffect.DARK_BLUE_BG.getKey());
        Assert.assertEquals("\u001B[45m", Console.ConsoleEffect.PURPLE_BG.getKey());
        Assert.assertEquals("\u001B[46m", Console.ConsoleEffect.LIGHT_BG.getKey());
        Assert.assertEquals("\u001B[47m", Console.ConsoleEffect.GREY_BG.getKey());
        Assert.assertEquals("\u001B[100m", Console.ConsoleEffect.DARK_GREY_BG.getKey());
        Assert.assertEquals("\u001B[101m", Console.ConsoleEffect.RED_BG.getKey());
        Assert.assertEquals("\u001B[102m", Console.ConsoleEffect.GREEN_BG.getKey());
        Assert.assertEquals("\u001B[103m", Console.ConsoleEffect.YELLOW_BG.getKey());
        Assert.assertEquals("\u001B[104m", Console.ConsoleEffect.BLUE_BG.getKey());
        Assert.assertEquals("\u001B[105m", Console.ConsoleEffect.MAGENTA_BG.getKey());
        Assert.assertEquals("\u001B[106m", Console.ConsoleEffect.CYAN_BG.getKey());
        Assert.assertEquals("\u001B[107m", Console.ConsoleEffect.WHITE_BG.getKey());
        
        //apply
        for (Console.ConsoleEffect consoleEffect : Console.ConsoleEffect.values()) {
            PowerMockito.mockStatic(Console.class);
            String test = "test";
            consoleEffect.apply(test);
            PowerMockito.verifyStatic(Console.class);
            Console.stringEffect(ArgumentMatchers.eq(test), ArgumentMatchers.eq(consoleEffect));
        }
    }
    
    /**
     * JUnit test of stringEffect.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffect(String, commons.console.Console.ConsoleEffect)
     */
    @Test
    public void testStringEffect() throws Exception {
        //standard
        String test = "test";
        for (Console.ConsoleEffect consoleEffect : Console.ConsoleEffect.values()) {
            Assert.assertEquals(
                    consoleEffect.getKey() + test + Console.ConsoleEffect.RESET.getKey(),
                    Console.stringEffect(test, consoleEffect)
            );
        }
    
        //empty
        Assert.assertEquals(
                Console.ConsoleEffect.RED.getKey() + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffect("", Console.ConsoleEffect.RED)
        );
        Assert.assertEquals(
                Console.ConsoleEffect.RED.getKey() + "null" + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffect(null, Console.ConsoleEffect.RED)
        );
    }
    
    /**
     * JUnit test of stringEffects.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffects(String, Console.ConsoleEffect...)
     */
    @Test
    public void testStringEffects() throws Exception {
        //standard
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.PURPLE.getCode() + ";" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + "mtest" + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffects("test", Console.ConsoleEffect.PURPLE, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.PURPLE.getCode() + ";" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + "mtest" + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffects("test", Console.ConsoleEffect.PURPLE, Console.ConsoleEffect.RESET, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
    
        //single
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.PURPLE.getCode() + "mtest" + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffects("test", Console.ConsoleEffect.PURPLE)
        );
    
        //empty
        Assert.assertEquals(
                "test" + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffects("test")
        );
    }
    
    /**
     * JUnit test of color.
     *
     * @throws Exception When there is an exception.
     * @see Console#color(String, Console.ConsoleEffect)
     */
    @Test
    public void testColor() throws Exception {
        PowerMockito.mockStatic(Console.class);
        PowerMockito.when(Console.class, "color", ArgumentMatchers.anyString(), ArgumentMatchers.any(Console.ConsoleEffect.class)).thenCallRealMethod();
        String test = "test";
        Console.color(test, Console.ConsoleEffect.GREEN);
        PowerMockito.verifyStatic(Console.class);
        Console.stringEffects(ArgumentMatchers.eq(test), ArgumentMatchers.eq(Console.ConsoleEffect.GREEN));
    }
    
    /**
     * JUnit test of colorAndBackground.
     *
     * @throws Exception When there is an exception.
     * @see Console#colorAndBackground(String, Console.ConsoleEffect, Console.ConsoleEffect)
     */
    @Test
    public void testColorAndBackground() throws Exception {
        PowerMockito.mockStatic(Console.class);
        PowerMockito.when(Console.class, "colorAndBackground", ArgumentMatchers.anyString(), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class)).thenCallRealMethod();
        String test = "test";
        Console.colorAndBackground(test, Console.ConsoleEffect.GREEN, Console.ConsoleEffect.RED_BG);
        PowerMockito.verifyStatic(Console.class);
        Console.stringEffects(ArgumentMatchers.eq(test), ArgumentMatchers.eq(Console.ConsoleEffect.GREEN), ArgumentMatchers.eq(Console.ConsoleEffect.RED_BG));
    }
    
    /**
     * JUnit test of color8Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#color8Bit(String, int)
     */
    @Test
    public void testColor8Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[38;5;88m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.color8Bit(test, 88)
        );
        Assert.assertEquals(
                "\u001B[38;5;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.color8Bit(test, 0)
        );
        Assert.assertEquals(
                "\u001B[38;5;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.color8Bit(test, 255)
        );
    
        //invalid
        Assert.assertEquals(test, Console.color8Bit(test, -1));
        Assert.assertEquals(test, Console.color8Bit(test, 256));
    }
    
    /**
     * JUnit test of colorAndBackground8Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#colorAndBackground8Bit(String, int, int)
     */
    @Test
    public void testColorAndBackground8Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[38;5;88;48;5;122m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.colorAndBackground8Bit(test, 88, 122)
        );
        Assert.assertEquals(
                "\u001B[38;5;0;48;5;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.colorAndBackground8Bit(test, 0, 0)
        );
        Assert.assertEquals(
                "\u001B[38;5;255;48;5;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.colorAndBackground8Bit(test, 255, 255)
        );
    
        //invalid
        Assert.assertEquals(test, Console.colorAndBackground8Bit(test, -1, 128));
        Assert.assertEquals(test, Console.colorAndBackground8Bit(test, 256, 128));
        Assert.assertEquals(test, Console.colorAndBackground8Bit(test, 128, -1));
        Assert.assertEquals(test, Console.colorAndBackground8Bit(test, 128, 256));
    }
    
    /**
     * JUnit test of color24Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#color24Bit(String, int, int, int)
     */
    @Test
    public void testColor24Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[38;2;88;122;34m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.color24Bit(test, 88, 122, 34)
        );
        Assert.assertEquals(
                "\u001B[38;2;0;0;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.color24Bit(test, 0, 0, 0)
        );
        Assert.assertEquals(
                "\u001B[38;2;255;255;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.color24Bit(test, 255, 255, 255)
        );
    
        //invalid
        Assert.assertEquals(test, Console.color24Bit(test, -1, 128, 128));
        Assert.assertEquals(test, Console.color24Bit(test, 256, 128, 128));
        Assert.assertEquals(test, Console.color24Bit(test, 128, -1, 128));
        Assert.assertEquals(test, Console.color24Bit(test, 128, 256, 128));
        Assert.assertEquals(test, Console.color24Bit(test, 128, 128, -1));
        Assert.assertEquals(test, Console.color24Bit(test, 128, 128, 256));
    }
    
    /**
     * JUnit test of colorAndBackground24Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#colorAndBackground24Bit(String, int, int, int, int, int, int)
     */
    @Test
    public void testColorAndBackground24Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[38;2;88;122;34;48;2;8;203;110m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.colorAndBackground24Bit(test, 88, 122, 34, 8, 203, 110)
        );
        Assert.assertEquals(
                "\u001B[38;2;0;0;0;48;2;0;0;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.colorAndBackground24Bit(test, 0, 0, 0, 0, 0, 0)
        );
        Assert.assertEquals(
                "\u001B[38;2;255;255;255;48;2;255;255;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.colorAndBackground24Bit(test, 255, 255, 255, 255, 255, 255)
        );
    
        //invalid
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, -1, 128, 128, 128, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 256, 128, 128, 128, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, -1, 128, 128, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 256, 128, 128, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, -1, 128, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, 256, 128, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, 128, -1, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, 128, 256, 128, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, 128, 128, -1, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, 128, 128, 256, 128));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, 128, 128, 128, -1));
        Assert.assertEquals(test, Console.colorAndBackground24Bit(test, 128, 128, 128, 128, 128, 256));
    }
    
    /**
     * JUnit test of stringEffectsWithColor.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffectsWithColor(String, Console.ConsoleEffect, Console.ConsoleEffect...)
     */
    @Test
    public void stringEffectsWithColor() throws Exception {
        PowerMockito.mockStatic(Console.class);
        PowerMockito.when(Console.class, "stringEffectsWithColor", ArgumentMatchers.anyString(), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class)).thenCallRealMethod();
        String test = "test";
        Console.stringEffectsWithColor(test, Console.ConsoleEffect.GREEN, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.ITALIC, Console.ConsoleEffect.REVERSE);
        PowerMockito.verifyStatic(Console.class);
        Console.stringEffects(ArgumentMatchers.eq(test), ArgumentMatchers.eq(Console.ConsoleEffect.BOLD), ArgumentMatchers.eq(Console.ConsoleEffect.ITALIC), ArgumentMatchers.eq(Console.ConsoleEffect.REVERSE), ArgumentMatchers.eq(Console.ConsoleEffect.GREEN));
    }
    
    /**
     * JUnit test of stringEffectsWithColorAndBackground.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffectsWithColorAndBackground(String, Console.ConsoleEffect, Console.ConsoleEffect, Console.ConsoleEffect...)
     */
    @Test
    public void stringEffectsWithColorAndBackground() throws Exception {
        PowerMockito.mockStatic(Console.class);
        PowerMockito.when(Console.class, "stringEffectsWithColorAndBackground", ArgumentMatchers.anyString(), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class), ArgumentMatchers.any(Console.ConsoleEffect.class)).thenCallRealMethod();
        String test = "test";
        Console.stringEffectsWithColorAndBackground(test, Console.ConsoleEffect.GREEN, Console.ConsoleEffect.RED_BG, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.ITALIC, Console.ConsoleEffect.REVERSE);
        PowerMockito.verifyStatic(Console.class);
        Console.stringEffects(ArgumentMatchers.eq(test), ArgumentMatchers.eq(Console.ConsoleEffect.BOLD), ArgumentMatchers.eq(Console.ConsoleEffect.ITALIC), ArgumentMatchers.eq(Console.ConsoleEffect.REVERSE), ArgumentMatchers.eq(Console.ConsoleEffect.GREEN), ArgumentMatchers.eq(Console.ConsoleEffect.RED_BG));
    }
    
    /**
     * JUnit test of stringEffectsWithColor8Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffectsWithColor8Bit(String, int, Console.ConsoleEffect...)
     */
    @Test
    public void testStringEffectWithColor8Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;5;88m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColor8Bit(test, 88, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;5;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColor8Bit(test, 0, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;5;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColor8Bit(test, 255, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
    
        //invalid
        Assert.assertEquals(test, Console.stringEffectsWithColor8Bit(test, -1, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColor8Bit(test, 256, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
    }
    
    /**
     * JUnit test of stringEffectsWithColorAndBackground8Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffectsWithColorAndBackground8Bit(String, int, int, Console.ConsoleEffect...)
     */
    @Test
    public void testStringEffectWithColorAndBackground8Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;5;88;48;5;122m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColorAndBackground8Bit(test, 88, 122, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;5;0;48;5;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColorAndBackground8Bit(test, 0, 0, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;5;255;48;5;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColorAndBackground8Bit(test, 255, 255, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
    
        //invalid
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground8Bit(test, -1, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground8Bit(test, 256, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground8Bit(test, 128, -1, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground8Bit(test, 128, 256, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
    }
    
    /**
     * JUnit test of stringEffectsWithColor24Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffectsWithColor24Bit(String, int, int, int, Console.ConsoleEffect...)
     */
    @Test
    public void testStringEffectsWithColor24Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;2;88;122;34m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColor24Bit(test, 88, 122, 34, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;2;0;0;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColor24Bit(test, 0, 0, 0, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;2;255;255;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColor24Bit(test, 255, 255, 255, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
    
        //invalid
        Assert.assertEquals(test, Console.stringEffectsWithColor24Bit(test, -1, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColor24Bit(test, 256, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColor24Bit(test, 128, -1, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColor24Bit(test, 128, 256, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColor24Bit(test, 128, 128, -1, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColor24Bit(test, 128, 128, 256, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
    }
    
    /**
     * JUnit test of stringEffectsWithColorAndBackground24Bit.
     *
     * @throws Exception When there is an exception.
     * @see Console#stringEffectsWithColorAndBackground24Bit(String, int, int, int, int, int, int, Console.ConsoleEffect...)
     */
    @Test
    public void testStringEffectsWithColorAndBackground24Bit() throws Exception {
        String test = "test";
    
        //standard
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;2;88;122;34;48;2;8;203;110m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColorAndBackground24Bit(test, 88, 122, 34, 8, 203, 110, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;2;0;0;0;48;2;0;0;0m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColorAndBackground24Bit(test, 0, 0, 0, 0, 0, 0, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
        Assert.assertEquals(
                "\u001B[" + Console.ConsoleEffect.BOLD.getCode() + ";" + Console.ConsoleEffect.UNDERLINE.getCode() + ";38;2;255;255;255;48;2;255;255;255m" + test + Console.ConsoleEffect.RESET.getKey(),
                Console.stringEffectsWithColorAndBackground24Bit(test, 255, 255, 255, 255, 255, 255, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE)
        );
    
        //invalid
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, -1, 128, 128, 128, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 256, 128, 128, 128, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, -1, 128, 128, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 256, 128, 128, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, -1, 128, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, 256, 128, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, 128, -1, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, 128, 256, 128, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, 128, 128, -1, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, 128, 128, 256, 128, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, 128, 128, 128, -1, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
        Assert.assertEquals(test, Console.stringEffectsWithColorAndBackground24Bit(test, 128, 128, 128, 128, 128, 256, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.UNDERLINE));
    }
    
}
