/*
 * File:    Actions.java
 * Package: commons.lambda.stream.action
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.action;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides access to custom stream actions.
 */
public class Actions {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Actions.class);
    
    
    //Static Methods
    
    /**
     * Creates a custom action that does nothing for each element in a stream.
     *
     * @param element The element.
     * @param <T>     The type of the element.
     * @return The custom action.
     */
    public static <T> Consumer<T> doNothing(T element) { //TODO test
        return e -> {
        };
    }
    
}
