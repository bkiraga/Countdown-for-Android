import random
from itertools import permutations

class Node:
	def __init__(self, data, add = None, sub = None, mult = None, div = None):
		self.add = add
		self.sub = sub
		self.mult = mult
		self.div = div
		self.data = data

class Tree:
	# not finished
	def __init__(self):
		self.root = None

def set_goal():
    goal = random.randint(1,1000)
    return goal

def set_numbers():
    possible_nos = [1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,25,50,75,100]
    numbers = []
    i = 0
    while i < 6:
	    index = random.randint(0, len(possible_nos)-1)
	    numbers.append(possible_nos[index])
	    possible_nos.pop(index)
	    i = i + 1
    return numbers

def solver(goal, numbers):
    # not finished
    perm = list(permutations(numbers, 2))
    print(perm)

def main():

    goal = set_goal()
    print(goal)
    numbers = set_numbers()
    print(numbers)

    solver(goal,numbers)

    	
if __name__ == "__main__":
	main()