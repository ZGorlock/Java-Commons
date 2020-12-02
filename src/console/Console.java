/*
 * File:    Console.java
 * Package: console
 * Author:  Zachary Gill
 */

package console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines console effects.<br>
 * Support for these effects can not be guaranteed on all consoles.
 */
public final class Console {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Console.class);
    
    
    //Enums
    
    /**
     * An enumeration of Console Effects.
     */
    public enum ConsoleEffect {
        
        //Values
        
        BOLD(1),
        FAINT(2),
        ITALIC(3),
        UNDERLINE(4),
        BLINK_SLOW(5),
        BLINK_FAST(6),
        INVERT(7),
        CONCEAL(8),
        STRIKE_THROUGH(9),
        
        BRIGHT_WHITE(97),
        WHITE(37),
        RED(31),
        ORANGE(33),
        PINK(91),
        YELLOW(93),
        GREEN(92),
        DARK_GREEN(32),
        CYAN(36),
        LIGHT_BLUE(96),
        BLUE(94),
        DARK_BLUE(34),
        MAGENTA(95),
        PURPLE(35),
        GREY(90),
        BLACK(30),
        
        BRIGHT_WHITE_BG(107),
        WHITE_BG(47),
        RED_BG(41),
        ORANGE_BG(43),
        PINK_BG(101),
        YELLOW_BG(103),
        GREEN_BG(102),
        DARK_GREEN_BG(42),
        CYAN_BG(46),
        LIGHT_BLUE_BG(106),
        BLUE_BG(104),
        DARK_BLUE_BG(44),
        MAGENTA_BG(105),
        PURPLE_BG(45),
        GREY_BG(100),
        BLACK_BG(40);
        
        
        //Fields
        
        /**
         * The code of the Console Effect.
         */
        private int code;
        
        
        //Constructors
        
        /**
         * The constructor for a Console Effect enumeration.
         *
         * @param code The code of the Console Effect.
         */
        ConsoleEffect(int code) {
            this.code = code;
        }
        
        
        //Getters
        
        /**
         * Returns the code of the Console Effect.
         *
         * @return The code of the Console Effect.
         */
        public int getCode() {
            return code;
        }
        
    }
    
    
    //Constants
    
    /**
     * The code to reset the console.
     */
    public static final String RESET = "\u001B[0m";
    
    /**
     * The console bold effect.
     */
    public static final String BOLD = "\u001B[1m";
    
    /**
     * The console faint effect.
     */
    public static final String FAINT = "\u001B[2m";
    
    /**
     * The console italic effect.
     */
    public static final String ITALIC = "\u001B[3m";
    
    /**
     * The console underline effect.
     */
    public static final String UNDERLINE = "\u001B[4m";
    
    /**
     * The console slow blink effect.
     */
    public static final String BLINK_SLOW = "\u001B[5m";
    
    /**
     * The console fast blink effect.
     */
    public static final String BLINK_FAST = "\u001B[6m";
    
    /**
     * The console invert effect.
     */
    public static final String INVERT = "\u001B[7m";
    
    /**
     * The console conceal effect.
     */
    public static final String CONCEAL = "\u001B[8m";
    
    /**
     * The console strike through effect.
     */
    public static final String STRIKE_THROUGH = "\u001B[9m";
    
    /**
     * The console bright white color.
     */
    public static final String BRIGHT_WHITE = "\u001B[97m";
    
    /**
     * The console white color.
     */
    public static final String WHITE = "\u001B[37m";
    
    /**
     * The console red color.
     */
    public static final String RED = "\u001B[31m";
    
    /**
     * The console orange color.
     */
    public static final String ORANGE = "\u001B[33m";
    
    /**
     * The console pink color.
     */
    public static final String PINK = "\u001B[91m";
    
    /**
     * The console yellow color.
     */
    public static final String YELLOW = "\u001B[93m";
    
    /**
     * The console green color.
     */
    public static final String GREEN = "\u001B[92m";
    
    /**
     * The console dark green color.
     */
    public static final String DARK_GREEN = "\u001B[32m";
    
    /**
     * The console cyan color.
     */
    public static final String CYAN = "\u001B[36m";
    
    /**
     * The console light blue color.
     */
    public static final String LIGHT_BLUE = "\u001B[96m";
    
    /**
     * The console blue color.
     */
    public static final String BLUE = "\u001B[94m";
    
    /**
     * The console dark blue color.
     */
    public static final String DARK_BLUE = "\u001B[34m";
    
    /**
     * The console magenta color.
     */
    public static final String MAGENTA = "\u001B[95m";
    
    /**
     * The console purple color.
     */
    public static final String PURPLE = "\u001B[35m";
    
    /**
     * The console grey color.
     */
    public static final String GREY = "\u001B[90m";
    
    /**
     * The console black color.
     */
    public static final String BLACK = "\u001B[30m";
    
    /**
     * The console bright white background color.
     */
    public static final String BRIGHT_WHITE_BG = "\u001B[107m";
    
    /**
     * The console white background color.
     */
    public static final String WHITE_BG = "\u001B[47m";
    
    /**
     * The console red background color.
     */
    public static final String RED_BG = "\u001B[101m";
    
    /**
     * The console orange background color.
     */
    public static final String ORANGE_BG = "\u001B[43m";
    
    /**
     * The console pink background color.
     */
    public static final String PINK_BG = "\u001B[41m";
    
    /**
     * The console yellow background color.
     */
    public static final String YELLOW_BG = "\u001B[103m";
    
    /**
     * The console green background color.
     */
    public static final String GREEN_BG = "\u001B[102m";
    
    /**
     * The console dark green background color.
     */
    public static final String DARK_GREEN_BG = "\u001B[42m";
    
    /**
     * The console cyan background color.
     */
    public static final String CYAN_BG = "\u001B[46m";
    
    /**
     * The console light blue background color.
     */
    public static final String LIGHT_BLUE_BG = "\u001B[106m";
    
    /**
     * The console blue background color.
     */
    public static final String BLUE_BG = "\u001B[104m";
    
    /**
     * The console dark blue background color.
     */
    public static final String DARK_BLUE_BG = "\u001B[44m";
    
    /**
     * The console magenta background color.
     */
    public static final String MAGENTA_BG = "\u001B[105m";
    
    /**
     * The console purple background color.
     */
    public static final String PURPLE_BG = "\u001B[45m";
    
    /**
     * The console grey background color.
     */
    public static final String GREY_BG = "\u001B[100m";
    
    /**
     * The console black background color.
     */
    public static final String BLACK_BG = "\u001B[40m";
    
    
    //Functions
    
    /**
     * Adds an effect to the string for output to the console.
     *
     * @param string The string to add an effect to.
     * @param effect The effect.
     * @return The string with the added effect for output to the console.
     */
    private static String stringEffect(String string, String effect) {
        return effect + string + RESET;
    }
    
    /**
     * Adds effects to the string for output to the console.
     *
     * @param string  The string to add effects to.
     * @param effects The effects to add.
     * @return The string with the added effects for output to the console.
     */
    public static String stringEffects(String string, ConsoleEffect... effects) {
        StringBuilder effectStringBuilder = new StringBuilder();
        for (ConsoleEffect effect : effects) {
            if (effectStringBuilder.length() > 0) {
                effectStringBuilder.append(';');
            }
            effectStringBuilder.append(effect.getCode());
        }
        effectStringBuilder.append('m');
        
        return "\u001B[" + effectStringBuilder.toString() + string + RESET;
    }
    
    /**
     * Adds color to the string for output to the console.
     *
     * @param string The string to add color to.
     * @param color  The color.
     * @return The string with the added color for output to the console.
     */
    public static String color(String string, ConsoleEffect color) {
        return "\u001B[" + color.getCode() + 'm' + string + RESET;
    }
    
    /**
     * Adds color and background to the string for output to the console.
     *
     * @param string     The string to add color to.
     * @param color      The color.
     * @param background The background color.
     * @return The string with the added color for output to the console.
     */
    public static String colorAndBackground(String string, ConsoleEffect color, ConsoleEffect background) {
        return "\u001B[" + color.getCode() + ';' + background.getCode() + 'm' + string + RESET;
    }
    
    /**
     * Adds 8-bit color to the string for output to the console.
     *
     * @param string The string to add color to.
     * @param color  The color.
     * @return The string with the added color for output to the console.
     */
    public static String color8Bit(String string, int color) {
        if ((color < 0) || (color > 255)) {
            return string;
        }
        
        return "\u001B[38;5;" + color + 'm' + string + RESET;
    }
    
    /**
     * Adds 8-bit color and background to the string for output to the console.
     *
     * @param string     The string to add color to.
     * @param color      The color.
     * @param background The background color.
     * @return The string with the added color for output to the console.
     */
    public static String colorAndBackground8Bit(String string, int color, int background) {
        if ((color < 0) || (color > 255) || (background < 0) || (background > 255)) {
            return string;
        }
        
        return "\u001B[38;5;" + color + ";48;5;" + background + 'm' + string + RESET;
    }
    
    /**
     * Adds 24-bit color to the string for output to the console.
     *
     * @param string The string to add color to.
     * @param red    The red element of the color.
     * @param green  The green element of the color.
     * @param blue   The blue element of the color.
     * @return The string with the added color for output to the console.
     */
    public static String color24Bit(String string, int red, int green, int blue) {
        if ((red < 0) || (red > 255) || (green < 0) || (green > 255) || (blue < 0) || (blue > 255)) {
            return string;
        }
        
        return "\u001B[38;2;" + red + ';' + green + ';' + blue + 'm' + string + RESET;
    }
    
    /**
     * Adds 24-bit color and background to the string for output to the console.
     *
     * @param string  The string to add color to.
     * @param red     The red element of the color.
     * @param green   The green element of the color.
     * @param blue    The blue element of the color.
     * @param redBg   The red element of the background color.
     * @param greenBg The green element of the background color.
     * @param blueBg  The blue element of the background color.
     * @return The string with the added effects and color for output to the console.
     */
    public static String colorAndBackground24Bit(String string, int red, int green, int blue, int redBg, int greenBg, int blueBg) {
        if ((red < 0) || (red > 255) || (green < 0) || (green > 255) || (blue < 0) || (blue > 255) ||
                (redBg < 0) || (redBg > 255) || (greenBg < 0) || (greenBg > 255) || (blueBg < 0) || (blueBg > 255)) {
            return string;
        }
        
        return "\u001B[38;2;" + red + ';' + green + ';' + blue + ";48;2;" + redBg + ';' + greenBg + ';' + blueBg + 'm' + string + RESET;
    }
    
    /**
     * Adds effects and color to the string for output to the console.
     *
     * @param string  The string to add effects and color to.
     * @param color   The color.
     * @param effects The effects to add.
     * @return The string with the added effects and color for output to the console.
     */
    public static String stringEffectsWithColor(String string, ConsoleEffect color, ConsoleEffect... effects) {
        StringBuilder effectStringBuilder = new StringBuilder();
        for (ConsoleEffect effect : effects) {
            if (effectStringBuilder.length() > 0) {
                effectStringBuilder.append(';');
            }
            effectStringBuilder.append(effect.getCode());
        }
        if (effectStringBuilder.length() > 0) {
            effectStringBuilder.append(';');
        }
        effectStringBuilder.append(color.getCode());
        effectStringBuilder.append('m');
        
        return "\u001B[" + effectStringBuilder.toString() + string + RESET;
    }
    
    /**
     * Adds effects and color and background to the string for output to the console.
     *
     * @param string     The string to add effects and color to.
     * @param color      The color.
     * @param background The background color.
     * @param effects    The effects to add.
     * @return The string with the added effects and color for output to the console.
     */
    public static String stringEffectsWithColorAndBackground(String string, ConsoleEffect color, ConsoleEffect background, ConsoleEffect... effects) {
        StringBuilder effectStringBuilder = new StringBuilder();
        for (ConsoleEffect effect : effects) {
            if (effectStringBuilder.length() > 0) {
                effectStringBuilder.append(';');
            }
            effectStringBuilder.append(effect.getCode());
        }
        if (effectStringBuilder.length() > 0) {
            effectStringBuilder.append(';');
        }
        effectStringBuilder.append(color.getCode()).append(';');
        effectStringBuilder.append(background.getCode());
        effectStringBuilder.append('m');
        
        return "\u001B[" + effectStringBuilder.toString() + string + RESET;
    }
    
    /**
     * Adds effects and 8-bit color to the string for output to the console.
     *
     * @param string  The string to add effects and color to.
     * @param color   The color.
     * @param effects The effects to add.
     * @return The string with the added effects and color for output to the console.
     */
    public static String stringEffectsWithColor8Bit(String string, int color, ConsoleEffect... effects) {
        if ((color < 0) || (color > 255)) {
            return string;
        }
        
        StringBuilder effectStringBuilder = new StringBuilder();
        for (ConsoleEffect effect : effects) {
            if (effectStringBuilder.length() > 0) {
                effectStringBuilder.append(';');
            }
            effectStringBuilder.append(effect.getCode());
        }
        if (effectStringBuilder.length() > 0) {
            effectStringBuilder.append(';');
        }
        effectStringBuilder.append("38;5;").append(color);
        effectStringBuilder.append('m');
        
        return "\u001B[" + effectStringBuilder.toString() + string + RESET;
    }
    
    /**
     * Adds effects and 8-bit color and background to the string for output to the console.
     *
     * @param string     The string to add effects and color to.
     * @param color      The color.
     * @param background The background color.
     * @param effects    The effects to add.
     * @return The string with the added effects and color for output to the console.
     */
    public static String stringEffectsWithColorAndBackground8Bit(String string, int color, int background, ConsoleEffect... effects) {
        if ((color < 0) || (color > 255) || (background < 0) || (background > 255)) {
            return string;
        }
        
        StringBuilder effectStringBuilder = new StringBuilder();
        for (ConsoleEffect effect : effects) {
            if (effectStringBuilder.length() > 0) {
                effectStringBuilder.append(';');
            }
            effectStringBuilder.append(effect.getCode());
        }
        if (effectStringBuilder.length() > 0) {
            effectStringBuilder.append(';');
        }
        effectStringBuilder.append("38;5;").append(color).append(';');
        effectStringBuilder.append("48;5;").append(background);
        effectStringBuilder.append('m');
        
        return "\u001B[" + effectStringBuilder.toString() + string + RESET;
    }
    
    /**
     * Adds effects and 24-bit color to the string for output to the console.
     *
     * @param string  The string to add effects and color to.
     * @param red     The red element of the color.
     * @param green   The green element of the color.
     * @param blue    The blue element of the color.
     * @param effects The effects to add.
     * @return The string with the added effects and color for output to the console.
     */
    public static String stringEffectsWithColor24Bit(String string, int red, int green, int blue, ConsoleEffect... effects) {
        if ((red < 0) || (red > 255) || (green < 0) || (green > 255) || (blue < 0) || (blue > 255)) {
            return string;
        }
        
        StringBuilder effectStringBuilder = new StringBuilder();
        for (ConsoleEffect effect : effects) {
            if (effectStringBuilder.length() > 0) {
                effectStringBuilder.append(';');
            }
            effectStringBuilder.append(effect.getCode());
        }
        if (effectStringBuilder.length() > 0) {
            effectStringBuilder.append(';');
        }
        effectStringBuilder.append("38;2;").append(red).append(';').append(green).append(';').append(blue);
        effectStringBuilder.append('m');
        
        return "\u001B[" + effectStringBuilder.toString() + string + RESET;
    }
    
    /**
     * Adds effects and 24-bit color and background to the string for output to the console.
     *
     * @param string  The string to add effects and color to.
     * @param red     The red element of the color.
     * @param green   The green element of the color.
     * @param blue    The blue element of the color.
     * @param redBg   The red element of the background color.
     * @param greenBg The green element of the background color.
     * @param blueBg  The blue element of the background color.
     * @param effects The effects to add.
     * @return The string with the added effects and color for output to the console.
     */
    public static String stringEffectsWithColorAndBackground24Bit(String string, int red, int green, int blue, int redBg, int greenBg, int blueBg, ConsoleEffect... effects) {
        if ((red < 0) || (red > 255) || (green < 0) || (green > 255) || (blue < 0) || (blue > 255) ||
                (redBg < 0) || (redBg > 255) || (greenBg < 0) || (greenBg > 255) || (blueBg < 0) || (blueBg > 255)) {
            return string;
        }
        
        StringBuilder effectStringBuilder = new StringBuilder();
        for (ConsoleEffect effect : effects) {
            if (effectStringBuilder.length() > 0) {
                effectStringBuilder.append(';');
            }
            effectStringBuilder.append(effect.getCode());
        }
        if (effectStringBuilder.length() > 0) {
            effectStringBuilder.append(';');
        }
        effectStringBuilder.append("38;2;").append(red).append(';').append(green).append(';').append(blue).append(';');
        effectStringBuilder.append("48;2;").append(redBg).append(';').append(greenBg).append(';').append(blueBg);
        effectStringBuilder.append('m');
        
        return "\u001B[" + effectStringBuilder.toString() + string + RESET;
    }
    
    /**
     * Makes a string bold for output to the console.
     *
     * @param string The string to make bold.
     * @return The string made bold for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String bold(String string) {
        return stringEffect(string, BOLD);
    }
    
    /**
     * Makes a string faint for output to the console.
     *
     * @param string The string to make faint.
     * @return The string made faint for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String faint(String string) {
        return stringEffect(string, FAINT);
    }
    
    /**
     * Makes a string italic for output to the console.
     *
     * @param string The string to make italic.
     * @return The string made italic for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String italic(String string) {
        return stringEffect(string, ITALIC);
    }
    
    /**
     * Underlines a string for output to the console.
     *
     * @param string The string to underline.
     * @return The underlined string for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String underline(String string) {
        return stringEffect(string, UNDERLINE);
    }
    
    /**
     * Makes a string blink slowly for output to the console.
     *
     * @param string The string to make blink slowly.
     * @return The string made to blink slowly for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String blinkSlow(String string) {
        return stringEffect(string, BLINK_SLOW);
    }
    
    /**
     * Makes a string blink fast for output to the console.
     *
     * @param string The string to make blink fast.
     * @return The string made to blink fast for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String blinkFast(String string) {
        return stringEffect(string, BLINK_FAST);
    }
    
    /**
     * Makes a string inverted for output to the console.
     *
     * @param string The string to make inverted.
     * @return The string made inverted for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String invert(String string) {
        return stringEffect(string, INVERT);
    }
    
    /**
     * Makes a string concealed for output to the console.
     *
     * @param string The string to make concealed.
     * @return The string made concealed for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String conceal(String string) {
        return stringEffect(string, CONCEAL);
    }
    
    /**
     * Strikes through a string for output to the console.
     *
     * @param string The string to strike through.
     * @return The string strike through for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String strikeThrough(String string) {
        return stringEffect(string, STRIKE_THROUGH);
    }
    
    /**
     * Colors a string bright white for output to the console.
     *
     * @param string The string to color bright white.
     * @return The string colored bright white for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String brightWhite(String string) {
        return stringEffect(string, BRIGHT_WHITE);
    }
    
    /**
     * Colors a string white for output to the console.
     *
     * @param string The string to color white.
     * @return The string colored white for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String white(String string) {
        return stringEffect(string, WHITE);
    }
    
    /**
     * Colors a string red for output to the console.
     *
     * @param string The string to color red.
     * @return The string colored red for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String red(String string) {
        return stringEffect(string, RED);
    }
    
    /**
     * Colors a string orange for output to the console.
     *
     * @param string The string to color orange.
     * @return The string colored orange for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String orange(String string) {
        return stringEffect(string, ORANGE);
    }
    
    /**
     * Colors a string pink for output to the console.
     *
     * @param string The string to color pink.
     * @return The string colored pink for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String pink(String string) {
        return stringEffect(string, PINK);
    }
    
    /**
     * Colors a string yellow for output to the console.
     *
     * @param string The string to color yellow.
     * @return The string colored yellow for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String yellow(String string) {
        return stringEffect(string, YELLOW);
    }
    
    /**
     * Colors a string green for output to the console.
     *
     * @param string The string to color green.
     * @return The string colored green for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String green(String string) {
        return stringEffect(string, GREEN);
    }
    
    /**
     * Colors a string dark green for output to the console.
     *
     * @param string The string to color dark green.
     * @return The string colored dark green for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String darkGreen(String string) {
        return stringEffect(string, DARK_GREEN);
    }
    
    /**
     * Colors a string cyan for output to the console.
     *
     * @param string The string to color cyan.
     * @return The string colored cyan for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String cyan(String string) {
        return stringEffect(string, CYAN);
    }
    
    /**
     * Colors a string light blue for output to the console.
     *
     * @param string The string to color light blue.
     * @return The string colored light blue for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String lightBlue(String string) {
        return stringEffect(string, LIGHT_BLUE);
    }
    
    /**
     * Colors a string blue for output to the console.
     *
     * @param string The string to color blue.
     * @return The string colored blue for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String blue(String string) {
        return stringEffect(string, BLUE);
    }
    
    /**
     * Colors a string dark blue for output to the console.
     *
     * @param string The string to color dark blue.
     * @return The string colored dark blue for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String darkBlue(String string) {
        return stringEffect(string, BLUE);
    }
    
    /**
     * Colors a string magenta for output to the console.
     *
     * @param string The string to color magenta.
     * @return The string colored magenta for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String magenta(String string) {
        return stringEffect(string, MAGENTA);
    }
    
    /**
     * Colors a string purple for output to the console.
     *
     * @param string The string to color purple.
     * @return The string colored purple for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String purple(String string) {
        return stringEffect(string, PURPLE);
    }
    
    /**
     * Colors a string grey for output to the console.
     *
     * @param string The string to color grey.
     * @return The string colored grey for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String grey(String string) {
        return stringEffect(string, GREY);
    }
    
    /**
     * Colors a string black for output to the console.
     *
     * @param string The string to color black.
     * @return The string colored black for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String black(String string) {
        return stringEffect(string, BLACK);
    }
    
    /**
     * Colors a string's background bright white for output to the console.
     *
     * @param string The string to color the background bright white for.
     * @return The string with its background colored bright white for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String brightWhiteBackground(String string) {
        return stringEffect(string, BRIGHT_WHITE_BG);
    }
    
    /**
     * Colors a string's background white for output to the console.
     *
     * @param string The string to color the background white for.
     * @return The string with its background colored white for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String whiteBackground(String string) {
        return stringEffect(string, WHITE_BG);
    }
    
    /**
     * Colors a string's background red for output to the console.
     *
     * @param string The string to color the background red for.
     * @return The string with its background colored red for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String redBackground(String string) {
        return stringEffect(string, RED_BG);
    }
    
    /**
     * Colors a string's background orange for output to the console.
     *
     * @param string The string to color the background orange for.
     * @return The string with its background colored orange for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String orangeBackground(String string) {
        return stringEffect(string, ORANGE_BG);
    }
    
    /**
     * Colors a string's background pink for output to the console.
     *
     * @param string The string to color the background pink for.
     * @return The string with its background colored pink for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String pinkBackground(String string) {
        return stringEffect(string, PINK_BG);
    }
    
    /**
     * Colors a string's background yellow for output to the console.
     *
     * @param string The string to color the background yellow for.
     * @return The string with its background colored yellow for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String yellowBackground(String string) {
        return stringEffect(string, YELLOW_BG);
    }
    
    /**
     * Colors a string's background green for output to the console.
     *
     * @param string The string to color the background green for.
     * @return The string with its background colored green for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String greenBackground(String string) {
        return stringEffect(string, GREEN_BG);
    }
    
    /**
     * Colors a string's background dark green for output to the console.
     *
     * @param string The string to color the background green for.
     * @return The string with its background colored green for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String darkGreenBackground(String string) {
        return stringEffect(string, DARK_GREEN_BG);
    }
    
    /**
     * Colors a string's background cyan for output to the console.
     *
     * @param string The string to color the background cyan for.
     * @return The string with its background colored cyan for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String cyanBackground(String string) {
        return stringEffect(string, CYAN_BG);
    }
    
    /**
     * Colors a string's background light blue for output to the console.
     *
     * @param string The string to color the background light blue for.
     * @return The string with its background colored light blue for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String lightBlueBackground(String string) {
        return stringEffect(string, LIGHT_BLUE_BG);
    }
    
    /**
     * Colors a string's background blue for output to the console.
     *
     * @param string The string to color the background blue for.
     * @return The string with its background colored blue for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String blueBackground(String string) {
        return stringEffect(string, BLUE_BG);
    }
    
    /**
     * Colors a string's background dark blue for output to the console.
     *
     * @param string The string to color the background dark blue for.
     * @return The string with its background colored dark blue for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String darkBlueBackground(String string) {
        return stringEffect(string, DARK_BLUE_BG);
    }
    
    /**
     * Colors a string's background magenta for output to the console.
     *
     * @param string The string to color the background magenta for.
     * @return The string with its background colored magenta for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String magentaBackground(String string) {
        return stringEffect(string, MAGENTA_BG);
    }
    
    /**
     * Colors a string's background purple for output to the console.
     *
     * @param string The string to color the background purple for.
     * @return The string with its background colored purple for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String purpleBackground(String string) {
        return stringEffect(string, PURPLE_BG);
    }
    
    /**
     * Colors a string's background grey for output to the console.
     *
     * @param string The string to color the background grey for.
     * @return The string with its background colored grey for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String greyBackground(String string) {
        return stringEffect(string, GREY_BG);
    }
    
    /**
     * Colors a string's background black for output to the console.
     *
     * @param string The string to color the background black for.
     * @return The string with its background colored black for output to the console.
     * @see #stringEffect(String, String)
     */
    public static String blackBackground(String string) {
        return stringEffect(string, BLACK_BG);
    }
    
}
