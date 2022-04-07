/*
 * File:    Mappers.java
 * Package: commons.lambda.stream.mapper
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.mapper;

import java.util.function.Function;

import commons.lambda.function.checked.CheckedConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides access to custom stream mappers.
 */
public final class Mappers {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Mappers.class);
    
    
    //Static Methods
    
    /**
     * Creates a custom mapper that modifies a stream.
     *
     * @param modifier The consumer that modifies the object.
     * @param <T>      The type of the object.
     * @return The object.
     */
    public static <T> Function<T, T> modify(CheckedConsumer<T> modifier) {
        return e -> {
            modifier.accept(e);
            return e;
        };
    }
    
}
