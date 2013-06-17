class BinarySearchTree:
    """ A fast searchable collection that uses a binary tree construct """
   
    class Node:
        """ Node construct for use in the binary search tree """
        
        ''' Initializes the node '''
        def __init__(self):
            self.parent = None
            self.leftChild = None
            self.rightChild = None
            self.key = None

    ''' Initializes the binary search tree by appending the provided items in order '''
    def __init__(self, keys):
        self.root = None

        for key in keys: # For each provided key
            self.insert(key)

    ''' Inserts the provided key into the binary search tree '''
    def insert(self, key):
        searchNode = self.root # Start searching for the insert location at the root
        searchNodeParent = None

        while searchNode != None: # Keep searching until an empty location is found
            searchNodeParent = searchNode # Save the last search node as the parent

            if searchNode.key <= key: # If the key is <= the current node
                searchNode = searchNode.leftChild # Search the left branch for the insert location
            else: # If the key is > the current node
                searchNode = searchNode.rightChild

        searchNode = Node() # Insert a node for the provided key
        searchNode.parent = searchNodeParent
        searchNode.key = key

    ''' Returns the content of the binary tree in order '''
    def inOrderWalk(self):
        return self._inOrderWalk(self.root)

    ''' Recursively returns the contents of the binary tree in order '''
    def _inOrderWalk(self, node):
        if node == None: # If the node does not exist
            return None # Return no items
        
        items = self._inOrderWalk(node.leftChild) # Retrieve the items from the left branch
        items.append(node.key)
        items = self._inOrderWalk(node.rightChild) # Retrieve the items from the right branch

        return items

    ''' Returns the node containing the specified key '''
    def search(self, key):
        return self._search(key, self.root)

    ''' Recusively searches for the node containing the specified key '''
    def _search(self, key, node):
        if (node == None) or (node.key == key): # If the node is found or we have no more nodes
            return node

        if key < node.key: # If the key value is < the current node's value
            return self._search(key, node.leftChild) # Search the left branch
        else: # If the key value is > the current node' value
            return self._search(key, node.rightChild) # Search the right branch

    ''' Returns the minimum value '''
    def min(self):
        node = self.root
        while (node != None) and (node.leftChild != None): # While there is a left branch to explore
            node = node.leftChild # Move down the left branch

        if node != None:
            return node.key # Found the key!
        else:
            return None

    ''' Returns the maximum value '''
    def max(self):
        node = self.root
        while (node != None) and (node.rightChild != None): # While there is a right branch to explore
            node = node.rightChild # Move down the right branch

        if node != None:
            return  node.key # Found the key!
        else:
            return None













