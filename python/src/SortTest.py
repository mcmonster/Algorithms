import random
import Sort
import unittest

class SortTest(unittest.TestCase):
    # Before each test
    def setUp(self):
        # Initialize a range of random integers
        self.testData = range(-10, 10)
        random.shuffle(self.testData)

    # Test the bubble sort algorithm
    def testBubbleSort(self):
        # Test that the list is sorted
        data = [1, 5, 9, 1, 3, 2]
        Sort.bubbleSort(data)
        self.assertEqual(data, [1, 1, 2, 3, 5, 9])

    # Test the generalized bucket sort
    def testBucketSort(self):
        data = [1, 5, 9, 1, 3, 2]
        Sort.bucketSort(data)
        self.assertEqual(data, [1, 1, 2, 3, 5, 9])
        
    # Test the insertion sort algorithm
    def testInsertionSort(self):
        # Sort the random numbers
        Sort.insertionSort(self.testData)
        
        # Verify that the numbers were properly sorted
        self.assertEqual(self.testData, range(-10, 10))
        
    # Test the heapify function
    def testHeapify(self):
        # Test that the root node is sorted to the left child
        data = [1, 9, 5]
        Sort._heapify(data, 0, 3)
        self.assertEqual(data, [9, 1, 5])

        # Test that the root node is sorted to the bottom left grand-child
        data = [1, 5, 9, 1, 3, 2]
        Sort._heapify(data, 0, 6)
        self.assertEqual(data, [9, 5, 2, 1, 3, 1])

    # Test the buildHeap function
    def testBuildHeap(self):
        # Test that the list is built into a heap
        data = [1, 5, 9, 1, 3, 2]
        Sort._buildHeap(data)
        self.assertEqual(data, [9, 5, 2, 1, 3, 1])

    # Test the heap sort algorithm
    def testHeapSort(self):
        # Test a small list
        data = [1, 5, 9, 1, 3, 2]
        Sort.heapSort(data)
        self.assertEqual(data, [1, 1, 2, 3, 5, 9])        

    # Test the partition function
    def testPartition(self):
        # Test that the list is properly partitioned
        data = [1, 5, 9, 1, 3, 2]
        pivotPos = Sort._partition(data, 0, 5)
        self.assertEqual(data, [1, 1, 2, 5, 9, 3])
        self.assertEqual(pivotPos, 2)

    # Test the quick sort function
    def testQuickSort(self):
        # Test that the list is properly sorted
        data = [1, 5, 9, 1, 3, 2]
        Sort.quickSort(data)
        self.assertEqual(data, [1, 1, 2, 3, 5, 9])

    # Tests the merge function
    def testMerge(self):
        # Test that the two sub-arrays created from an even number of items are properly merged
        data = [1, 9, 2, 2]
        Sort._merge(data, 0, 1, 3)
        self.assertEqual(data, [1, 2, 2, 9])

        # Test that the two sub-arrays created from an off number of items are properly merged
        data = [3, 9, 2, 1, 7]
        Sort._merge(data, 0, 2, 4)
        self.assertEqual(data, [1, 3, 7, 9, 2])

    # Test the merge sort function
    def testMergeSort(self):
        # Test that the list is properly sorted when using an even number of items
        data = [1, 5, 9, 1, 3, 2]
        Sort.mergeSort(data)
        self.assertEqual(data, [1, 1, 2, 3, 5, 9])

        # Test that the list is properly sorted when using an odd number of items
        data = [1, 5, 9, 1, 3, 2, 7]
        Sort.mergeSort(data)
        self.assertEqual(data, [1, 1, 2, 3, 5, 7, 9])

if __name__ == '__main__':
    unittest.main()
