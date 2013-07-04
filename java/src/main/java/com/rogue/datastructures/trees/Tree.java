package com.rogue.datastructures.trees;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;
import com.rogue.datastructures.DynamicSet;

/**
 * General functionality of the "Tree" class of data structures.
 * 
 * @author R. Matt McCann
 */
public abstract class Tree<T> implements DynamicSet<T> {
    /** The root of the tree. */
    private Optional<? extends TreeNode<T>> root = Optional.absent();

    protected TreeNode<T> getRoot() {
        checkState(root.isPresent(), "Tree is empty!");
        
        return root.get();
    }
    
    /** {@inheritDocs} */
    @Override
    public void empty() {
        root = Optional.absent();
    }
    
    /** {@inheritDocs} */
    @Override
    public boolean isEmpty() {
        return !root.isPresent(); // If the root is present the tree must not be null
    }
    
    /** @param root Must not be null. */
    protected void setRoot(TreeNode<T> root) {
        this.root = Optional.of(root);
    } 
}
