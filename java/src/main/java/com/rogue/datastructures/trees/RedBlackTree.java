package com.rogue.datastructures.trees;

import static com.google.common.base.Preconditions.*;
import com.rogue.datastructures.Node;
import com.rogue.datastructures.trees.RedBlackNode.Color;
import java.util.Comparator;

/**
 * Binary search tree implementation is the guaranteed to be approximately
 * balanced, improving the worst case search time to O(lg n) versus O(n) in a
 * plain binary search tree.
 * 
 * Maintains the following properties:
 * 1. Every node is colored either red or black.
 * 2. The root is black.
 * 3. Every leaf of the tree is the sentinel node and therefore colored black.
 * 4. If a node is red, both of its children are black.
 * 5. For each node, all simple paths from the node to its descendent leaves
 *    contains the same number of black nodes.
 * 
 * @author R. Matt McCann
 */
public class RedBlackTree<T> extends BinarySearchTree<T> {
    /**
     * Constructor.
     * 
     * @param comparator Must not be null.
     */
    public RedBlackTree(Comparator<T> comparator) {
        super(comparator);
        
        setSentinel(new RedBlackNode());
    }
    
    /** 
     * Deletes the provided node from the tree. Runs in O(lg n) time.
     * 
     * @param node Must not be null. Must be an instance of RedBlackNode. 
     * Must not be the sentinel. 
     */
    @Override
    public void delete(Node node) {
        checkArgument(node != null, "Node must not be null!");
        checkArgument(node instanceof RedBlackNode, "Node must be an instance of "
                + "RedBlackNode, but instead got %s", node.getClass());
        checkArgument(node != getSentinel(), "Node must not be the sentinel!");
        
        RedBlackNode<T> toBeRecolored;
        RedBlackNode<T> toBeDeleted = (RedBlackNode) node;
        Color originalColor = toBeDeleted.getColor(); // Save the node's color for later correction
        
        if (toBeDeleted.getLeftChild() == getSentinel()) { // If the node does not have a left child
            toBeRecolored = (RedBlackNode) toBeDeleted.getRightChild();
            transplant(toBeDeleted, toBeRecolored); // Move the right child to take the deleted node's place
        }
        // If the node does not have a right child
        else if (toBeDeleted.getRightChild() == getSentinel()) {
            toBeRecolored = (RedBlackNode) toBeDeleted.getLeftChild();
            transplant(toBeDeleted, toBeRecolored); // Move the left child to take the deleted node's place
        } else { // If the node has both children
            // The sub-tree root will be its min node
            RedBlackNode<T> newSubRoot = (RedBlackNode) toBeDeleted.getRightChild();
            originalColor = newSubRoot.getColor();
            toBeRecolored = (RedBlackNode) newSubRoot.getRightChild();
            
            // If the new sub-root is a direct child of the node to be deleted
            if (newSubRoot.getParent() == toBeDeleted) { 
                toBeRecolored.setParent(toBeDeleted);
            } else { // If the new sub-root is not a direct child of the node to be deleted
                transplant(newSubRoot, (RedBlackNode) newSubRoot.getRightChild()); // Replace the new sub-root
                newSubRoot.setRightChild(toBeDeleted.getRightChild()); // Move the right sub-tree into position
                newSubRoot.getRightChild().setParent(newSubRoot);
            }
            
            transplant(toBeDeleted, newSubRoot); // Replace the deleted node
            newSubRoot.setLeftChild(toBeDeleted.getLeftChild());
            newSubRoot.getLeftChild().setParent(newSubRoot);
            newSubRoot.setColor(toBeDeleted.getColor());
        }
        
        if (originalColor == Color.BLACK) { // If new sub-roots color was originally black
            maintainRedBlackStateAfterInsert(toBeRecolored);
        }
    }

