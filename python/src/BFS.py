from Queue import *

def bfs(graph, node1, node2):
    for node in graph.nodes: # Initialize the nodes as unexplored
        node.distance = 999999 # TODO Make this real max
        node.color = 'WHITE'
        node.parent = None

    node1.distance = 0 # Initialize 
    node1.color = 'GRAY'

    frontier = Queue() # Set up the frontier nodes queue
    frontier.enqueue(node1)

    while not frontier.isEmpty(): # While there are frontier nodes to process
        frontierNode = frontier.dequeue()
    
        for edge in frontierNode.edges:
            neighbor = edge.destNode # Ignoring link weight
            
            if neighbor.color == 'WHITE': # If this node has not been seen before
                neighbor.color = 'GRAY' # Mark it as a frontier node
                neighbor.distance = frontierNode.distance + 1
                neighbor.parent = frontierNode
               
                frontier.enqueue(neighbor) # Process the node

        frontierNode.color = 'BLACK' # Mark it as processed

def dfs(graph, node1, node2):
    for node in graph.nodes: # Initialize the nodes as unexplored
        node.distance = 999999 # TODO Make this real max
        node.color = 'WHITE'
        node.parent = None

    time = 0
    for node in graph.nodes:
        if node.color == 'WHITE':
            _dfsVisit(graph, node, time)

def _dfsVisit(graph, node, time):
    time = time + 1
    
    node.d = time
    node.color = 'GRAY'
    for edge in node.edges:
        neighbor = edge.destNode

        if neighbor.color == 'WHITE':
            neighbor.parent = node
            _dfsVisit(graph, neighbor, time)
    node.color = 'BLACK'
    time = time + 1
    node.time = time
