package com.rogue.datastructures.trees;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;

/**
 * Node for the Binary Search Trees sub-set of the Tree family of data structures.
 * 
 * @author R. Matt McCann
 */
public class BinaryNode<T> extends TreeNode<T> {
    /** Left child of the node. */
    private Optional<? extends BinaryNode<T>> leftChild = Optional.absent();
    
    /** Right child of the node. */
    private Optional<? extends BinaryNode<T>> rightChild = Optional.absent();

    public BinaryNode<T> getLeftChild() {
        checkState(leftChild.isPresent(), "Left child has not been set!");
        
        return leftChild.get();
    }

    public BinaryNode<T> getRightChild() {
        checkState(rightChild.isPresent(), "Right child has not been set!");
        
        return rightChild.get();
    }
    
    public boolean hasLeftChild() {
        return leftChild.isPresent();
    }
    
    public boolean hasRightChild() {
        return rightChild.isPresent();
    }

    public void setLeftChild(BinaryNode<T> leftChild) {
        this.leftChild = Optional.of(leftChild);
    }

    public void setRightChild(BinaryNode<T> rightChild) {
        this.rightChild = Optional.of(rightChild);
    }
}