    /**
     * Inserts a node featuring the provided label into the tree. Runs in 
     * O(lg n) time, which is an improvement over a classic binary search tree 
     * whose insertion runs in O(lg n) time.
     * 
     * @param label Must not be null.
     */
    @Override
    public void insert(T label) {
        checkArgument(label != null, "Label must not be null!");
        
        RedBlackNode<T> node = new RedBlackNode(); // Initializes the new node
        node.setLabel(label);
        node.setLeftChild(getSentinel());
        node.setRightChild(getSentinel());
        node.setColor(Color.RED);
        
        RedBlackNode<T> parent = (RedBlackNode) getSentinel();
        RedBlackNode<T> insertPos = isEmpty() ? parent : (RedBlackNode) getRoot();
        while (insertPos != getSentinel()) { // Search for where to insert the node
            parent = insertPos; // Save its current position as its parent
            
            // If the node is < the node we were considering for its insert position
            if (getComparator().compare(node.getLabel(), insertPos.getLabel()) < 0) {
                insertPos = (RedBlackNode) insertPos.getLeftChild(); // Move down the left sub-tree
            } else { // If the node is >= the node we were considering for its insert position
                insertPos = (RedBlackNode) insertPos.getRightChild(); // Move down the righ sub-tree
            }
        }
        
        node.setParent(parent); // Set the node's parent
        if (parent == getSentinel()) { // If the node's parent is absent (the sentinel)
            setRoot(node); // Make the new node the root
        } 
        // If the node is < its parent
        else if (getComparator().compare(node.getLabel(), parent.getLabel()) < 0) {
            parent.setLeftChild(node); // Make the new node its parent's left child
        } else { // If the node is >= its parent
            parent.setRightChild(node); // Make the new node its parent's right child
        }
        
        maintainRedBlackStateAfterInsert(node); // Make sure that the red-black state of the tree is maintained
    }
    
    /**
     * Repairs any violations of the red-black tree properties that may have
     * been caused by the provided node's insertion into the tree.
     * 
     * @param node Must not be null.
     */
    protected void maintainRedBlackStateAfterInsert(RedBlackNode<T> node) {
        checkArgument(node != null, "Node must not be null!");
        
        RedBlackNode<T> parent = (RedBlackNode) node.getParent();
        while (parent.getColor() == Color.RED) { // While the parent is RED
            RedBlackNode<T> grandparent = (RedBlackNode) node.getParent();
            
            // If the node's parent is the left child of its grandparent
            if (grandparent.getLeftChild() == parent) {
                RedBlackNode<T> uncle = (RedBlackNode) grandparent.getRightChild();
                
                if (uncle.getColor() == Color.RED) { // If the uncle is red
                    parent.setColor(Color.BLACK); // Change the parent to black
                    uncle.setColor(Color.BLACK); // Change the uncle to black
                    grandparent.setColor(Color.RED); // Change the grandparent to red
                    node = grandparent; // Move up two tiers of the tree and keep adjusting
                } else { // If the uncle is black
                    if (node == parent.getRightChild()) { // If the node is its parent's right child
                        node = parent;
                        rotateLeft(node); // Rotate the parent node to be the left child of the grandparent
                    }
                    
                    parent = (RedBlackNode) node.getParent();
                    grandparent = (RedBlackNode) parent.getParent();
                    
                    parent.setColor(Color.BLACK); // Change the parent to black
                    grandparent.setColor(Color.RED); // Change the grandparent to red
                    
                    // Rotate the grandparent node so that the parent becomes the new sub-tree root
                    rotateRight(grandparent); 
                }
            } else { // If the node's parent is the left child of its grandparent
                RedBlackNode<T> uncle = (RedBlackNode) grandparent.getLeftChild();
                
                if (uncle.getColor() == Color.RED) { // If the uncle is red
                    parent.setColor(Color.BLACK); // Change the parent to black
                    uncle.setColor(Color.BLACK); // Change the uncle to black
                    grandparent.setColor(Color.RED); // Change the grandparent to red
                    node = grandparent; // Move up two tiers of the tree and keep adjusting
                } else { // If the uncle is black
                    if (node == parent.getLeftChild()) { // If the node is its parent's left child
                        node = parent;
                        rotateRight(node); // Rotate the parent node to be the right child of the grandparent
                    }
                    
                    parent = (RedBlackNode) node.getParent();
                    grandparent = (RedBlackNode) parent.getParent();
                    
                    parent.setColor(Color.BLACK); // Change the parent to black
                    grandparent.setColor(Color.RED); // Change the grandparent to red
                    
                    // Rotate the grandparent node so that the parent becomes the new sub-tree root
                    rotateLeft(grandparent); 
                }
            }
        }
        
        ((RedBlackNode) getRoot()).setColor(Color.RED); // Make sure the root is still red
    }
    
