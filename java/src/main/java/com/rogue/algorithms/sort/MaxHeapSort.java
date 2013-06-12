package com.rogue.algorithms.sort;

import java.util.List;

/**
 * Heap sorts from largest to smallest.
 * 
 * @author R. Matt McCann
 */
public class MaxHeapSort<T extends Comparable<T>> extends HeapSort<T> {
    /** {@inheritDoc} */
    @Override
    protected void buildHeap(List<T> toBeHeaped) {
        // Build the heap from the bottom up
        for (int nodeIter = toBeHeaped.size() / 2; nodeIter >= 1; nodeIter--) {
            heapify(toBeHeaped, nodeIter, toBeHeaped.size());
        }
    }
    
    /** {@inheritDoc} */
    @Override
    protected void heapify(final List<T> toBeHeaped, final int root, final int heapSize) {
        final int leftChild = 2 * root;
        final T   nodeValue = toBeHeaped.get(root - 1);
        final int rightChild = 2 * root + 1;
        
        int largestValue = root;
        
        // If the left child exists and is greater than the node
        if ((leftChild <= heapSize) && 
            (toBeHeaped.get(leftChild - 1).compareTo(nodeValue) > 0)) {
            largestValue = leftChild;
        }
        
        // If the right child exists and is greater than the current largest node
        if ((rightChild <= heapSize) &&
            (toBeHeaped.get(rightChild - 1).compareTo(toBeHeaped.get(largestValue - 1)) > 0)) {
            largestValue = rightChild;
        }
        
        // If the root is not the largest value
        if (largestValue != root) {
            final T childValue = toBeHeaped.remove(largestValue - 1);
            final T rootValue = toBeHeaped.remove(root - 1);
            
            // Swap the root with its largest child
            toBeHeaped.add(root - 1, childValue);
            toBeHeaped.add(largestValue - 1, rootValue);
            
            // Sort the swapped child node's sub-tree
            heapify(toBeHeaped, largestValue, heapSize);
        }
    }
}
