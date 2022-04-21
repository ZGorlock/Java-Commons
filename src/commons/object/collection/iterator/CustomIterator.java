/*
 * File:    CustomIterator.java
 * Package: commons.object.collection.iterator
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.iterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import commons.math.BoundUtility;
import commons.object.collection.ListUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a Custom Iterator.
 *
 * @param <T> The type of the elements of the iterator.
 */
public class CustomIterator<T> implements Iterator<T> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomIterator.class);
    
    
    //Fields
    
    /**
     * The elements of the iteration.
     */
    private final List<T> iteration;
    
    /**
     * The current index of the iteration.
     */
    private int current = -1;
    
    /**
     * A flag indicating whether or not the current element of the iteration can be removed.
     */
    private boolean canRemove = false;
    
    /**
     * The function that performs removals, or null if removals are not permitted.
     */
    private final Consumer<T> remover;
    
    
    //Constructors
    
    /**
     * The constructor for a Custom Iterator.
     *
     * @param iteration The elements of the iteration.
     * @param remover   The function that performs removals, or null if removals are not permitted.
     */
    public CustomIterator(Collection<T> iteration, Consumer<T> remover) {
        this.iteration = ListUtility.toList(iteration);
        this.remover = remover;
    }
    
    /**
     * The constructor for a Custom Iterator.
     *
     * @param iteration The elements of the iteration.
     * @see #CustomIterator(Collection, Consumer)
     */
    public CustomIterator(Collection<T> iteration) {
        this(iteration, null);
    }
    
    
    //Methods
    
    /**
     * Returns whether or not the iteration has more elements.
     *
     * @return Whether or not the iteration has more elements.
     * @see BoundUtility#inListBounds(int, List)
     */
    @Override
    public boolean hasNext() {
        return BoundUtility.inListBounds((current + 1), iteration);
    }
    
    /**
     * Retrieves the next element of the iteration.
     *
     * @return The next element of the iteration.
     * @throws NoSuchElementException When there is not a next element.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        canRemove = true;
        return iteration.get(++current);
    }
    
    /**
     * Removes the current element of the iteration.
     *
     * @throws IllegalStateException When attempting to remove an element that has already been removed, or when the the current element has not yet been retrieved, or if a function to perform removals is not defined.
     */
    @Override
    public void remove() {
        if (!canRemove || (remover == null)) {
            throw new IllegalStateException();
        }
        remover.accept(iteration.get(current));
        canRemove = false;
    }
    
}
