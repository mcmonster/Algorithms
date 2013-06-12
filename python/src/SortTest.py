import random
import Sort
import unittest

class Sortest(unittest.TestCase):
    # Before each test
    def setUp(self):
        # Initialize a range of random integers
        self.testData = range(-10, 10)
        random.shuffle(self.testData)
        
    # Test the insertion sort algorithm
    def testInsertionSort(self):
        # Sort the random numbers
        Sort.insertionSort(self.testData)
        
        # Verify that the numbers were properly sorted
        self.assertEqual(self.testData, range(-10, 10))
        
    # Test the heap sort algorithm
    def testHeapSort(self):
        # Sort the random numbers
        Sort.heapSort(self.testData)
        
        # Verify that the numbers were properly sorted
        self.assertEqual(self.testData, range(-10, 10))
        
if __name__ == '__main__':
    unittest.main()