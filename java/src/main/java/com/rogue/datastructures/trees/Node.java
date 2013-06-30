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
    private T label;
    
    /** Parent node in the tree structure. */
    private Optional<Node<T>> parent;
    
    /**
     * Constructor.
     * 
     * @param label Must not be null. 
     */
    public Node(T label) { 
        this.label = checkNotNull(label);
    }
    
    /**
     * Constructor.
     * 
     * @param label Must not be null.
     * @param parent Must not be null.
     */
    public Node(T label, Optional<Node<T>> parent) {
        this.label = checkNotNull(label);
        this.parent = checkNotNull(parent);
    }

    public T getLabel() {
        return label;
    }

    public Node<T> getParent() {
        return parent.get();
    }
    
    public boolean hasParent() {
        return parent.isPresent();
    }

    public void setLabel(T label) {
        this.label = checkNotNull(label);
    }

    public void setParent(Node<T> parent) {
        this.parent = Optional.of(parent);
    }
}
