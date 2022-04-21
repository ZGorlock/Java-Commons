/*
 * File:    CounterSet.java
 * Package: commons.object.collection.set
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.set;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

import commons.lambda.stream.collector.MapCollectors;
import commons.object.collection.MapUtility;
import commons.object.collection.iterator.CustomIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a Counter Set.
 *
 * @param <T> The type of the elements.
 */
public class CounterSet<T> extends HashSet<T> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CounterSet.class);
    
    
    //Fields
    
    /**
     * The counters of the Counter Set.
     */
    private final Map<T, AtomicInteger> counters = new HashMap<>();
    
    
    //Constructors
    
    /**
     * The constructor for a Counter Set from a list of elements.
     *
     * @param elements The elements.
     */
    public CounterSet(Collection<? extends T> elements) {
        this.addAll(elements);
    }
    
    /**
     * The constructor for a Counter Set from a map of counters.
     *
     * @param counters The counters.
     * @see #CounterSet(Collection)
     * @see #set(Object, int)
     */
    public CounterSet(Map<? extends T, ? extends Number> counters) {
        this(counters.keySet());
        counters.forEach((element, counter) ->
                this.set(element, counter.intValue()));
    }
    
    /**
     * The constructor for a Counter Set from another Counter Set.
     *
     * @param set The Counter Set.
     * @param <U> The type of the elements of the source set.
     * @see #CounterSet(Collection)
     * @see #set(Object, int)
     */
    public <U extends T> CounterSet(CounterSet<U> set) {
        this((Collection<U>) set);
        set.forEach(element ->
                this.set(element, set.get(element)));
    }
    
    /**
     * The default no-argument constructor for a Counter Set.
     */
    public CounterSet() {
    }
    
    
    //Methods
    
    /**
     * Adds an element to the set.
     *
     * @param element The element.
     * @return If the element was added to the set.
     * @see HashSet#add(Object)
     */
    @Override
    public synchronized boolean add(T element) {
        counters.putIfAbsent(element, new AtomicInteger(0));
        return super.add(element);
    }
    
    /**
     * Removes an element from the set.
     *
     * @param element The element.
     * @return If the element was removed from the set.
     * @see HashSet#remove(Object)
     */
    @Override
    public synchronized boolean remove(Object element) {
        counters.remove(element);
        return super.remove(element);
    }
    
    /**
     * Clears the set.
     *
     * @see HashSet#clear()
     */
    @Override
    public synchronized void clear() {
        counters.clear();
        super.clear();
    }
    
    /**
     * Clones the set.
     *
     * @return A clone of the set.
     * @see #CounterSet(CounterSet)
     */
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public CounterSet<T> clone() {
        return new CounterSet<>(this);
    }
    
    /**
     * Determines if another CounterSet is equal to this CounterSet.
     *
     * @param o The other CounterSet.
     * @return Whether the two CounterSets are equal or not.
     * @see MapUtility#equals(Map, Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public synchronized boolean equals(Object o) {
        if (!(o instanceof CounterSet)) {
            return false;
        }
        CounterSet<Object> other = (CounterSet<Object>) o;
        
        return MapUtility.equals(
                counters.keySet().stream().collect(MapCollectors.toHashMap(Function.identity(), this::get)),
                other.counters.keySet().stream().collect(MapCollectors.toHashMap(Function.identity(), other::get)));
    }
    
    /**
     * Gets the counter for an element of the set.
     *
     * @param element The element.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer get(T element) {
        return modify(element, AtomicInteger::get);
    }
    
    /**
     * Gets the counter for an element of the set and sets the counter to a new value.
     *
     * @param element The element.
     * @param value   The new value.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer getAndSet(T element, int value) {
        return modify(element, (counter -> counter.getAndSet(value)));
    }
    
    /**
     * Sets the counter for an element of the set.
     *
     * @param element The element.
     * @param value   The value of the counter.
     * @see #getAndSet(Object, int)
     */
    public synchronized void set(T element, int value) {
        getAndSet(element, value);
    }
    
    /**
     * Gets the counter for an element of the set and resets the counter.
     *
     * @param element The element.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #getAndSet(Object, int)
     */
    public synchronized Integer getAndReset(T element) {
        return getAndSet(element, 0);
    }
    
    /**
     * Resets the counter for an element of the set.
     *
     * @param element The element.
     * @see #getAndReset(Object)
     */
    public synchronized void reset(T element) {
        getAndReset(element);
    }
    
    /**
     * Gets the counter for an element of the set and steps the counter by a certain amount.
     *
     * @param element The element.
     * @param step    The value to step.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer getAndStep(T element, int step) {
        return modify(element, (counter -> counter.getAndAdd(step)));
    }
    
    /**
     * Steps the counter for an element of the set by a certain amount and gets the counter.
     *
     * @param element The element.
     * @param step    The value to step.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer stepAndGet(T element, int step) {
        return modify(element, (counter -> counter.addAndGet(step)));
    }
    
    /**
     * Steps the counter for an element of the set by a certain amount.
     *
     * @param element The element.
     * @param step    The value to step.
     * @see #getAndStep(Object, int)
     */
    public synchronized void step(T element, int step) {
        getAndStep(element, step);
    }
    
    /**
     * Gets the counter for an element of the set and increments the counter.
     *
     * @param element The element.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer getAndIncrement(T element) {
        return modify(element, AtomicInteger::getAndIncrement);
    }
    
    /**
     * Increments the counter for an element of the set and gets the counter.
     *
     * @param element The element.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer incrementAndGet(T element) {
        return modify(element, AtomicInteger::incrementAndGet);
    }
    
    /**
     * Increments the counter for an element of the set.
     *
     * @param element The element.
     * @see #getAndIncrement(Object)
     */
    public synchronized void increment(T element) {
        getAndIncrement(element);
    }
    
    /**
     * Gets the counter for an element of the set and decrements the counter.
     *
     * @param element The element.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer getAndDecrement(T element) {
        return modify(element, AtomicInteger::getAndDecrement);
    }
    
    /**
     * Decrements the counter for an element of the set and gets the counter.
     *
     * @param element The element.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer decrementAndGet(T element) {
        return modify(element, AtomicInteger::decrementAndGet);
    }
    
    /**
     * Decrements the counter for an element of the set.
     *
     * @param element The element.
     * @see #getAndDecrement(Object)
     */
    public synchronized void decrement(T element) {
        getAndDecrement(element);
    }
    
    /**
     * Gets the counter for an element of the set and updates the counter.
     *
     * @param element The element.
     * @param updater The function to perform the update.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer getAndUpdate(T element, IntUnaryOperator updater) {
        return modify(element, (counter -> counter.getAndUpdate(updater)));
    }
    
    /**
     * Updates the counter for an element of the set and gets the counter.
     *
     * @param element The element.
     * @param updater The function to perform the update.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer updateAndGet(T element, IntUnaryOperator updater) {
        return modify(element, (counter -> counter.updateAndGet(updater)));
    }
    
    /**
     * Updates the counter for an element of the set.
     *
     * @param element The element.
     * @param updater The function to perform the update.
     * @see #getAndUpdate(Object, IntUnaryOperator)
     */
    public synchronized void update(T element, IntUnaryOperator updater) {
        getAndUpdate(element, updater);
    }
    
    /**
     * Gets the counter for an element of the set and accumulates the counter.
     *
     * @param element     The element.
     * @param update      The update value.
     * @param accumulator The function to perform the accumulation.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer getAndAccumulate(T element, int update, IntBinaryOperator accumulator) {
        return modify(element, (counter -> counter.getAndAccumulate(update, accumulator)));
    }
    
    /**
     * Accumulates the counter for an element of the set and gets the counter.
     *
     * @param element     The element.
     * @param update      The update value.
     * @param accumulator The function to perform the accumulation.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     * @see #modify(Object, Function)
     */
    public synchronized Integer accumulateAndGet(T element, int update, IntBinaryOperator accumulator) {
        return modify(element, (counter -> counter.accumulateAndGet(update, accumulator)));
    }
    
    /**
     * Accumulates the counter for an element of the set.
     *
     * @param element     The element.
     * @param update      The update value.
     * @param accumulator The function to perform the accumulation.
     * @see #getAndAccumulate(Object, int, IntBinaryOperator)
     */
    public synchronized void accumulate(T element, int update, IntBinaryOperator accumulator) {
        getAndAccumulate(element, update, accumulator);
    }
    
    /**
     * Performs a modification on the counter for an element of the set.
     *
     * @param element  The element.
     * @param modifier The function to perform on the modification.
     * @return The value of the counter for the specified element, or null if the set does not contain the specified element.
     */
    public synchronized Integer modify(T element, Function<AtomicInteger, Integer> modifier) {
        return Optional.ofNullable(counters.get(element))
                .map(modifier)
                .orElse(null);
    }
    
    /**
     * Creates an iterator of the elements of the set.
     *
     * @return The iterator.
     * @see CustomIterator
     */
    @Override
    public Iterator<T> iterator() {
        return new CustomIterator<>(counters.keySet(), this::remove);
    }
    
}
