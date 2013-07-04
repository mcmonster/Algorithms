package com.rogue.datastructures.trees;

import com.rogue.datastructures.Node;
import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;

/**
 * Minimal features of a node in the "Tree" family of data structures.
 * 
 * @author R. Matt McCann
 */
public class TreeNode<T> extends Node<T> {
    /** Parent node in the tree structure. */
    private Optional<? extends TreeNode<T>> parent = Optional.absent();

    public TreeNode<T> getParent() {
        checkState(parent.isPresent(), "Parent has not been set!");
        
        return parent.get();
    }
    
    public boolean hasParent() {
        return parent.isPresent();
    }
    
    public void setParent(TreeNode<T> parent) {
        this.parent = Optional.of(parent);
    }
}
