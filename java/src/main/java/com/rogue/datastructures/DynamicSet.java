package com.rogue.datastructures;

import com.google.common.base.Optional;

/**
 * Functional contract for the "Dynamic Set" collection of data structures.
 * 
 * @author R. Matt McCann
 */
public interface DynamicSet <T> {
    /**
     * Removes the node from the set.
     * 
     * @param node Must not be null.
     */
    void delete(Node<T> node);
    
    /** Empties the dynamic set. */
    void empty();
    
    /**
     * Inserts a node featuring the provided label into the set.
     * 
     * @param label Must not be null. 
     */
    void insert(T label);
    
    /**
     * @return Whether or not the set is empty.
     */
    boolean isEmpty();
    
    /**
     * Returns the node featuring the maximum label value in the set. The set
     * must not be empty.
     * 
     * @return Node featuring the maximum label value. 
     */
    Node<T> maximum();
    
    /**
     * Returns the node featuring the minimum label value in the set. The set
     * must not be empty.
     * 
     * @return Node featuring the minimum label value.
     */
    Node<T> minimum();
    
    /**
     * Retries the next smaller node than the provided node.
     * 
     * @param node Must not be null.
     * @return Absent if no smaller node exists or the next smaller node.
     */
    Optional<? extends Node<T>> predecessor(Node<T> node);
    
    /**
     * Retrieves the first node discovered in the set with the provided label.
     * 
     * @param label Must not be null.
     * @return Absent if no node with the provided label exists or the discovered node.
     */
    Optional<? extends Node<T>> search(T label);
    
    /**
     * Retrieves the next larger node than the provided node.
     * 
     * @param node Must not be null.
     * @return Absent if no larger node exists or the next larger node.
     */
    Optional<? extends Node<T>> successor(Node<T> node);
}
