package com.rogue.datastructures.trees;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;

/**
 * Minimal features of a node in the "Tree" family of data structures.
 * 
 * @author R. Matt McCann
 */
public class Node<T> {
    /** Label for the node. */
    private Optional<T> label = Optional.absent();
    
    /** Parent node in the tree structure. */
    private Optional<? extends Node<T>> parent = Optional.absent();
    
    /** Empty Bean-style constructor. */
    public Node() { }

    public T getLabel() {
        checkState(label.isPresent(), "Label has not been set!");
        
        return label.get();
    }

    public Node<T> getParent() {
        checkState(label.isPresent(), "Parent has not been set!");
        
        return parent.get();
    }
    
    public boolean hasLabel() {
        return label.isPresent();
    }
    
    public boolean hasParent() {
        return parent.isPresent();
    }

    public void setLabel(T label) {
        this.label = Optional.of(label);
    }

    public void setParent(Node<T> parent) {
        this.parent = Optional.of(parent);
    }
}
