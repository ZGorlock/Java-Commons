/*
 * File:    LazyCounterSet.java
 * Package: commons.object.collection.set
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.set;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a Lazy Counter Set.
 *
 * @param <T> The type of the elements.
 */
public class LazyCounterSet<T> extends CounterSet<T> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(LazyCounterSet.class);
    
    
    //Constructors
    
    /**
     * The constructor for a Counter Set from a list of elements.
     *
     * @param elements The elements.
     * @param count    Whether or not to initialize the counters with the number of occurrences of the elements in the list.
     * @see CounterSet#CounterSet(Collection, boolean)
     */
    public LazyCounterSet(Collection<? extends T> elements, boolean count) {
        super(elements, count);
    }
    
    /**
     * The constructor for a Counter Set from a list of elements.
     *
     * @param elements The elements.
     * @see CounterSet#CounterSet(Collection)
     */
    public LazyCounterSet(Collection<? extends T> elements) {
        super(elements);
    }
    
    /**
     * The constructor for a Counter Set from a map of counters.
     *
     * @param counters The counters.
     * @see CounterSet#CounterSet(Map)
     */
    public LazyCounterSet(Map<? extends T, ? extends Number> counters) {
        super(counters);
    }
    
    /**
     * The constructor for a Counter Set from another Counter Set.
     *
     * @param set The Counter Set.
     * @see CounterSet#CounterSet(CounterSet)
     */
    public LazyCounterSet(CounterSet<T> set) {
        super(set);
    }
    
    /**
     * The default no-argument constructor for a Counter Set.
     *
     * @see CounterSet#CounterSet()
     */
    public LazyCounterSet() {
        super();
    }
    
    
    //Methods
    
    /**
     * Performs a modification on the counter for an element of the set.
     *
     * @param element  The element.
     * @param modifier The function to perform on the modification.
     * @return The value of the counter for the specified element.
     */
    @Override
    public synchronized Integer modifyAndGet(T element, Function<AtomicInteger, Integer> modifier) {
        if (!contains(element)) {
            add(element);
        }
        return super.modifyAndGet(element, modifier);
    }
    
    /**
     * Clones the set.
     *
     * @return A clone of the set.
     * @see #LazyCounterSet(CounterSet)
     */
    @Override
    public LazyCounterSet<T> clone() {
        return new LazyCounterSet<>(this);
    }
    
}
