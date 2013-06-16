class OpenAddressingHashTable:
    """
    A hash table implementation that uses an open addressing function
    to organize items within the hash table.
    TODO: Implement this with support for providing custom open addressing
    hash functions.
    """

    ''' Initializes the hash table '''
    def __init__(self, size):
        self.table = [None] * size # Allocate a table of the specified size
        self.hashFunc = OpenAddressingHashTable.linearProbe

    ''' Calculates the position of the hash using linear probing '''
    def linearProbe(self, key, probeIter):
        return (key + probeIter) % len(self.table)

    ''' Calculates the position of the hash using quadratic probing '''
    def quadraticProbe(self, key, probeIter):
        # TODO: Pick the constants to be optimal
        return (key + 2 * probeIter + 3 * probeIter * probeIter) % len(self.table)

    ''' Calculates the position of the hash using a relatively prime double-hash '''
    def doubleHashProbe(self, key, probeIter):
        # TODO: Pick the constants to be optimal
        hash1 = key % len(self.table)
        hash2 = 1 + key % (len(self.table) - 1)
        return (hash1 + probeIter * hash2) % len(self.table)       

    ''' Inserts the provided item into the hash table '''
    def insert(self, item):
        probeIter = 0
        while probeIter < len(self.table): # Search for the correct placement
            pos = self.hashFunc(self, item, probeIter) # Calculate the current probe position
           
            if self.table[pos] == None: # If the probe position is empty
                self.table[pos] = item # Place the item at the probe position
                return # Done searching for the correct placement

            probeIter = probeIter + 1 # Search the next probe position

        raise OverflowError # Ran out of space in the hash table

    ''' Returns whether or not the provided item exists in the hash table '''
    def hasElement(self, item):
        probeIter = 0
        while probeIter < len(self.table): # Search along the probe pattern
            pos = self.hashFunc(self, item, probeIter) # Calculate the current probe position

            if self.table[pos] == item: # If the probe position contains the item
                return True # Found the item
            elif self.table[pos] == None: # If the probe position is empty
                return False # The rest of the probe pattern will be empty            

            probeIter = probeIter + 1 # Search the next probe position

        return False # Did not find the item

    ''' Removes the provided item from the hash table if it exists '''
    def remove(self, item):
        probeIter = 0
        while probeIter < len(self.table): # Search along the probe pattern
            pos = self.hashFunc(self, item, probeIter) # Calculate the current probe position
 
            if self.table[pos] == item: # If the probe position contains the item
                toBeRemoved = self.table[pos] # Remove the item and replace it with a sentinel
                self.table[pos] = -1 # TODO Temporary sentinal that forces items to be positive
                return toBeRemoved
            elif self.table[pos] == None: # If the probe position is empty
                return None # The rest of the probe pattern will be empty

            probeIter = probeIter + 1 # Search the next probe position

        return None # Did not find the item

