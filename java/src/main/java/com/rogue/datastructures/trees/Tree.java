package com.rogue.datastructures.trees;

import com.google.common.base.Optional;

/**
 * Functional contract for the "Tree" family of data structures.
 * 
 * @author R. Matt McCann
 */
public interface Tree <T> {
    /**
     * Removes the node from the tree. Runs in O(n) time.
     * 
     * @param node Must not be null.
     */
    void delete(Node<T> node);
    
    /**
     * Inserts a node featuring the provided label into the tree.
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
     * Retries the next smaller node than the provided node.
     * 
     * @param node Must not be null.
     * @return Absent if no smaller node exists or the next smaller node.
     */
    Optional<Node<T>> predecessor(Node<T> node);
    
    /**
     * Retrieves the first node discovered in the tree with the provided label.
     * 
     * @param label Must not be null.
     * @return Absent if no node with the provided label exists or the discovered node.
     */
    Optional<Node<T>> search(T label);
    
    /**
     * Retrieves the next larger node than the provided node.
     * 
     * @param node Must not be null.
     * @return Absent if no larger node exists or the next larger node.
     */
    Optional<Node<T>> successor(Node<T> node);
}
