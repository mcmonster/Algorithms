package com.rogue.algorithms.sort;

import java.util.List;

/**
 * Sorts a provided collection.
 * 
 * @author R. Matt McCann
 */
public interface Sorter<T extends Comparable<T>> {
    void sort(List<T> toBeSorted);
}
