package com.rogue.datastructures.trees;

import static com.google.common.base.Preconditions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author R. Matt McCann
 */
public class NaryNode<T> extends TreeNode<T> {
    /** Children of the node in order from left to right. */
    private final List<NaryNode<T>> children = new ArrayList();
    
    /** Used to compare the children nodes. */
    private final Comparator<T> comparator;
    
    /**
     * @param comparator Must not be null.
     */
    public NaryNode(Comparator<T> comparator) {
        this.comparator = checkNotNull(comparator);
    }
    
    /**
     * @param pos Must satisfy 0 <= pos < children.size
     * @return Child at the specified position.
     */
    public NaryNode getChild(int pos) {
        checkArgument((0 <= pos) && (pos < children.size()), "Expected pos to be "
                + "0 <= pos < children.size but got pos = %s, children.size = %s",
                pos, children.size());
        
        return children.get(pos);
    }
    
    /**
     * @param child Node to add as a child. Must not be null.
     */
    public void addChild(NaryNode<T> child) {
        checkArgument(child != null, "Child must not be null!");
        
        for (int childIter = 0; childIter < children.size(); childIter++) {
            NaryNode<T> currentChild = children.get(childIter);
            
            // If the current child is greater than the to-be-added child
            if (comparator.compare(currentChild.getLabel(), child.getLabel()) > 0) {
                children.add(childIter, child);
                return;
            }
        }
        
        children.add(children.size(), child);
    }
    
    /**
     * @param child Node to add as a child. Must not be null.
     * @param pos Where to place the child. Must satisfy 0 <= pos <= children.size
     */
    public void addChild(NaryNode<T> child, int pos) {
        checkArgument((0 <= pos) && (pos <= children.size()), "Expected pos to be "
                + "0 <= pos <= children.size but got pos = %s, children.size = %s",
                pos, children.size());
        checkArgument(child != null, "Child must not be null!");
        
        children.add(pos, child);
    }
    
    /** @return The number of children nodes. */
    public int getNumChildren() {
        return children.size();
    }
    
    /** @return Whether or not the node has children. */
    public boolean hasChildren() {
        return (children.size() > 0);
    }
}
