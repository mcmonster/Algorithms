package com.rogue.datastructures.trees;

import com.google.common.base.Optional;

/**
 * Functional contract for the "Tree" family of data structures.
 * 
 * @author R. Matt McCann
 */
public interface Tree <T> {
    /**
     * Removes the first node from the tree with the provided label.
     * 
     * @param label Must not be null.
     * @return Absent if no node with the provided label exists or the removed node. 
     */
    Optional<Node<T>> delete(T label);
    
    /**
     * Inserts a node with the provided label into the tree.
     * 
     * @param label Must not be null. 
     */
    void insert(T label);
    
    /**
     * @return Whether or not the tree is empty.
     */
    boolean isEmpty();
    
    /**
     * Returns the node featuring the maximum label value in the tree. The tree
     * must not be empty.
     * 
     * @return Node featuring the maximum label value. 
     */
    Node<T> maximum();
    
    /**
     * Returns the node featuring the minimum label value in the tree. The tree
     * must not be empty.
     * 
     * @return Node featuring the minimum label value.
     */
    Node<T> minimum();
    
    /**
     * Retrieves the first node discovered in the tree with the provided label.
     * 
     * @param label Must not be null.
     * @return Absent if no node with the provided label exists or the discovered node.
     */
    Optional<Node<T>> search(T label);
}
