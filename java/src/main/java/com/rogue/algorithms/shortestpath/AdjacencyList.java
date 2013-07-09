package com.rogue.algorithms.shortestpath;

import com.rogue.datastructures.graphs.Graph;
import static com.google.common.base.Preconditions.*;
import java.util.Collection;

/**
 * Adjacency list implementation of Graph.
 * 
 * @author R. Matt McCann
 */
public class AdjacencyList<LabelType> implements Graph<LabelType> {
    private Collection<Node<LabelType>> nodes;
    
    public AdjacencyList(Collection<Node<LabelType>> nodes) {
        this.nodes = checkNotNull(nodes);
    }
    
    @Override
    public Collection<Node<LabelType>> getNodes() {
        return nodes;
    }
}
