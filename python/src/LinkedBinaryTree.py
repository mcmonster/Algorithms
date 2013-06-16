class LinkedBinaryTree:
    """ A linked implementation of a rooted binary tree """

    class Node:
        """ Node implementation for LinkedBinaryTree """

        ''' Initializes the node '''
        def __init__(self):
            self.parent = None
            self.leftChild = None
            self.rightChild = None
            self.key = None

    ''' Initializes an empty tree '''
    def __init__(self):
        self.root = None

    ''' Initializes a tree assuming the provided array is nearly complete '''
    def __init__(self, items):
        self.root = self._buildNode(items, 0, None)
                
    ''' Recursively (depth-first) builds the tree '''
    def _buildNode(self, items, nodePos, parentNode):
        if nodePos < len(items): # If the node to be built exists
            node = LinkedBinaryTree.Node() # Build the node
            node.key = items[nodePos]
            node.parent = parentNode
            node.leftChild = self._buildNode(items, nodePos * 2 + 1, node)
            node.rightChild = self._buildNode(items, nodePos * 2 + 2, node)
            return node
        else: # If the node to be built is beyond the bounds of the items list
            return None
