package com.rogue.datastructures.graphs;

import static com.google.common.base.Preconditions.*;
import com.rogue.datastructures.Node;

/**
 * Represents an edge in a graph.
 * 
 * @author R. Matt McCann
 */
public class Edge<LabelType> {
    /** Weight of the edge. */
    private float weight = 1.0f;
    
    /** Node from which the edge starts. */
    private final Node<LabelType> fromNode;
    
    /** Node at which the edge terminates. */
    private final Node<LabelType> toNode;
    
    /**
     * @param fromNode Must not be null.
     * @param toNode Must not be null.
     */
    public Edge(Node<LabelType> fromNode,
                Node<LabelType> toNode) {
        this.fromNode = checkNotNull(fromNode);
        this.toNode = checkNotNull(toNode);
    }

    public Node<LabelType> getFromNode() {
        return fromNode;
    }

    public Node<LabelType> getToNode() {
        return toNode;
    }
    
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
