class Queue:
    """ An implementation of a queue using an array """

    ''' Initializes the queue '''
    def __init__(self):
        self.data = []

    ''' Whether or not the queue is empty '''
    def isEmpty(self):
        return (len(self.data) == 0)

    ''' Dequeues the item from the queue '''
    def dequeue(self):
        assert not self.isEmpty()

        return self.data.pop(0)

    ''' Enqueues the item into the queue '''
    def enqueue(self, item):
        self.data.append(item)
