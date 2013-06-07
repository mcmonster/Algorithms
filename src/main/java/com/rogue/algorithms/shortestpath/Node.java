package com.rogue.algorithms.shortestpath;

import static com.google.common.base.Preconditions.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a node in a graph.
 * 
 * @author R. Matt McCann
 */
public class Node<LabelType> {
    private Map<Node<LabelType>, Integer> adjacencies = new HashMap();
    
    private LabelType label;
    
    @Override
    public boolean equals(Object comparee) {
        if (!(comparee instanceof Node)) {
            return false;
        }

        Node node = (Node) comparee;
        return Objects.equals(label, node.label);
    }
    
    public Map<Node<LabelType>, Integer> getAdjacencies() { return adjacencies; }
    
    public LabelType getLabel() { return label; }
    
    @Override
    public int hashCode() { return Objects.hashCode(label); }
    
    public void setAdjacencies(Map<Node<LabelType>, Integer> adjacencies) {
        this.adjacencies = checkNotNull(adjacencies);
    }
    
    public void setLabel(LabelType label) {
        this.label = checkNotNull(label);
    }
    
    @Override
    public String toString() { return label.toString(); }
}
