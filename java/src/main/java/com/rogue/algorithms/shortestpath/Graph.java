package com.rogue.algorithms.shortestpath;

import java.util.Collection;

/**
 * A collection of interconnected nodes.
 * 
 * @author R. Matt McCann
 */
public interface Graph<LabelType> {
    Collection<Node<LabelType>> getNodes();
}
