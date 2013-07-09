package com.rogue.datastructures.graphs;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author R. Matt McCann
 */
public abstract class GraphUtils {
    private static enum Color {
        WHITE, GRAY, BLACK
    }
    
    private static class MetaData<LabelType> {
        public Color color = Color.WHITE;
        public float distance = Float.MAX_VALUE;
        public Optional<LabelType> parent = Optional.absent();
    }
    
    /**
     * Searches for the end node starting at startNode using a breadth first
     * search algorithm.
     * 
     * @param <LabelType> Type of label used on the nodes in the graph.
     * @param graph Must not be null. Must contain start node and end node.
     * @param startNode Must not be null. Must exist in graph.
     * @param endNode Must not be null. Must exist in graph.
     * @returns The path from the start node to the end node.
     */
    public static <LabelType> List<LabelType> breadthFirstSearch(Graph<LabelType> graph, 
            LabelType startNode, LabelType endNode) {
        checkArgument(graph != null, "Graph must not be null!");
        checkArgument(startNode != null, "StartNode must not be null!");
        checkArgument(endNode != null, "EndNode must not be null!");
        
        Collection<LabelType> nodes = graph.getNodes();
        Map<LabelType, MetaData<LabelType>> metadata = new HashMap();
        for (LabelType node : nodes) { // Initialize the meta data for the search
            metadata.put(node, new MetaData());
        }
        
        // Initialize the starting node
        MetaData<LabelType> startNodeMetaData = metadata.get(startNode);
        startNodeMetaData.color = Color.GRAY;
        startNodeMetaData.distance = 0.0f;
        
        Queue<LabelType> toBeSearched = new LinkedList();
        while (!toBeSearched.isEmpty()) { // While there are nodes still to be searched
            LabelType currentNode = toBeSearched.remove();
            MetaData<LabelType> currentMetaData = metadata.get(currentNode);
            
            Collection<? extends Edge> edges = graph.getAdjacencies(currentNode);
            for (Edge<LabelType> edge : edges) { // For each edge connected to the node
                LabelType adjacency = edge.getToNode().getLabel(); // Retrieve the current status of the neighbor
                MetaData<LabelType> adjacencyMetaData = metadata.get(adjacency);
                
                if (adjacencyMetaData.color == Color.WHITE) { // If this node has not been seen before
                    adjacencyMetaData.color = Color.GRAY; // Mark it as seen
                    adjacencyMetaData.distance = currentMetaData.distance + edge.getWeight();
                    adjacencyMetaData.parent = Optional.of(currentNode); // Make the current node its parent
                    toBeSearched.add(adjacency); // Save it for later processing
                }
                
                currentMetaData.color = Color.BLACK; // Mark the node as processed
            }
        }
        
        List<LabelType> path = new ArrayList(); // Reconstruct the path
        path.add(0, endNode);
        MetaData<LabelType> currentMetaData = metadata.get(endNode);
        while (currentMetaData.parent.isPresent()) { // While there is a parent
            path.add(0, currentMetaData.parent.get());
            currentMetaData = metadata.get(currentMetaData.parent.get());
        }
        
        return path;
    }
    
    /**
     * Searches for the end node starting at the startNode using a depth first
     * search algorithm.
     * 
     * @param <LabelType> Type of label used on the nodes in the graph.
     * @param graph Must not be null. Must contain start node and end node.
     * @param startNode Must not be null. Must exist in graph.
     * @param endNode Must not be null. Must exist in graph.
     * @returns The path from the start node to the end node.
     */
    public static <LabelType> List<LabelType> depthFirstSearch(Graph<LabelType> graph,
            LabelType startNode, LabelType endNode) {
        
    }
}
