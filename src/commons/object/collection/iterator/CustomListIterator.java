/*
 * File:    CustomListIterator.java
 * Package: commons.object.collection.iterator
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.iterator;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

import commons.math.number.BoundUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a Custom List Iterator.
 *
 * @param <T> The type of the elements of the iterator.
 */
public class CustomListIterator<T> extends CustomIterator<T> implements ListIterator<T> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomListIterator.class);
    
    
    //Fields
    
    /**
     * The function that performs replacements, or null if replacements are not permitted.
     */
    private final BiConsumer<Integer, T> replacer;
    
    /**
     * The function that performs insertions, or null if insertions are not permitted.
     */
    private final BiConsumer<Integer, T> inserter;
    
    
    //Constructors
    
    /**
     * The constructor for a Custom List Iterator.
     *
     * @param iteration The elements of the iteration.
     * @param remover   The function that performs removals, or null if removals are not permitted.
     * @param replacer  The function that performs replacements, or null if replacements are not permitted.
     * @param inserter  The function that performs insertions, or null if insertions are not permitted.
     * @see CustomIterator#CustomIterator(Collection, BiConsumer)
     */
    public CustomListIterator(Collection<T> iteration, BiConsumer<Integer, T> remover, BiConsumer<Integer, T> replacer, BiConsumer<Integer, T> inserter) {
        super(iteration, remover);
        this.replacer = replacer;
        this.inserter = inserter;
    }
    
    /**
     * The constructor for a Custom List Iterator.
     *
     * @param iteration The elements of the iteration.
     * @see #CustomListIterator(Collection, BiConsumer, BiConsumer, BiConsumer)
     */
    public CustomListIterator(Collection<T> iteration) {
        this(iteration, null, null, null);
    }
    
    
    //Methods
    
    /**
     * Returns whether or not the iteration has previous elements.
     *
     * @return Whether or not the iteration has previous elements.
     * @see BoundUtility#inListBounds(int, List)
     */
    @Override
    public boolean hasPrevious() {
        return BoundUtility.inListBounds((index - 1), iteration);
    }
    
    /**
     * Retrieves the previous element of the iteration.
     *
     * @return The previous element of the iteration.
     * @throws NoSuchElementException When there is not a previous element.
     */
    @Override
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        canModify = true;
        return iteration.get(--index);
    }
    
    /**
     * Returns the index of the next element of the iteration.
     *
     * @return The index of the next element of the iteration, or the iteration size if the iterator has no more elements.
     */
    @Override
    public int nextIndex() {
        return (index + 1);
    }
    
    /**
     * Returns the index of the previous element of the iteration.
     *
     * @return The index of the previous element of the iteration, or -1 if the iterator has no previous elements.
     */
    @Override
    public int previousIndex() {
        return Math.max((index - 1), -1);
    }
    
    /**
     * Replaces the current element of the iteration.
     *
     * @param newElement The new element.
     * @throws IllegalStateException         When the current element that has already been modified, or when a current element has not yet been retrieved.
     * @throws UnsupportedOperationException If a function to perform replacements was not defined.
     */
    @Override
    public void set(T newElement) {
        if (replacer == null) {
            throw new UnsupportedOperationException();
        }
        if (!canModify) {
            throw new IllegalStateException();
        }
        replacer.accept(index, newElement);
        iteration.set(index, newElement);
        canModify = false;
    }
    
    /**
     * Inserts an element before the current element of the iteration.
     *
     * @param element The element.
     * @throws UnsupportedOperationException If a function to perform insertions was not defined.
     */
    @Override
    public void add(T element) {
        if (inserter == null) {
            throw new UnsupportedOperationException();
        }
        inserter.accept(Math.max(index, 0), element);
        iteration.add(Math.max(index++, 0), element);
        canModify = false;
    }
    
}
