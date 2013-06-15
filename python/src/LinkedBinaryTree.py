class LinkedBinaryTree:
    """ A linked implementation of a rooted binary tree """

    class Node:
        """ Node implementation for LinkedBinaryTree """

        ''' Initializes the node '''
        def __init__(self):
            self.parent = None
            self.leftChild = None
            self.rightChild = None

    ''' Initializes an empty tree '''
    def __init__(self):
        self.root = None

    ''' Initializes a tree assuming the provided array is nearly complete '''
    def __init__(self, items):
        numNodesInLevel = 1 # Doubles with each tier of the tree
        while (len(items) > 0): # While there are more nodes to load
            for nodeIter in range(0, numNodesInLevel): # For each node in the level
                currentNode = Node()
                
                
    ''' Recursively builds the tree '''
    def _buildNode(self, items, nodePos, parentNode):
        if nodePos < len(items): # If the node to be built exists
            node = Node() # Build the node
            node.parent = parentNode
            node.leftChild = _buildNode(self, items, nodePos * 2 + 1, node)

