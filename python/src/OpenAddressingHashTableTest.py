import unittest
from OpenAddressingHashTable import *

class OpenAddressingHashTableTest(unittest.TestCase):
    ''' Test the insert function '''
    def testInsert(self):
        # Test the linear probing pattern
        table = self._testInsert(OpenAddressingHashTable.linearProbe)
        self.assertEquals(table.table, [0, 3, 6])

        # Test the quadratic probing pattern
        table = self._testInsert(OpenAddressingHashTable.quadraticProbe)
        self.assertEquals(table.table, [0, 6, 3])

        # Test the double hash probing pattern
        table = self._testInsert(OpenAddressingHashTable.doubleHashProbe)
        self.assertEquals(table.table, [0, 6, 3])

    ''' Tests the insert function using the provided probing function '''
    def _testInsert(self, hashFunc):
        table = OpenAddressingHashTable(3)
        table.hashFunc = hashFunc
        table.insert(0)
        table.insert(3)
        table.insert(6)
        return table

    ''' Test the hasElement function '''
    def testHasElement(self):
        self._testHasElement(OpenAddressingHashTable.linearProbe)
        self._testHasElement(OpenAddressingHashTable.quadraticProbe)
        self._testHasElement(OpenAddressingHashTable.doubleHashProbe)

    ''' Tests the hashElement function using the provided probing function '''
    def _testHasElement(self, hashFunc):
        table = OpenAddressingHashTable(3)
        table.hashFunc = hashFunc
        table.insert(0)
        table.insert(2)
        table.insert(4)
        self.assertTrue(table.hasElement(0))
        self.assertTrue(table.hasElement(2))
        self.assertTrue(table.hasElement(4))
        self.assertFalse(table.hasElement(1))

    ''' Tests the remove function '''
    def testRemove(self):
        self._testRemove(OpenAddressingHashTable.linearProbe)
        self._testRemove(OpenAddressingHashTable.quadraticProbe)
        self._testRemove(OpenAddressingHashTable.doubleHashProbe)

    ''' Tests the remove function using the provided probing function '''
    def _testRemove(self, hashFunc):
        table = OpenAddressingHashTable(3)
        table.hashFunc = hashFunc
        table.insert(0)
        table.insert(2)
        table.insert(4)
        self.assertEquals(table.remove(0), 0)
        self.assertEquals(table.remove(2), 2)
        self.assertEquals(table.remove(4), 4)
        self.assertEquals(table.remove(1), None)

if __name__ == '__main__':
    unittest.main()
