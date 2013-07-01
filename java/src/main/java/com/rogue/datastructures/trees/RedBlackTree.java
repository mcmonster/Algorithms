package com.rogue.datastructures.trees;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;
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
public class RedBlackTree<T> implements Tree<T> {
    /** Used to compare the label values of nodes. */
    private final Comparator<T> comparator;
    
    /** The root of the tree. */
    private Optional<RedBlackNode<T>> root = Optional.absent();
    
    /** Convenience node used instead of an absent node. */
    private final RedBlackNode<T> sentinel = new RedBlackNode();
    
    /**
     * Constructor.
     * 
     * @param comparator Must not be null.
     */
    public RedBlackTree(Comparator<T> comparator) {
        this.comparator = checkNotNull(comparator);
    }
    
    /** {@inheritDocs} */
    @Override
    public void delete(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        node.setLeftChild(sentinel);
        node.setRightChild(sentinel);
        node.setColor(Color.RED);
        
        RedBlackNode<T> parent = sentinel;
        RedBlackNode<T> insertPos = root.isPresent() ? root.get() : sentinel;
        while (insertPos != sentinel) { // Search for where to insert the node
            parent = insertPos; // Save its current position as its parent
            
            // If the node is < the node we were considering for its insert position
            if (comparator.compare(node.getLabel(), insertPos.getLabel()) < 0) {
                insertPos = (RedBlackNode) insertPos.getLeftChild(); // Move down the left sub-tree
            } else { // If the node is >= the node we were considering for its insert position
                insertPos = (RedBlackNode) insertPos.getRightChild(); // Move down the righ sub-tree
            }
        }
        
        node.setParent(parent); // Set the node's parent
        if (parent == sentinel) { // If the node's parent is absent (the sentinel)
            root = Optional.of(node); // Make the new node the root
        } 
        // If the node is < its parent
        else if (comparator.compare(node.getLabel(), parent.getLabel()) < 0) {
            parent.setLeftChild(node); // Make the new node its parent's left child
        } else { // If the node is >= its parent
            parent.setRightChild(node); // Make the new node its parent's right child
        }
        
        maintainRedBlackState(node); // Make sure that the red-black state of the tree is maintained
    }

    /** {@inheritDocs} */
    @Override
    public boolean isEmpty() {
        return !root.isPresent(); // If the root is present the tree must not be null
    }
    
    /**
     * Repairs any violations of the red-black tree properties that may have
     * been caused by the provided node's insertion into the tree.
     * 
     * @param node Must not be null.
     */
    protected void maintainRedBlackState(RedBlackNode<T> node) {
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
                } 
            }
        }
    }
    
    /** {@inheritDocs} */
    @Override
    public Node maximum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /** {@inheritDocs} */
    @Override
    public Node minimum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /** {@inheritDocs} */
    @Override
    public Optional predecessor(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        checkArgument(x != sentinel, "Node must not be the sentinel!");
        checkArgument(x.hasRightChild(), "Node must have a right child!");
        
        RedBlackNode<T> y = (RedBlackNode) x.getRightChild(); // Get node Y
        RedBlackNode<T> xParent = (RedBlackNode) x.getParent(); // Get x's parent
        
        x.setRightChild(y.getLeftChild()); // Set y's left child as x's right child
        if (y.getLeftChild() != sentinel) { // If y's left child is a real node
            y.getLeftChild().setParent(x); // Update it's parent to x
        }
        
        y.setParent(xParent); // Set x's parent as y's parent
        if (x == root.get()) { // If x was the root of the tree
            root = Optional.of(y); // Make y the new root
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
        checkArgument(x != sentinel, "Node must not be the sentinel!");
        checkArgument(x.hasLeftChild(), "Node must have a left child!");
        
        RedBlackNode<T> y = (RedBlackNode) x.getLeftChild(); // Get node Y
        RedBlackNode<T> xParent = (RedBlackNode) x.getParent(); // Get x's parent
        
        x.setLeftChild(y.getRightChild()); // Set y's right child as x's left child
        if (y.getRightChild() != sentinel) { // If y's right child is a real node
            y.getRightChild().setParent(x); // Update it's parent to x
        }
        
        y.setParent(xParent); // Set x's parent as y's parent
        if (x == root.get()) { // If x was the root of the tree
            root = Optional.of(y); // Make y the new root
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
    
    /** {@inheritDocs} */
    @Override
    public Optional search(Object label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /** {@inheritDocs} */
    @Override
    public Optional successor(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
