import unittest
from BinarySearchTree import *

class BinarySearchTreeTest(unittest.TestCase):
    ''' Tests the insert function '''
    def testInsert(self):
        tree = BinarySearchTree([])
        tree.insert(0)
        tree.insert(-5)
        tree.insert(-2)
        tree.insert(5)
        tree.insert(10)

        self.assertEquals(tree.root.parent, None)
        self.assertEquals(tree.root.key, 0)
        
        leftChild = tree.root.leftChild
        self.assertEquals(leftChild.parent, tree.root)
        self.assertEquals(leftChild.key, -5)

        leftRightChild = leftChild.rightChild
        self.assertEquals(leftRightChild.parent, leftChild)
        self.assertEquals(leftRightChild.key, -2)
 
        rightChild = tree.root.rightChild
        self.assertEquals(rightChild.parent, tree.root)
        self.assertEquals(rightChild.key, 5)
       
        rightRightChild = rightChild.rightChild
        self.assertEquals(rightRightChild.parent, rightChild)
        self.assertEquals(rightRightChild.key, 10)

    ''' Tests the inOrderWalk function '''
    def testInOrderWalk(self):
        tree = BinarySearchTree([5, 1, 3, 9, -1, -2])
        self.assertEquals(tree.inOrderWalk(), [-2, -1, 1, 3, 5, 9])

    ''' Tests the search function '''
    def testSearch(self):
        tree = BinarySearchTree([0, -5, -2, 5, 10])
        
        self.assertEquals(tree.search(99), None)

        node = tree.search(-2)
        self.assertEquals(node.parent.key, -5)
        self.assertEquals(node.leftChild, None)
        self.assertEquals(node.rightChild, None)

        node = tree.search(10)
        self.assertEquals(node.parent.key, 5)
        self.assertEquals(node.leftChild, None)
        self.assertEquals(node.rightChild, None)

    ''' Tests the delete function '''
    def testDelete(self):
        # Test the no left sub child case
        tree = BinarySearchTree([1, 2])
        tree.delete(tree.root)
        self.assertEquals(tree.root.key, 2)
        self.assertEquals(tree.root.leftChild, None)
        self.assertEquals(tree.root.rightChild, None)

        # Test the no right sub child case
        tree = BinarySearchTree([2, 1])
        tree.delete(tree.root)
        self.assertEquals(tree.root.key, 1)
        self.assertEquals(tree.root.leftChild, None)
        self.assertEquals(tree.root.rightChild, None)

        # Test the right sub tree successor is right child case
        tree = BinarySearchTree([1, 0, 2])
        tree.delete(tree.root)
        self.assertEquals(tree.root.key, 2)
        self.assertEquals(tree.root.leftChild.key, 0)
        self.assertEquals(tree.root.rightChild, None)

        # Test the right sub tree successor is not right child case
        tree = BinarySearchTree([0, -1, 3, 1])
        tree.delete(tree.root)
        self.assertEquals(tree.root.key, 1)
        self.assertEquals(tree.root.leftChild.key, -1)
        self.assertEquals(tree.root.rightChild.key, 3)

if __name__ == '__main__':
    unittest.main()

























