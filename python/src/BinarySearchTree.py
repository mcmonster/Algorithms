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
    def __init__(self, keys = []):
        self.root = None

        for key in keys: # For each provided key
            self.insert(key)

    ''' Replaces the toBeReplaced node with the replacement node '''
    def _transplant(self, toBeReplaced, replacement):
        if toBeReplaced.parent == None: # If the node to be replaced is the root
            self.root = replacement # Make the replacement the root node
        elif toBeReplaced == toBeReplaced.parent.leftChild: # If the node to be replaced is its parents left sub-child
            toBeReplaced.parent.leftChild = replacement # Make the replacement node the left sub-child
        else: # If the node to be replaced is its parents right sub-child
            toBeReplaced.parent.rightChild = replacement # Make the replacement node the right sub-child

        if replacement != None: # If the replacement node exists
            replacement.parent = toBeReplaced.parent # Keep the parent

    ''' Delete the provided node from the tree '''
    def delete(self, node):
        if node.leftChild == None: # If the node doesn't have a left child
            self._transplant(node, node.rightChild) # Replace the node with its right child
        elif node.rightChild == None: # If the node doesn't have a right child
            self._transplant(node, node.leftChild) # Replace the node with its left child
        else:
            rightSubTree = BinarySearchTree() # This seems unwieldy. TODO Simpler way
            rightSubTree.root = node.rightChild
            minNode = rightSubTree.minNode()

            if minNode.parent != node: # If the min right sub-tree value is not the node's immediate child
                self._transplant(minNode, minNode.rightChild) # Replace min node with it's right child
                minNode.rightChild = node.rightChild # Inherit the right sub-tree
                minNode.rightChild.parent = minNode

            self._transplant(node, minNode) # Replace the node with the min node
            minNode.leftChild = node.leftChild # Inherit the left sub-tree
            minNode.leftChild.parent = minNode

    ''' Inserts the provided key into the binary search tree '''
    def insert(self, key):
        newNode = BinarySearchTree.Node() # Set up what we can in the node
        newNode.key = key
        newNode.parent = None

        searchNode = self.root
        while searchNode != None: # Search for the correct position to insert the node
            newNode.parent = searchNode
            
            if key <= searchNode.key:
                searchNode = searchNode.leftChild # Search the left branch
            else:
                searchNode = searchNode.rightChild # Search the right branch

        if newNode.parent == None: # If no parent node was established
            self.root = newNode # The tree was empty so make this the root
        elif newNode.key <= newNode.parent.key: # If the inserted key is less than the parent key
            newNode.parent.leftChild = newNode # Make the inserted node the parent's left child
        else: # If the inserted key is greater than the parent key
            newNode.parent.rightChild = newNode # Make the inserted node the parent's right child

    ''' Returns the content of the binary tree in order '''
    def inOrderWalk(self):
        return self._inOrderWalk(self.root)

    ''' Recursively returns the contents of the binary tree in order '''
    def _inOrderWalk(self, node):
        if node == None: # If the node does not exist
            return None # Return no items
       
        items = [] 
        leftItems = self._inOrderWalk(node.leftChild) # Retrieve the items from the left branch
        if leftItems != None: # If there are left items to append
            items.extend(leftItems) # Append them
        items.append(node.key)
        rightItems = self._inOrderWalk(node.rightChild) # Retrieve the items from the right branch
        if rightItems != None: # If there are right items to append
            items.extend(rightItems) # Append them

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

    ''' Returns the minimum value in the tree '''
    def min(self):
        minNode = self.minNode()
        if minNode != None: # If the tree was not empty
            return minNode.key
        else:
            return None

    ''' Returns the node containing the minimum value '''
    def minNode(self):
        node = self.root
        while (node != None) and (node.leftChild != None): # While there is a left branch to explore
            node = node.leftChild # Move down the left branch

        return node

    ''' Returns the maximum value in the tree '''
    def max(self):
        maxNode = self.maxNode()
        if maxNode != None: # If the tree was not empty
            return maxNode.key
        else: 
            return None

    ''' Returns the node containing the maximum value '''
    def maxNode(self):
        node = self.root
        while (node != None) and (node.rightChild != None): # While there is a right branch to explore
            node = node.rightChild # Move down the right branch

        return node

