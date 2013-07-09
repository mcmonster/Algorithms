package com.rogue.datastructures.trees;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;
import com.rogue.datastructures.Node;

/**
 *
 * @author R. Matt McCann
 */
public class NaryTree<T> extends Tree<T> {
    /** {@inheritDocs} */
    @Override
    public void delete(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /** {@inheritDocs} */
    @Override
    public void insert(Object label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /** {@inheritDocs} */
    @Override
    public NaryNode<T> maximum() {
        checkState(!isEmpty(), "Tree must not be empty!");
        
        NaryNode<T> currentNode = (NaryNode<T>) getRoot();
        while (currentNode.hasChildren()) { // While the current node has children
            int numChildren = currentNode.getNumChildren();
            currentNode = currentNode.getChild(numChildren - 1); // Move to the right most child
        }
        
        return currentNode;
    }

    /** {@inheritDocs} */
    @Override
    public Node minimum() {
        checkState(!isEmpty(), "Tree must not be empty!");
        
        NaryNode<T> currentNode = (NaryNode) getRoot();
        while (currentNode.hasChildren()) { // While the current node has children
            currentNode = currentNode.getChild(0); // Move to the left most child
        }
        
        return currentNode;    
    }

    /** {@inheritDocs} */
    @Override
    public Optional<? extends Node<T>> predecessor(Node<T> node) {
        NaryNode<T> naryNode = (NaryNode) node;
        
        //if (naryNode.hasChildren()) {
        //    return naryNode
        //}
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
