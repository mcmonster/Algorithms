package com.rogue.datastructures.graphs;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Comparator;

/**
 * A collection of interconnected nodes.
 * 
 * @author R. Matt McCann
 */
public interface Graph<LabelType> {
    /** @return All nodes in the graph. */
    ImmutableSet<LabelType> getNodes();
    
    /** 
     * @param nodeLabel Label of the node of interest. Must not be null.
     * @return All edges leading from the node to its adjacencies. 
     */
    ImmutableList<? extends Edge<LabelType>> getAdjacencies(LabelType nodeLabel);
    
    Comparator<LabelType> getComparator();
}
