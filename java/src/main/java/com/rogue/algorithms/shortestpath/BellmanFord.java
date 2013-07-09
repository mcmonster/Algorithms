package com.rogue.algorithms.shortestpath;

import com.rogue.datastructures.graphs.Graph;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements a shortest path finding using the Bellman-Ford algorithm.
 * 
 * @author R. Matt McCann
 */
public class BellmanFord<LabelType> implements SinglePairShortestPathFinder<LabelType> {  
    @Override
    public List<Node<LabelType>> findPath(Graph<LabelType> graph, 
                                          LabelType startNode, 
                                          LabelType endNode) {
        Map<LabelType, SingleSourceStats> singleSources = 
                initializeSingleSources(graph, startNode);
        
        return null;
    }
    
    private Map<LabelType, SingleSourceStats> initializeSingleSources(
            final Graph graph, LabelType startNode) {
        Map<LabelType, SingleSourceStats> singleSources = new HashMap();
        
        // Initialize the single sources as infinite distance and no next node
        Collection<Node<LabelType>> nodes = graph.getNodes();
        for (Node<LabelType> node : nodes) {
            singleSources.put(node.getLabel(), new SingleSourceStats(Integer.MAX_VALUE, null));
        }
        
        // Initialize the starting node as having a 0 distance
        singleSources.get(startNode).distance = 0;
        
        return singleSources;
    }
    
    private class SingleSourceStats {
        public int distance;
        
        public Node nextNode;
        
        public SingleSourceStats(int distance, Node nextNode) {
            this.distance = distance;
            this.nextNode = nextNode;
        }
    }
}
