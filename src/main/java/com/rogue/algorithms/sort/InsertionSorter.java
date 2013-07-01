package com.rogue.algorithms.sort;

import java.util.List;

/**
 * Sorts using the insertion sort algorithm.
 * 
 * @author R. Matt McCann
 */
public class InsertionSorter<T extends Comparable<T>> implements Sorter<T> {
    public void sort(List<T> toBeSorted) {
        // For each node starting with the second
        for (int nodeIter = 1; nodeIter < toBeSorted.size(); nodeIter++) {
            T currentValue = toBeSorted.get(nodeIter);
            int searchPos = nodeIter - 1;
            
            // While there are more nodes to search
            while (searchPos >= 0)
        }
    }
}
