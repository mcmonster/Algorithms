class DoubleLinkedList:
    """ An implementation of a doubley-linked list """
    
    class Node:
        """ A doubley-linked node """
        
        ''' Initializes the node '''
        def __init__(self):
            self.prevNode = None
            self.nextNode = None
            self.key = None

    ''' Initializes the list as empty '''
    def __init__(self):
        self.head = None

    ''' Returns the first node whose key is equal to the item '''
    def search(self, item):
        node = self.head # Start searching at the head of the list

        # While there are more nodes and a match is not found
        while (node is not None) and (node.key != item):
            node = node.nextNode # Move to the next node

        return node

    ''' Inserts the item into the head of the list '''
    def insert(self, item):
        newNode = DoubleLinkedList.Node() # Set up the new node
        newNode.nextNode = self.head
        newNode.key = item
        
        if self.head is not None: # If there is a head node to refer to
            self.head.prevNode = newNode # Linked the old head to the new
        
        self.head = newNode # Set the insert item as the new head node

        print "Head.Key: ", self.head.key
        print "Head.NextNode: ", self.head.nextNode
        print "Head.PrevNode: ", self.head.prevNode

    ''' Removes the provided node from the list '''
    def delete(self, node):
        assert isinstance(node, DoubleLinkedList.Node)

        if node.prevNode is not None: # If the node has a previous node
            # Link the previous node to the current node's next node
            node.prevNode.nextNode = node.nextNode
        else: # If this node doesn't have a previous node, meaning it's the head
            self.head = node.nextNode # Make the next node the new head

        if node.nextNode is not None: # If the next node exists
            # Link the next node to the previous node
            node.nextNode.prevNode = node.prevNode

