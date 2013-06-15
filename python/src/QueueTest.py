import unittest
from Queue import *

class QueueTest(unittest.TestCase):
    """ Tests the Queue class """

    ''' Test the isEmpty function '''
    def testIsEmpty(self):
        queue = Queue()
        self.assertTrue(queue.isEmpty())
        queue.enqueue(0)
        self.assertFalse(queue.isEmpty())

    ''' Test the enqueue function '''
    def testEnqueue(self):
        queue = Queue()
        queue.enqueue(0)
        queue.enqueue(5)
        queue.enqueue(2)
        self.assertEquals(queue.data, [0, 5, 2])

    ''' Test the dequeue function '''
    def testDequeue(self):
        queue = Queue()
        queue.enqueue(0)
        queue.enqueue(5)
        queue.enqueue(2)
        item = queue.dequeue()
        self.assertEquals(item, 0)
        self.assertEquals(queue.data, [5, 2])

if __name__ == '__main__':
    unittest.main()
