import unittest
from BFS import *
from Graph import *

class GraphTest(unittest.TestCase):
    def setUp(self):
        self.adj = [[None, 4, 1, 3, None]]
        self.adj.append([4, None, 1, None, 2])
        self.adj.append([1, 1, None, 2, 3])
        self.adj.append([3, None, 2, None, 2])
        self.adj.append([None, 2, 3, 2, None])
        self.labels = ['A', 'B', 'C', 'D', 'E']
        self.graph = Graph(self.labels, self.adj)

    def testBFS(self):
        sourceNode = self.graph.nodes[0]
        targetNode = self.graph.nodes[4]
        bfs(self.graph, sourceNode, targetNode)

        path = [targetNode.key] # Reconstruct the solution
        pathNode = targetNode.parent
        while pathNode != None:
            path.insert(0, pathNode.key)
            pathNode = pathNode.parent

        self.assertEquals(['A', 'B', 'E'], path)
        self.assertEquals(2, targetNode.distance)

if __name__ == '__main__':
     unittest.main()
