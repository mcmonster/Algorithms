package com.rogue.algorithms.shortestpath;

import com.rogue.datastructures.graphs.Graph;
import java.util.List;

/**
 * Finds the shortest path from node u to node v.
 * 
 * @author R. Matt McCann
 */
public interface SinglePairShortestPathFinder<LabelType> {
    List<Node<LabelType>> findPath(Graph<LabelType> graph, LabelType node1, LabelType node2);
}
