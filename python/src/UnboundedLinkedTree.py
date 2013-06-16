from Queue import *

class UnboundedLinkedTree:
    """ 
    A linked implementation of a rooted tree with an unbounded 
    number of branches. 
    """

    class Node:
        """ Node implementation for UnboundedLinkedTree """

        ''' Initializes the node '''
        def __init__(self):
            self.parent = None
            self.leftMostChild = None
            self.nextSibling = None
            self.key = None

    ''' Initializes an empty tree '''
    def __init__(self):
        self.root = None

    ''' Initializes a tree from a list of (key, list(childrenPos)) '''
    def __init__(self, items):
        self.root = self._buildNode(items, 0, None)

    ''' Recursively (depth-first) builds the tree '''
    def _buildNode(self, items, nodePos, parentNode, nextSibling):
        if nodePos < len(items): # If the node to be built exists
            node = UnboundedLinkedTree.Node() # Build the node
            node.key = items[nodePos][0]
            node.parent = parentNode
            node.nextSibling = nextSibling

            # Build and bind the children nodes
            children = items[nodePos][1]
            if len(children) > 0: # If there are children nodes
                node.leftMostChild = self._buildNode(items, 
            #TODO Finish this.

