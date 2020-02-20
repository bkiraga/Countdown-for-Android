from operator import *
from time import sleep, time
from itertools import permutations
#start = time()
class Stack():

    def __init__(self, data):
        self.data = []
    
    def push(self, item):
        self.data.append(item)

    def pop(self):
        return self.data.pop()

target = 785

lsti = [8,75,10,6,25,7]
listOfLists = list(permutations(lsti))

def calculate(oper, total, number):
    if oper == "+":
        return total + number
    elif oper == "-":
        return total - number
    elif oper == "*":
        return total * number
    elif oper == "/":
        return total / number 

def next_op(n):
    stack = []
    stack.append("+" + str(n))
    stack.append("-" + str(n))
    stack.append("*" + str(n))
    stack.append("/" + str(n))
    return stack

def dec(oper, total, number):
    if oper == "+":
        return total - number
    elif oper == "-":
        return total - number
    elif oper == "*":
        return total / number
    elif oper == "/":
        return total * number     

def difference(target, closest, total):
    v = abs(target - closest)
    w = abs(target - total)
    return min(v,w)



operators = {
    "+" : lambda x, y: x + y,
    "-" : lambda x, y: x - y,
    "*" : lambda x, y: x * y,
    "/" : lambda x, y: x / y
}



for lst in listOfLists:

    closest = 0

    second = next_op(lst[1])
    third = next_op(lst[2])
    fourth = next_op(lst[3])
    fifth = next_op(lst[4])
    sixth = next_op(lst[5])

    num = lst[0]

    for s in second:
        s_total = (operators[s[0]])(num,int(s[1:]))
        #closest = difference(target, closest, s_total)
        if s_total == target:
            print("Operator: {}, number: {}, previous: {}".format(s[0],num, int(s[1:]), s_total))
        #print(s_total)
        for t in third:
            t_total = (operators[t[0]])(s_total,int(t[1:]))
            #closest = difference(target, closest, t_total)
            #if closest == target:
            #print(t_total)
            if t_total == target:
                print("Operator: {}, number{}, number: {}, number {}, previous: {}".format(t[0], num, int(s[1:]), int(t[1:]), t_total))
            
            for o in fourth:
                o_total = (operators[o[0]])(t_total,int(o[1:]))
                #closest = difference(target, closest, o_total)
                #if closest == target:
                #print(o_total)
                if o_total == target:
                    print("Operator: {}, number{}, number{}, number: {}, number {}, previous: {}".format(t[0], num, int(s[1:]), int(t[1:]),int(o[1:]), o_total))
                
                for i in fifth:
                    i_total = (operators[i[0]])(o_total,int(i[1:]))
                    #closest = difference(target, closest, i_total)
                    #if closest == target:
                    #print(i_total)
                    if i_total == target:
                        print("Operator: {}, number {}, number {}, number: {}, number {}, number {}, previous: {}".format(t[0], num, int(s[1:]), int(t[1:]),int(o[1:]), int(i[1:]), i_total))
                    
                    for x in sixth:
                        x_total = (operators[x[0]])(i_total,int(x[1:]))
                        #closest = difference(target, closest, x_total)
                        #if closest == target:
                        #print(x_total)
                        if x_total == target:
                            print("Operator: {},number {}, number {}, number: {}, number {}, number {}, number {}, previous: {}".format(t[0], num, int(s[1:]), int(t[1:]),int(o[1:]), int(i[1:]), int(x[1:]), x_total))