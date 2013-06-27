class RodCutter:
    """ Evaluates the most profit that can be obtained from cutting a rod """

    '''
    Initializes the pricing used by the rod cutting algorithms
    @param prices Price paid for rod of length i is prices[i-1]
    @type list
    '''
    def __init__(self, prices):
        self.prices = prices

    '''
    Naively calculates the max revenue by repeatedly solving the same sub-problems
    Runs in 2^n time, eep!

    @param length Total length of the rod to be cut
    @type integer
    @returns Total value of rod after cuts
    @type integer
    '''
    def solveNaively(self, length):
        if length == 0: # If the rod is empty
            return 0 # No value can be obtained from it

        max = -1
        for lengthIter in range(1, length+1): # Calculate the local maxs
            localMax = self.prices[lengthIter-1] + self.solveNaively(length - lengthIter)

            if localMax > max:
                max = localMax

        return max # Return the global max

    '''
    Efficiently calculates the max revenue using memoization aka remember already
    solved problems.
    Runs in polynomial time, hurray!

    @param length Total length of the rod to be cut
    @type integer
    @returns Total value of rod after cuts
    @type integer
    '''
    def solveTopDown(self, length):
        solutions = [None] * length # Initialize the solutions to sub-problems

        return self._solveTopDown(length, solutions)

    '''
    Efficiently calculates the max revenue using memoization aka remember already
    solved problems.
    Runs in polynomial time, hurray!

    @param length Total length of the rod to be cut
    @type integer
    @param solutions Best known solution for rod of length i is solutions[i]
    @type list
    @returns Total value of rod after cuts
    @type integer
    '''
    def _solveTopDown(self, length, solutions):
        if length == 0: # If the rod is empty
            return 0 # No value can be obtained from it
        
        if solutions[length-1] != None: # If the solution to this length is known
            return solutions[length-1]
        else: # If the solution to this length is not known
            max = -1
            for lengthIter in range(1, length+1) # Calculate the local maxes
                localMax = self.prices[lengthIter-1] + self._solveTopDown(length - lengthIter, solutions)

                if localMax > max:
                    max = localMax

        solutions[length-1] = max
        return max # Return the global max

    '''
    Efficiently calculates the max revenue using an iterative bottom-up approach
    Runs in polynomial time, hurray!

    @param length Total length of the rod to be cut
    @type integer
    @returns Total value of rod after cuts
    @type integer
    '''
    def solveBottomUp(self, length):
        solutions = [None] * length # Initialize the sub-problem solutions
        solutions[0] = 0

        for solutionIter in range(1, length+1): # For each sub-length
            max = -1
            for lengthIter in (1, solutionIter+1): # Calculate the local maxes
                max = max(max, self.prices[lengthIter] + solutions[solutionIter-lengthIter])

            solutions[solutionIter-1] = max # Save the sub-problem solution
        return solutions[length-1] # Return the global max
















