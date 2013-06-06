package com.rogue.algorithms.sort;

import static com.google.common.base.Preconditions.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sorts using the quick sort algorithm.
 * 
 * @author R. Matt McCann
 */
public class QuickSort<T extends Comparable<T>> implements Sorter<T> {
    private static final Logger logger = Logger.getLogger("QuickSort");
    
    /**
     * Sorts the sub array such that all items below the return pivot position are 
     * less than or equal to the item at the pivot position and all values in
     * positions past the pivot position are greater than or equal to the pivot
     * value.
     * 
     * @param toBeSorted 
     * The array to be sorted. Must not be null.
     * @param subArrayStart 
     * The starting position in the array to apply the sort. Must satisfy 
     * 0 <= subArrayStart < toBeSorted.length
     * @param subArrayEnd 
     * The item in the list to partition the toBeSorted array into two sub-arrays.
     * Must satisfy subArrayStart <= subArrayEnd < toBeSorted.length
     * 
     * @returns Pivot position such that toBeSorted[subArrayStart,pivotPos-1] <=
     * toBeSorted[pivotPos] <= toBeSorted[pivotPos+1, subArrayEnd]
     */
    private int partition(final List<T> toBeSorted, 
                          final int subArrayStart, 
                          final int subArrayEnd) {
        checkArgument((0 <= subArrayStart) && (subArrayStart < toBeSorted.size()), 
                "Expected 0 <= subArrayStart < toBeSorted.length, got subArrayStart"
              + "= %s and toBeSorted.length = %s", subArrayStart, toBeSorted.size());
        checkArgument((subArrayStart <= subArrayEnd) && (subArrayEnd < toBeSorted.size()),
                "Expected subArrayStart < pivotPos < toBeSorted.length, got "
              + "subArrayStart = %s, subArrayEnd = %s, toBeSorted.length = %s", 
              subArrayStart, subArrayEnd, toBeSorted.size());
        
        // Pick the last item in the sub-array as the pivot value
        final T pivotValue = toBeSorted.remove(subArrayEnd);
        int pivotPos = subArrayStart - 1;
        
        // For each item in the sub-array except the pivot value at the end
        for (int itemIter = subArrayStart; itemIter < subArrayEnd; itemIter++) {
            T currentItem = toBeSorted.get(itemIter);
            
            // If the current item is <= the pivot value
            if (currentItem.compareTo(pivotValue) <= 0) {
                // Move the pivot position forward
                pivotPos++;
                
                // Place the current item before the pivot position
                currentItem = toBeSorted.remove(itemIter);
                toBeSorted.add(pivotPos, currentItem);
            }
        }
        
        // Put the pivot value into the selected pivot position
        toBeSorted.add(++pivotPos, pivotValue);
        
        return pivotPos;
    }
    
    /**
     * Quick sorts the toBeSorted array by splitting it into two sub-arrays 
     * which are then recurvisely quick sorted.
     * 
     * @param toBeSorted 
     * The array to be sorted. Must not be null.
     * @param subArrayStart 
     * The starting position in the array to apply the sort. Must satisfy 
     * 0 <= subArrayStart
     * @param subArrayEnd 
     * The item in the list to partition the toBeSorted array into two sub-arrays.
     * Must satisfy subArrayStart <= subArrayEnd
     */
    private void quickSort(final List<T> toBeSorted, 
                           final int subArrayStart, 
                           final int subArrayEnd) {
        checkArgument(0 <= subArrayStart, 
                "Expected 0 <= subArrayStart < toBeSorted.length, got subArrayStart"
              + "= %s and toBeSorted.length = %s", subArrayStart, toBeSorted.size());
        checkArgument(subArrayEnd < toBeSorted.size(),
                "Expected subArrayStart < pivotPos < toBeSorted.length, got "
              + "subArrayStart = %s, subArrayEnd = %s, toBeSorted.length = %s", 
              subArrayStart, subArrayEnd, toBeSorted.size());
        
        // If the sub-array to be sorted is not empty
        if (subArrayStart < subArrayEnd) {
            // Calculate the pivot point along which to split the sub-array
            final int pivotPos = partition(toBeSorted, subArrayStart, subArrayEnd);
            logger.log(Level.INFO, "Pivot Pos: {0}", pivotPos);
            
            // Quick sort the resulting sub-arrays
            quickSort(toBeSorted, subArrayStart, pivotPos - 1);
            quickSort(toBeSorted, pivotPos + 1, subArrayEnd);
        }
    }
    
    /** {@inheritDocs} */
    @Override
    public void sort(final List<T> toBeSorted) {
        checkArgument(toBeSorted != null, "ToBeSorted must not be null!");

        // If there are items in the array to sort
        if (!toBeSorted.isEmpty()) {
            // Sort them with quick sort
            quickSort(toBeSorted, 0, toBeSorted.size() - 1);
        }
    }
}
