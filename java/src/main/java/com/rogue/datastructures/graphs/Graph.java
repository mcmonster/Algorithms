package com.rogue.datastructures.graphs;

import com.rogue.datastructures.Node;
import java.util.Collection;

/**
 * A collection of interconnected nodes.
 * 
 * @author R. Matt McCann
 */
public interface Graph<LabelType> {
    /** @return All nodes in the graph. */
    Collection<LabelType> getNodes();
    
    /** 
     * @param nodeLabel Label of the node of interest. Must not be null.
     * @return All edges leading from the node to its adjacencies. 
     */
    Collection<? extends Edge> getAdjacencies(LabelType nodeLabel);
}
