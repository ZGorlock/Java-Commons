/*
 * File:    SetCollectors.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import commons.object.collection.set.CounterSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides access to custom stream collectors for collecting to sets.
 */
public final class SetCollectors {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SetCollectors.class);
    
    
    //Static Methods
    
    /**
     * Creates a new custom collector that collects a stream to a counter set.
     *
     * @param <T> The type of the elements of the stream.
     * @return The custom collector.
     * @see ListCollectors#toArrayList()
     * @see CounterSet#CounterSet(Collection)
     */
    public static <T> Collector<T, ?, CounterSet<T>> toCounterSet() {
        return Collectors.collectingAndThen(
                ListCollectors.toArrayList(),
                CounterSet::new);
    }
    
}
