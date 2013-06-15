'''
Calculates into which bucket an item belongs
'''
def whichBucket(item, maxItem, numBuckets):
    bucket = int(float(item) * float(numBuckets - 1) / float(maxItem))
    
    print "Item: ", item
    print "MaxItem: ", maxItem
    print "NumBuckets: ", numBuckets
    print "Bucket: ", bucket

    return bucket

'''
Sorts the provided list of items using a generalized form of bucket sort (aka slower)
TODO: Do the performance analysis for the generalized form. Good practice!
'''
def bucketSort(items):
    buckets = []
    for bucketIter in range(len(items)): # Initialize the buckets
        buckets.append([])

    maxItem = -1
    for item in items: # Find the max item value, read: generalized form of bucket sort
        if item > maxItem:
            maxItem = item

    while len(items) > 0: # Put each item in its bucket
        item = items.pop(0)
        buckets[whichBucket(item, maxItem, len(buckets))].append(item)

    for bucket in buckets: # Sort each bucket using insertion sort and put it back in the main list
        print "Bucket Before Sort: ", bucket
        insertionSort(bucket)
        print "Bucket After Sort: ", bucket
        items.extend(bucket)
        print "Items: ", items

'''
Sorts the provided items using the bubble sort algorithm
'''
def bubbleSort(items):
    for itemIter in range(0, len(items)): # For each item in the list
        stride = -1
        
        for reverseIter in range(len(items) - 1, itemIter, stride): # Search in reverse
            # If the current item is smaller than the item before it
            if items[reverseIter] < items[reverseIter - 1]:
                # Swap it with the item before it
                items[reverseIter], items[reverseIter - 1] = items[reverseIter - 1], items[reverseIter]

'''
Builds a legal heap by iteratively applying heapify.

Runtime: O(n)
'''
def _buildHeap(items):
    print "__buildHeap()..."
    print "\titems: ", items
    
    firstNonLeafNode, rootNode = (len(items) - 1) / 2, 0
    print "\tFirstNonLeafNode: ", firstNonLeafNode
    print "\tRootNode: ", rootNode
    
    # For each node starting with the first non-leaf node up to and including the root
    print "\tRange[", firstNonLeafNode, ",", rootNode - 1, ")"
    step = -1
    for nodeIter in range(firstNonLeafNode, rootNode - 1, step):
        # Make the current node's 
        _heapify(items, nodeIter, len(items))
    
'''
Sorts a given node into the correct position by recursively sifting it down the heap.

Runtime: O(lg n)
'''
def _heapify(items, pos, heapSize):
    print "__heapify()..."
    print "\titems: ", items
    print "\theapSize: ", heapSize    

    leftChild = pos * 2 + 1
    rightChild = pos * 2 + 2
    largestNode = pos
    print "\tRoot Pos: ", pos
    print "\tRoot Value: ", items[pos]
    print "\tLeftChild Pos: ", leftChild
    if leftChild < heapSize:
        print "\tLeftChild Value: ", items[leftChild]
    else:
        print "\tLeftChild Value: N/A"
    print "\tRightChild Pos: ", rightChild
    if rightChild < heapSize:
        print "\tRightChild Value: ", items[rightChild]
    else:
        print "\tRightChild Value: N/A"
    
    # If the left child exists and is greater than the parent node
    if (leftChild < heapSize) and (items[leftChild] > items[largestNode]):
        largestNode = leftChild # Save it as the largest node so far
        
    # If the right child exists and is greater than the current largest value
    if (rightChild < heapSize) and (items[rightChild] > items[largestNode]):
        largestNode = rightChild # Save it as the largest node
        
    print "\tLargestNodePos: ", largestNode
    print "\tLargestNodeValue: ", items[largestNode]
        
    # If at least one of the children nodes is larger than the parent node
    if largestNode != pos:
        # Swap the parent node with its larger child
        items[pos], items[largestNode] = items[largestNode], items[pos]
        print "\tItems after swap: ", items        

        # Continue sifting the node down the heap
        _heapify(items, largestNode, heapSize)

'''
Sorts the provided items using the heap sort algorithm

Runtime: O(n lg n)
'''
def heapSort(items):
    # Initialize the items into a legal, but unsorted, heap
    _buildHeap(items)
    print "Done building heap: ", items
 
    heapSize = len(items)
    
    # Starting with the last node and working up to the root of the heap
    print "Range: ", len(items) - 1, ",", 0 
    step = -1
    for nodeIter in range(len(items) - 1, 0, step):
        print "Items: ", items
        rootPos = 0
        
        # Swap the current root with the last node in the heap
        items[rootPos], items[nodeIter] = items[nodeIter], items[rootPos]
        print "Items After Swap: ", items

        # Mark the node now last in the heap as correctly sorted
        heapSize = heapSize - 1
        
        # Sort the new root value in order to maintain the state of being a heap
        _heapify(items, rootPos, heapSize)
        

