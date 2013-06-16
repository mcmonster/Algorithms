import unittest
from LinkedBinaryTree import *

class LinkedBinaryTreeTest(unittest.TestCase):
    """ Tests the LinkedBinaryTree class """

    ''' Test building a Linked Binary Tree from an array '''
    def testBuildTree(self):
        data = [1, 2, 3, 4, 5, 6]
        tree = LinkedBinaryTree(data)
        
        # Traverse the tree and verify it is properly built
        self.assertEquals(tree.root.key, 1)
        self.assertEquals(tree.root.parent, None)
        
        leftChild = tree.root.leftChild
        self.assertEquals(leftChild.key, 2)
        self.assertEquals(leftChild.parent, tree.root)
        
        leftLeftGrandChild = leftChild.leftChild
        self.assertEquals(leftLeftGrandChild.key, 4)
        self.assertEquals(leftLeftGrandChild.parent, leftChild)
        self.assertEquals(leftLeftGrandChild.leftChild, None)
        self.assertEquals(leftLeftGrandChild.rightChild, None)

        leftRightGrandChild = leftChild.rightChild
        self.assertEquals(leftRightGrandChild.key, 5)
        self.assertEquals(leftRightGrandChild.parent, leftChild)
        self.assertEquals(leftRightGrandChild.leftChild, None)
        self.assertEquals(leftRightGrandChild.rightChild, None)

        rightChild = tree.root.rightChild
        self.assertEquals(rightChild.key, 3)
        self.assertEquals(rightChild.parent, tree.root)
        self.assertEquals(rightChild.rightChild, None)

        rightLeftGrandChild = rightChild.leftChild
        self.assertEquals(rightLeftGrandChild.key, 6)
        self.assertEquals(rightLeftGrandChild.parent, rightChild)
        self.assertEquals(rightLeftGrandChild.leftChild, None)
        self.assertEquals(rightLeftGrandChild.rightChild, None)

if __name__ == '__main__':
    unittest.main()
