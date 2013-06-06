package com.rogue.algorithms.sort;

import java.util.List;

/**
 * Sorts using the insertion algorithm.
 * 
 * @author R. Matt McCann
 */
public class InsertionSort<T extends Comparable<T>> implements Sorter<T>{
    /** {@inheritDoc} */
    public void sort(List<T> toBeSorted) {
        // For each item past the first
        for (int itemIter = 1; itemIter < toBeSorted.size(); itemIter++) {
            final T currentItem = toBeSorted.remove(itemIter);
            int searchPos = itemIter - 1;
            
            // Search backwards for the correct position to place the current item
            while (searchPos >= 0) {
                final T searchItem = toBeSorted.get(searchPos);
                
                // If the current item is greater than the search item
                if (currentItem.compareTo(searchItem) > 0) {
                    // The correct position has been found so stop searching
                    break;
                }
                
                // Move back another item
                searchPos--;
            }
            
            // Insert the current item into the selected location
            toBeSorted.add(searchPos + 1, currentItem);
        }
    }
}
