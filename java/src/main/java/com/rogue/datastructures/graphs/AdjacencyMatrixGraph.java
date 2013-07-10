package com.rogue.datastructures.graphs;

import static com.google.common.base.Preconditions.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import com.rogue.datastructures.Node;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author R. Matt McCann
 */
public class AdjacencyMatrixGraph<LabelType> implements Graph<LabelType> {
    /** Edges in the graph. */
    private final Table<LabelType, LabelType, Float> adjacencies;
    
    /** 
     * Used to order the edges when retrieving them. Allows the graph to be 
     * evaluated deterministically. 
     */
    private final Comparator<LabelType> comparator;
    
    /** Nodes in the nodes. */
    private final ImmutableSet<LabelType> nodes;
    
    /** 
     * @param adjacencies Must not be null. All keys must exist in nodes.
     * @param comparator Must not be null.
     * @param nodes Must not be null.
     */
    public AdjacencyMatrixGraph(Table<LabelType, LabelType, Float> adjacencies,
            Comparator<LabelType> comparator, ImmutableSet<LabelType> nodes) {
        this.adjacencies = checkNotNull(adjacencies);
        this.comparator = checkNotNull(comparator);
        this.nodes = checkNotNull(nodes);
        
        for (LabelType node : adjacencies.columnKeySet()) { // Verify all columns exist in nodes
            checkArgument(nodes.contains(node), "Expected all columns to exist in nodes, but got"
                    + " %s which is not!", node);
        }
        
        for (LabelType node : adjacencies.rowKeySet()) { // Verify all rows exist in nodes
            checkArgument(nodes.contains(node), "Expected all rows to exist in nodes, but got"
                    + " %s which is not!", node);
        }
    }
    
    @Override
    public ImmutableSet<LabelType> getNodes() {
        return nodes;
    }

    @Override
    public ImmutableList<? extends Edge<LabelType>> getAdjacencies(LabelType nodeLabel) {
        List<Edge<LabelType>> edges = new ArrayList();
        
        Node<LabelType> fromNode = new Node();
        fromNode.setLabel(nodeLabel);
        
        for (Entry<LabelType, Float> edgeData : adjacencies.row(nodeLabel).entrySet()) {
            Node<LabelType> toNode = new Node();
            toNode.setLabel(edgeData.getKey());
            
            Edge<LabelType> edge = new Edge(fromNode, toNode);
            edge.setWeight(edgeData.getValue());
            edges.add(edge);
        }
        
        Collections.sort(edges, new Comparator<Edge<LabelType>>() {
            @Override
            public int compare(Edge<LabelType> edge1, Edge<LabelType> edge2) {
                return comparator.compare(edge1.getToNode().getLabel(), edge2.getToNode().getLabel());
            }
        });
        
        return ImmutableList.copyOf(edges);
    }

    @Override
    public Comparator<LabelType> getComparator() {
        return comparator;
    }
}
