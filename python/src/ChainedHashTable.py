class ChainedHashTable:
    """ 
    A hash table implementation that uses chaining to resolve collisions
    TODO: Implement this with support for providing custom hash functions
    """

    ''' Initializes the hash table '''
    def __init__(self, numBuckets):
        self.buckets = []
        for bucketIter in range(numBuckets): 
            self.buckets.append([])

    ''' Inserts the provided item into the hash table '''
    def insert(self, item):
        bucket = item % len(self.buckets) # Determine which bucket it goes into
        self.buckets[bucket].insert(0, item) # Put the item in the front of the bucket

    ''' Returns whether or not the provided item exists in the hash table '''
    def hasElement(self, item):
        bucket = item % len(self.buckets) # Determine which bucket it goes into
        
        bucketContents = self.buckets[bucket] # Search the bucket's contents for the item
        for bucketContent in bucketContents:
            if bucketContent == item: # If this bucket content equals the item
                return True # Found the item in the hash table
        
        return False # Did not find the item in the hash table

    ''' Removes the provided item from the hash table if it exists '''
    def remove(self, item):
        bucket = item % len(self.buckets) # Determine which bucket the item would be in

        bucketContents = self.buckets[bucket] # Search the bucket's contents for the item
        for contentIter in range(len(bucketContents)):
            if bucketContents[contentIter] == item: # If this bucket content equals the item
                return bucketContents.pop(contentIter) # Remove the item from the hash table

        return None # Item did not exist in the hash table

