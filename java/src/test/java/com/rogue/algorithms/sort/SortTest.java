package com.rogue.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Contains test data sets for testing sort algorithms.
 * 
 * @author R. Matt McCann
 */
public class SortTest {
    private static final Logger logger = Logger.getLogger("SortTest");
    
    private final List<Integer> sorted;
    
    private final List<Integer> unsorted;
    
    public SortTest() {
        unsorted = new ArrayList(Arrays.asList(5, 8, 1, 0, 9, -9));
        sorted = new ArrayList(Arrays.asList(-9, 0, 1, 5, 8, 9));
    }
    
    @Test
    public void testInsertionSort() {
        final Sorter<Integer> insertionSorter = new InsertionSort();
        final List<Integer> toBeSorted = copySortData();
        
        insertionSorter.sort(toBeSorted);
        
        logger.log(Level.INFO, "Expected: {0}", Arrays.toString(sorted.toArray()));
        logger.log(Level.INFO, "Actual: {0}", Arrays.toString(toBeSorted.toArray()));
        for (int dataIter = 0; dataIter < unsorted.size(); dataIter++) {
            final int actualValue = toBeSorted.get(dataIter);
            final int expectedValue = sorted.get(dataIter);
            
            assertEquals("Test Data #" + dataIter, expectedValue, actualValue);
        }
    }
    
    @Test
    public void testMaxHeapSort() {
        final Sorter<Integer> maxHeapSorter = new MaxHeapSort();
        final List<Integer> toBeSorted = copySortData();

        maxHeapSorter.sort(toBeSorted);
        
        logger.log(Level.INFO, "Expected: {0}", Arrays.toString(sorted.toArray()));
        logger.log(Level.INFO, "Actual: {0}", Arrays.toString(toBeSorted.toArray()));
        for (int dataIter = 0; dataIter < unsorted.size(); dataIter++) {
            final int actualValue = toBeSorted.get(dataIter);
            final int expectedValue = sorted.get(dataIter);
            
            assertEquals("Test Data #" + dataIter, expectedValue, actualValue);
        }
    }
    
    @Test
    public void testQuickSort() {
        final Sorter<Integer> quickSorter = new QuickSort();
        final List<Integer> toBeSorted = copySortData();
        
        quickSorter.sort(toBeSorted);
        
        logger.log(Level.INFO, "Expected: {0}", Arrays.toString(sorted.toArray()));
        logger.log(Level.INFO, "Actual: {0}", Arrays.toString(toBeSorted.toArray()));
        for (int dataIter = 0; dataIter < unsorted.size(); dataIter++) {
            final int actualValue = toBeSorted.get(dataIter);
            final int expectedValue = sorted.get(dataIter);
            
            assertEquals("Test Data #" + dataIter, expectedValue, actualValue);
        }
    }
    
    private List<Integer> copySortData() {
        List<Integer> copy = new ArrayList();
        
        for (Integer data : unsorted) {
            copy.add(data);
        }
        
        return copy;
    }
}
