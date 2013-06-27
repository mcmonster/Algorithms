class Graph:
    """ Creates an adjacency list graph """

    class Node:
        def __init__(self, key):
            self.key = key
            self.edges = []

    class Edge:
        def __init__(self, weight, destNode):
            self.weight = weight
            self.destNode = destNode

    ''' 
    Initializes the graph using the provided adjacency matrix.
    
    @param keys Key value of each node.
    @param adjacencies Square matrix of edge weights. None is no edge
    @type list of lists
    '''
    def __init__(self, keys, adjacencies):
        self.nodes = [] # Initialize the nodes
        for key in keys:
            self.nodes.append(Graph.Node(key))

        for node1 in range(len(adjacencies)):
            for node2 in range(len(adjacencies[node1])):
                if adjacencies[node1][node2] != None:
                    self.nodes[node1].edges.append(Graph.Edge(adjacencies[node1][node2], self.nodes[node2]))
