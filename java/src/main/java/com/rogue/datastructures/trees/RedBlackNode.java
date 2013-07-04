package com.rogue.datastructures.trees;

import static com.google.common.base.Preconditions.*;

/**
 * Node for the Red Black Tree implementation of the Binary Search Tree.
 * 
 * @author R. Matt McCann
 */
public class RedBlackNode<T> extends BinaryNode<T> {
    /** Color of the node. */
    private Color color = Color.BLACK;
    
    /** Valid colors of a node. */
    public enum Color {
        BLACK, RED
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = checkNotNull(color);
    }
}
