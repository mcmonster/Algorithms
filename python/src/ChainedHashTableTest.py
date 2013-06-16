from ChainedHashTable import *
import unittest

class ChainedHashTableTest(unittest.TestCase):
    ''' Tests the insert function '''
    def testInsert(self):
        table = ChainedHashTable(3)
        table.insert(0)
        table.insert(5)
        table.insert(2)

        self.assertEqual(table.buckets[0], [0])
        self.assertEqual(table.buckets[1], [])
        self.assertEqual(table.buckets[2], [2, 5])

    ''' Test the hasElement function '''
    def testHasElement(self):
        table = ChainedHashTable(3)
        table.insert(0)
        table.insert(5)
   
        self.assertTrue(table.hasElement(0))
        self.assertTrue(table.hasElement(5))
        self.assertFalse(table.hasElement(4))

    ''' Test the remove function '''
    def testRemove(self):
        table = ChainedHashTable(3)
        table.insert(0)
        table.insert(5)
        table.insert(2)

        self.assertEquals(table.remove(5), 5)
        self.assertEquals(table.remove(4), None)
        self.assertEquals(table.buckets[0], [0])
        self.assertEquals(table.buckets[1], [])
        self.assertEquals(table.buckets[2], [2])

if __name__ == '__main__':
    unittest.main()
