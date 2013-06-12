package com.rogue.algorithms.shortestpath;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import static com.google.common.base.Preconditions.*;

/**
 * Constructs graph objects.
 *  
 * @author R. Matt McCann
 */
public class GraphFactory {
    /**
     * Constructs an adjacency list graph.
     * 
     * @param adjacencyList numNodes by numNodes list of edge weights. X means
     * no edge. Must not be null. 
     * @param numNodes Number of nodes. Must be greater than 0.
     * @return Cosntructed graph.
     */
    public static Graph buildAdjacencyList(String adjacencyList, String labels, int numNodes) {
        String[] edges = adjacencyList.split(" ");
        checkArgument(edges.length == numNodes * numNodes, "Must define as many"
                + " edges as numNodes * numNodes, got edges.length = %s, numNodes"
                + " = %s", edges.length, numNodes);
        
        String[] nodeLabels = labels.split(" ");
        checkArgument(nodeLabels.length == numNodes, "Must define as many node "
                + "labels as numNodes, got nodeLabels.length = %s, numNodes = "
                + "%s", nodeLabels.length, numNodes);
        
        // Construct the empty nodes
        List<Node> nodes = new ArrayList();
        for (int nodeIter = 0; nodeIter < numNodes; nodeIter++) {
            Node node = new Node();
            node.setLabel(nodeLabels[nodeIter]);
            
            nodes.add(node);
        }
        
        // Calculate the adjacencies for each node
        for (int nodeIter = 0; nodeIter < numNodes; nodeIter++) {
            Map<Node, Integer> adjacencies = new HashMap();
            
            // For each edge
            for (int edgeIter = 0; edgeIter < numNodes; edgeIter++) {
                String weight = edges[nodeIter * numNodes + edgeIter];
                
                // If the edge has a weight, add it to the adjacencies
                if (!weight.equalsIgnoreCase("X")) {
                    adjacencies.put(nodes.get(edgeIter), Integer.parseInt(weight));
                }
            }
            
            nodes.get(nodeIter).setAdjacencies(adjacencies);
        }
        
        return new AdjacencyList(nodes);
    }
}
