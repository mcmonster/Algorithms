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
        public Optional<Float> finish = Optional.absent();
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
    public static <LabelType> Optional<List<LabelType>> breadthFirstSearch(Graph<LabelType> graph, 
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
                    adjacencyMetaData.distance = currentMetaData.distance + 1;
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
        
        if (path.get(0).equals(startNode)) { // If the path runs from the start to the end
            return Optional.of(path);
        } else { // If no path exists
            return Optional.absent();
        }
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
    public static <LabelType> Optional<List<LabelType>> depthFirstSearch(Graph<LabelType> graph,
            LabelType startNode, LabelType endNode) {
        checkArgument(graph != null, "Graph must not be null!");
        checkArgument(startNode != null, "StartNode must not be null!");
        checkArgument(endNode != null, "EndNode must not be null!");
        
        Collection<LabelType> nodes = graph.getNodes();
        Map<LabelType, MetaData<LabelType>> metadata = new HashMap();
        for (LabelType node : nodes) { // Initialize the meta data for the search
            metadata.put(node, new MetaData());
        }
        
        float time = 0;
        for (LabelType node : nodes) { // For each node in the graph
            MetaData<LabelType> currentMetaData = metadata.get(node);
            
            if (currentMetaData.color == Color.WHITE) { // If the node hasn't been visited
                time = dfsVisit(graph, metadata, node, time); // Visit the node
            }
        }
        
        List<LabelType> path = new ArrayList(); // Reconstruct the path
        path.add(0, endNode);
        MetaData<LabelType> currentMetaData = metadata.get(endNode);
        while (currentMetaData.parent.isPresent()) { // While there is a parent
            path.add(0, currentMetaData.parent.get());
            currentMetaData = metadata.get(currentMetaData.parent.get());
        }
        
        if (path.get(0).equals(startNode)) { // If the path runs from the start to the end
            return Optional.of(path);
        } else { // If no path exists
            return Optional.absent();
        }
    }
    
    /**
     * Visits the node and its neighbors.
     * 
     * @param <LabelType> Type of label used on the nodes in the graph.
     * @param graph Must not be null. Must contain start node and end node.
     * @param metadata Current state of the graph meta data. Must not be null.
     * @param node Node to be visited. Must not be null.
     * @param time Current timestep of the search algorithm. Must be >= 0.
     * @return Time step of the search algorithm after the visit is complete.
     */
    private static <LabelType> float dfsVisit(Graph<LabelType> graph, 
            Map<LabelType, MetaData<LabelType>> metadata, LabelType node, 
            float time) {
        checkArgument(graph != null, "Graph must not be null!");
        checkArgument(metadata != null, "MetaData must not be null!");
        checkArgument(node != null, "Node must not be null!");
        checkArgument(time >= 0, "Time must be >= 0, got %s", time);
        
        time += 1; // Increase the time by one step
        MetaData<LabelType> currentMetaData = metadata.get(node);
        currentMetaData.distance = time; // Record at what time step the node was discovered
        currentMetaData.color = Color.GRAY; // Mark the vertex as having been discovered
        
        Collection<? extends Edge<LabelType>> edges = graph.getAdjacencies(node);
        for (Edge<LabelType> edge : edges) {
            LabelType adjacency = edge.getToNode().getLabel(); // Retrieve the current status of the neighbor
            MetaData<LabelType> adjacencyMetaData = metadata.get(adjacency);
                
            if (adjacencyMetaData.color == Color.WHITE) { // If this node has not been seen before
                adjacencyMetaData.parent = Optional.of(node); // Mark the current node as its parent
                dfsVisit(graph, metadata, adjacency, time); // Visit the node
            }
        }
        
        currentMetaData.color = Color.BLACK; // Mark the node as visited
        time += 1; // Move forward one time step
        currentMetaData.finish = Optional.of(time); // Save the time step when the node was finished being visited
        
        return time;
    }
    
    /**
     * Visits the node and its neighbors, recording when a node has been finished
     * processing (to construct a DAG).
     * 
     * @param <LabelType> Type of label used on the nodes in the graph.
     * @param graph Must not be null. Must contain start node and end node.
     * @param metadata Current state of the graph meta data. Must not be null.
     * @param node Node to be visited. Must not be null.
     * @param time Current timestep of the search algorithm. Must be >= 0.
     * @param dag Must not be null. 
     * @return Time step of the search algorithm after the visit is complete.
     */
    private static <LabelType> float dfsVisit(Graph<LabelType> graph, 
            Map<LabelType, MetaData<LabelType>> metadata, LabelType node, 
            float time, List<LabelType> dag) {
        checkArgument(graph != null, "Graph must not be null!");
        checkArgument(metadata != null, "MetaData must not be null!");
        checkArgument(node != null, "Node must not be null!");
        checkArgument(time >= 0, "Time must be >= 0, got %s", time);
        checkArgument(dag != null, "DAG must not be null!");
        
        time += 1; // Increase the time by one step
        MetaData<LabelType> currentMetaData = metadata.get(node);
        currentMetaData.distance = time; // Record at what time step the node was discovered
        currentMetaData.color = Color.GRAY; // Mark the vertex as having been discovered
        
        Collection<? extends Edge<LabelType>> edges = graph.getAdjacencies(node);
        for (Edge<LabelType> edge : edges) {
            LabelType adjacency = edge.getToNode().getLabel(); // Retrieve the current status of the neighbor
            MetaData<LabelType> adjacencyMetaData = metadata.get(adjacency);
                
            if (adjacencyMetaData.color == Color.WHITE) { // If this node has not been seen before
                adjacencyMetaData.parent = Optional.of(node); // Mark the current node as its parent
                dfsVisit(graph, metadata, adjacency, time); // Visit the node
            }
        }
        
        currentMetaData.color = Color.BLACK; // Mark the node as visited
        time += 1; // Move forward one time step
        currentMetaData.finish = Optional.of(time); // Save the time step when the node was finished being visited
        
        dag.add(node); // Add the node to the DAG
        
        return time;
    }
    
    /**
     * Topologically sorts the graph in such a way that the return list is
     * an directed acyclic graph. DAG!
     * 
     * @param <LabelType> Type of label used on the nodes in the graph.
     * @param graph Must not be null.
     * @return 
     */
    public static <LabelType> List<LabelType> sortTopologically(Graph<LabelType> graph) {
        checkArgument(graph != null, "Graph must not be null!");
        
        Collection<LabelType> nodes = graph.getNodes();
        Map<LabelType, MetaData<LabelType>> metadata = new HashMap();
        for (LabelType node : nodes) { // Initialize the meta data for the search
            metadata.put(node, new MetaData());
        }
        
        List<LabelType> dag = new ArrayList();
        
        float time = 0;
        for (LabelType node : nodes) { // For each node in the graph
            MetaData<LabelType> currentMetaData = metadata.get(node);
            
            if (currentMetaData.color == Color.WHITE) { // If the node hasn't been visited
                time = dfsVisit(graph, metadata, node, time, dag); // Visit the node
            }
        }
        
        return dag;
    }
}
