package com.rogue.datastructures;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkState;

/**
 * Generic node for all data structures.
 * 
 * @author R. Matt McCann
 */
public class Node<T> {
    /** Label for the node. */
    private Optional<T> label = Optional.absent();
    
    /** Empty Bean-style constructor. */
    public Node() { }
    
    public T getLabel() {
        checkState(label.isPresent(), "Label has not been set!");
        
        return label.get();
    }
    
    public boolean hasLabel() {
        return label.isPresent();
    }
    
    public void setLabel(T label) {
        this.label = Optional.of(label);
    }
}
