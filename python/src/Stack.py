class Stack:
    """ An implementation of a stack using an array """

    ''' Initializes the stack '''
    def __init__(self):
        self.data = []

    ''' Whether or not the stack is empty '''
    def isEmpty(self):
        return (len(self.data) == 0)

    ''' Pushes the item onto the top of the stack '''
    def push(self, item):
        self.data.append(item)

    ''' Removes the top item from the stack '''
    def pop(self):
        assert not self.isEmpty() # Verify that there is an item to pop
        
        return self.data.pop()