'''
Sorts the provided items using the insertion sort algorithm

Runtime: Theta(n^2)
'''
def insertionSort(items):
    # For each item in past the first
    for itemIter in range(1, len(items)):
        currentItem = items.pop(itemIter)
        searchPos = itemIter - 1
        
        # Search backwards for the correct position to place the current item
        while searchPos >= 0:
            searchItem = items[searchPos]
            
            # If the current item is greater than the search item
            if currentItem > searchItem:
                # The correct position has been found so stop searching
                break
            
            # Move back another item
            searchPos = searchPos - 1
            
        # Insert the current item into the selected location
        items.insert(searchPos + 1, currentItem)
     
'''
Merges the two sub-arrays together in order
'''
def _merge(items, subArrayStart, pivot, subArrayEnd):
    leftSubArrayLength = pivot - subArrayStart + 1 # Determine the lengths of each sub-array
    rightSubArrayLength = subArrayEnd - pivot

    # Merge sort cannot sort in place so created two sub-array working spaces
    leftSubArray = [] # Copy the left sub array items into the working space
    for item in items[subArrayStart:subArrayStart+leftSubArrayLength]:
        leftSubArray.append(item) # Add it to the working space
    rightSubArray = [] # Copy the right sub array items into the working space
    for item in items[pivot+1:pivot+1+rightSubArrayLength]:
        rightSubArray.append(item) # Add it to the working space

    leftSubArray.append(9999999) # Add sentinel values to the end of each sub-array
    rightSubArray.append(9999999) # TODO Find type safe way of defining sentinel    

    leftPos = 0 # Initialize the sub-array search positions
    rightPos = 0

    print "Range: [", subArrayStart, ",", subArrayEnd + 1, ")"
    for itemIter in range(subArrayStart, subArrayEnd + 1): # For each position in the arrayi
        print "=========================="
        print "Items: ", items
        print "ItemIter: ", itemIter
        print "Left: ", leftSubArray
        print "LeftPos: ", leftPos
        print "Right: ", rightSubArray
        print "RightPos: ", rightPos

        if leftSubArray[leftPos] <= rightSubArray[rightPos]: # If the next left sub-array item is smaller
            items[itemIter] = leftSubArray[leftPos] # Add it to the sorted list
            leftPos = leftPos + 1 # Move to the next left sub-array item
        else: # If the next righ sub-array item is smaller
            items[itemIter] = rightSubArray[rightPos] # Add it to the sorted lsit
            rightPos = rightPos + 1 # Move to the next righ sub-array item
    print "Final Items: ", items

'''
Sorts the provided items by recursively merge sorting
'''
def _mergeSort(items, subArrayStart, subArrayEnd):
    if subArrayStart < subArrayEnd: # If the sub-array is not empty
        pivot = (subArrayStart + subArrayEnd) / 2 # Split the array into two sub-array halfs

        _mergeSort(items, subArrayStart, pivot) # Recursively sort the sub-arrays
        _mergeSort(items, pivot + 1, subArrayEnd)
        _merge(items, subArrayStart, pivot, subArrayEnd) # Merge the sub-arrays together

'''
Sorts the provided items using the merge sort algorithm
'''
def mergeSort(items):
    _mergeSort(items, 0, len(items) - 1)
 
'''
Sorts the sub array such that all items below the pivot position are
less than or equal to the item at the pivot position and all values 
in positions past the pivot position are greater than or equal to the
pivot value.
'''
def _partition(items, subArrayStart, subArrayEnd):
    # Validate the input parameters
    assert (0 <= subArrayStart) and (subArrayStart < len(items))
    assert (subArrayStart <= subArrayEnd) and (subArrayEnd < len(items))
    
    print "Items: ", items
    print "Sub Array Start: ", subArrayStart
    print "Sub Array End: ", subArrayEnd    

    # Pick the last item in the sub-array as the pivot value
    pivotValue = items.pop(subArrayEnd)
    pivotPos = subArrayStart - 1
    
    print "Pivot Value: ", pivotValue
    print "Pivot Pos: ", pivotPos

    # For each item in the sub-array except the pivot value at the end
    print "Range[", subArrayStart, ",", subArrayEnd, ")"
    for itemIter in range(subArrayStart, subArrayEnd):
        currentItem = items[itemIter]

        print "Items: ", items
        print "Current Item: ", currentItem

        if currentItem <= pivotValue: # If the current item is <= the pivot value
            pivotPos = pivotPos + 1 # Move the pivot position forward

            currentItem = items.pop(itemIter)
            items.insert(pivotPos, currentItem)

    # Put the pivot value into the selected pivot position
    pivotPos = pivotPos + 1
    items.insert(pivotPos, pivotValue)
    
    print "Items: ", items

    return pivotPos

'''
Internal recursive quick sort implementation
'''
def _quickSort(items, subArrayStart, subArrayEnd):
    assert subArrayStart >= 0 # Validate the input parameters
    assert subArrayEnd < len(items) 

    if subArrayStart < subArrayEnd: # If the sub-array to be sorted is not empty
        # Calculate the pivot point along which to split the sub-array
        pivotPos = _partition(items, subArrayStart, subArrayEnd)

        # Recursively quick-sort the two sub-array
        _quickSort(items, subArrayStart, pivotPos - 1)
        _quickSort(items, pivotPos + 1, subArrayEnd)    

'''
Sorts the provided items using the quicksort algorithm
'''
def quickSort(items):
    _quickSort(items, 0, len(items) - 1)

