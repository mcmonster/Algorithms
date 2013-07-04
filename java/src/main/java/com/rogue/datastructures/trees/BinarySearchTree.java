package com.rogue.datastructures.trees;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import com.rogue.datastructures.Node;
import java.util.Comparator;

/**
 * Provides a 
 * 
 * @author R. Matt McCann
 */
public class BinarySearchTree<T> extends Tree<T> {
    /** Used to compare the label values of nodes. */
    private final Comparator<T> comparator;
    
    /** Convenience node used instead of an absent node. */
    private BinaryNode<T> sentinel = new BinaryNode();
    
    /**
     * Constructor.
     * 
     * @param comparator Must not be null.
     */
    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = checkNotNull(comparator);
    }
    
    @Override
    public void delete(Node<T> node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected Comparator<T> getComparator() {
        return comparator;
    }
    
    protected BinaryNode<T> getSentinel() {
        return sentinel;
    }

    @Override
    public void insert(T label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node<T> maximum() {
        checkState(!isEmpty(), "The set must not be empty!");
        
        return maximum((BinaryNode) getRoot());
    }
    
    /**
     * Finds the largest value in the tree with the provided root.
     * 
     * @param root Must not be null.
     * @return Largest value in the tree.
     */
    protected BinaryNode<T> maximum(BinaryNode<T> root) {
        checkArgument(root != null, "Root must not be null!");
        
        BinaryNode<T> max = root;
        while (max.getRightChild() != sentinel) { // While there is a right child
            max = max.getRightChild();
        }
        
        return max;
    }

    /** {@inheritDocs} */
    @Override
    public Node<T> minimum() {
        checkState(!isEmpty(), "The set must not be empty!");
        
        return minimum((BinaryNode) getRoot());
    }
    
    /**
     * Finds the smallest value in the tree with the provided root.
     * 
     * @param root Must not be null.
     * @return Smallest value in the tree.
     */
    protected BinaryNode<T> minimum(BinaryNode<T> root) {
        checkArgument(root != null, "Root must not be null!");
        
        BinaryNode<T> min = root;
        while (min.getRightChild() != sentinel) { // While there is a left child
            min = min.getLeftChild();
        }
        
        return min;
    }

    /** {@inheritDocs} */
    @Override
    public Optional<? extends Node<T>> predecessor(Node<T> node) {
        BinaryNode<T> binaryNode = (BinaryNode) node;
        
        if (binaryNode.getLeftChild() != sentinel) {
            return Optional.of(binaryNode.getLeftChild());
        } else {
            return Optional.absent();
        }
    }

    /** {@inheritDocs} */
    @Override
    public Optional<? extends Node<T>> search(T label) {
        if (isEmpty()) { // If the tree is empty
            return Optional.absent();
        } 
        
        BinaryNode<T> node = (BinaryNode) getRoot();
        while (node != sentinel) { // While there's still more node's to explore
            if (node.getLabel().equals(label)) { // If the matching label is found
                return Optional.of(node);
            } 
            // If the current node's label is greater than the search label
            else if (comparator.compare(label, node.getLabel()) < 0) {
                node = node.getLeftChild();
            } else { // If the current node's label is less than the search label
                node = node.getRightChild();
            }
        }
        
        return Optional.absent(); // Couldn't find the label
    }

    protected void setSentinel(BinaryNode<T> sentinel) {
        this.sentinel = checkNotNull(sentinel);
    }
    
    /** {@inheritDocs} */
    @Override
    public Optional<? extends Node<T>> successor(Node<T> node) {
        BinaryNode<T> binaryNode = (BinaryNode) node;
        
        if (binaryNode.getRightChild() != sentinel) {
            return Optional.of(binaryNode.getRightChild());
        } else {
            return Optional.absent();
        }
    }
}
