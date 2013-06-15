import unittest
from LinkedList import *

class LinkedListTest(unittest.TestCase):
    """ Tests the LinkedList class """

    ''' Test the search function '''
    def testSearch(self):
        linkedList = DoubleLinkedList()
        linkedList.insert(5)
        linkedList.insert(6)
        
        node = linkedList.search(5)
        self.assertEquals(node.key, 5)

    ''' Test the insert function '''
    def testInsert(self):
        linkedList = DoubleLinkedList()
        linkedList.insert(5)

        self.assertEquals(linkedList.head.key, 5)

    ''' Test the delete function '''
    def testDelete(self):
        linkedList = DoubleLinkedList()
        linkedList.insert(5)
        linkedList.insert(6)
        linkedList.insert(1)
        node = linkedList.search(6)
        linkedList.delete(node)
     
        self.assertEquals(linkedList.head.nextNode.key, 5)
        self.assertEquals(linkedList.head.nextNode.prevNode.key, 1)

if __name__ == '__main__':
    unittest.main()
