import unittest
from Stack import *

class StackTest(unittest.TestCase):
    """ Tests the Stack class """
   
    ''' Test the isEmpty function '''
    def testIsEmpty(self):
        stack = Stack()
        self.assertTrue(stack.isEmpty())
        stack.push(0)
        self.assertFalse(stack.isEmpty())

    ''' Test the push function '''
    def testPush(self):
        stack = Stack()
        stack.push(0)
        stack.push(5)
        stack.push(2)
        self.assertEquals(stack.data, [0, 5, 2])

    ''' Test the pop function '''
    def testPop(self):
        stack = Stack()
        stack.push(0)
        stack.push(5)
        stack.push(2)
        item = stack.pop()
        self.assertEquals(item, 2)
        self.assertEquals(stack.data, [0, 5])

if __name__ == '__main__':
    unittest.main()
