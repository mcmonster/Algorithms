'''
Builds a legal heap by iteratively applying heapify.

Runtime: O(n)
'''
def __buildHeap(items):
    print "__buildHeap()..."
    print "\titems: ", items
    
    firstNonLeafNode, rootNode = (len(items) - 1) / 2, 0
    print "\tFirstNonLeafNode: ", firstNonLeafNode
    print "\tRootNode: ", rootNode
    
    # For each node starting with the first non-leaf node up to and including the root
    print "\tRange(", firstNonLeafNode, ",", rootNode - 1, ")"
    step = -1
    for nodeIter in range(firstNonLeafNode, rootNode - 1, step):
        # Make the current node's 
        __heapify(items, nodeIter, len(items))
    
'''
Sorts a given node into the correct position by recursively sifting it down the heap.

Runtime: O(lg n)
'''
def __heapify(items, pos, heapSize):
    print "__heapify()..."
    print "\titems: ", items
    
    leftChild = pos * 2 + 1
    rightChild = pos * 2 + 2
    largestNode = pos
    print "\tRoot: ", pos
    print "\tLeftChild: ", leftChild
    print "\tRightChild: ", rightChild
    
    # If the left child exists and is greater than the parent node
    if (leftChild < heapSize) and (items[leftChild] > items[largestNode]):
        largestNode = leftChild # Save it as the largest node so far
        
    # If the right child exists and is greater than the current largest value
    if (rightChild < heapSize) and (items[rightChild] > items[largestNode]):
        largestNode = rightChild # Save it as the largest node
        
    print "\tLargestNode: ", largestNode
        
    # If at least one of the children nodes is larger than the parent node
    if largestNode != pos:
        # Swap the parent node with its larger child
        items[pos], items[largestNode] = items[largestNode], items[pos]
        
        # Continue sifting the node down the heap
        __heapify(items, largestNode, heapSize)

'''
Sorts the provided items using the heap sort algorithm

Runtime: O(n lg n)
'''
def heapSort(items):
    # Initialize the items into a legal, but unsorted, heap
    __buildHeap(items)
    
    heapSize = len(items)
    
    # Starting with the last node and working up to the root of the heap 
    step = -1
    for nodeIter in range(len(items) - 1, step):
        rootPos = 0
        
        # Swap the current root with the last node in the heap
        items[rootPos], items[nodeIter] = items[rootPos], items[nodeIter]
        
        # Mark the node now last in the heap as correctly sorted
        heapSize = heapSize - 1
        
        # Sort the new root value in order to maintain the state of being a heap
        __heapify(items, rootPos, heapSize)
        

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
        