    /**
     * Given a node x with a right child y, makes y the new sub-trees root with 
     * x as y's left child and y's left child as x's right child. 
     * Runs in O(1) time.
     * 
     * @param x Must not be null. Must not be the sentinel. Must have a right child.
     */
    protected void rotateLeft(RedBlackNode<T> x) {
        checkArgument(x != null, "Node must not be null!");
        checkArgument(x != getSentinel(), "Node must not be the sentinel!");
        checkArgument(x.hasRightChild(), "Node must have a right child!");
        
        RedBlackNode<T> y = (RedBlackNode) x.getRightChild(); // Get node Y
        RedBlackNode<T> xParent = (RedBlackNode) x.getParent(); // Get x's parent
        
        x.setRightChild(y.getLeftChild()); // Set y's left child as x's right child
        if (y.getLeftChild() != getSentinel()) { // If y's left child is a real node
            y.getLeftChild().setParent(x); // Update it's parent to x
        }
        
        y.setParent(xParent); // Set x's parent as y's parent
        if (!isEmpty() && x == getRoot()) { // If x was the root of the tree
            setRoot(y); // Make y the new root
        } 
        // If x is its parent's left child
        else if (xParent.hasLeftChild() && xParent.getLeftChild() == x) {
            xParent.setLeftChild(y); // Make y the left child
        } 
        // If x is its parent's right child
        else if (xParent.hasRightChild() && xParent.getRightChild() == x) { 
            xParent.setRightChild(y); // Make y the right child
        } else { // This case indicates the tree is malformed
            throw new IllegalStateException("Node provided is not root and is not"
                    + " its parent's left or right child!");
        }
        
        y.setLeftChild(x); // Set x as y's left child
        x.setParent(y); // Set y as x's parent
    }
    
    /**
     * Given a node x with a left child y, makes y the new sub-trees root with
     * x as y's right child and y's right child as x's left child. 
     * Runs in O(1) time.
     * 
     * @param x Must not be null. Must not be the sentinel. Must have a left child.
     */
    protected void rotateRight(RedBlackNode x) {
        checkArgument(x != null, "Node must not be null!");
        checkArgument(x != getSentinel(), "Node must not be the sentinel!");
        checkArgument(x.hasLeftChild(), "Node must have a left child!");
        
        RedBlackNode<T> y = (RedBlackNode) x.getLeftChild(); // Get node Y
        RedBlackNode<T> xParent = (RedBlackNode) x.getParent(); // Get x's parent
        
        x.setLeftChild(y.getRightChild()); // Set y's right child as x's left child
        if (y.getRightChild() != getSentinel()) { // If y's right child is a real node
            y.getRightChild().setParent(x); // Update it's parent to x
        }
        
        y.setParent(xParent); // Set x's parent as y's parent
        if (x == getRoot()) { // If x was the root of the tree
            setRoot(y); // Make y the new root
        }
        // If x is its parent's left child
        else if (xParent.hasLeftChild() && xParent.getLeftChild() == x) {
            xParent.setLeftChild(y); // Make y the left child
        }
        // IF x is its parent's right child
        else if (xParent.hasRightChild() && xParent.getRightChild() == x) {
            xParent.setRightChild(y); // Make y the right child
        } else { // This case indicates the tree is malformed
            throw new IllegalStateException("Node provided is not root and is not"
                    + " its parent's left or right child!");
        }
        
        y.setRightChild(x); // Set x as y's right child
        x.setParent(y); // Set y as x's parent
    }
    
    /**
     * Replaces the toBeReplaced node with the replacement node.
     * 
     * @param toBeReplaced Must not be null. Must not the be the sentinel.
     * @param replacement Must not be null.
     */
    protected void transplant(RedBlackNode<T> toBeReplaced,
                              RedBlackNode<T> replacement) {
        checkArgument(toBeReplaced != null, "ToBeReplaced must not be null!");
        checkArgument(toBeReplaced != getSentinel(), "ToBeReplaced must not be the sentinel!");
        checkArgument(replacement != null, "Replacement must not be null!");
        
        RedBlackNode<T> parent = (RedBlackNode) toBeReplaced.getParent();
        if (parent == getSentinel()) { // If the node to be replaced is the root
            setRoot(replacement);
        } 
        // If the node to be replaced is its parent left child
        else if (toBeReplaced == parent.getLeftChild()) { 
            parent.setLeftChild(replacement);
        }
        // If the node to be replaced is its parents right child
        else {
            parent.setRightChild(replacement);
        }
        
        replacement.setParent(parent); // Update the replacement's parent
    }
}
