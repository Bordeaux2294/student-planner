#!/bin/python3

from collections import deque
import math
import os
import random
import re
import sys

#
# Complete the 'countHops' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER m
#  2. INTEGER n
#  3. INTEGER pr
#  4. INTEGER pc
#  5. INTEGER a
#  6. INTEGER b
#  7. INTEGER br
#  8. INTEGER bc
#
# Solution discussed and tested in collaboration with:
# Rene Tim
# Zoe Duncan


result=[]

def canReach(m, n, pr, pc, a, b, br, bc):
    # Write your code here
    def hop(current, vector, m, n):
        hops = []
        if current[0] + vector[0] < m and current[1] + vector[1] < n:
            hops.append((current[0] + vector[0], current[1] + vector[1]))
        if current[0] + vector[1] < m and current[1] + vector[0] < n:
            hops.append((current[0] + vector[1], current[1] + vector[0]))
        if current[0] - vector[0] > -1 and current[1] + vector[1] < n:
            hops.append((current[0] - vector[0], current[1] + vector[1]))
        if current[0] - vector[1] > -1 and current[1] + vector[0] < n:
            hops.append((current[0] - vector[1], current[1] + vector[0]))
        if current[0] + vector[0] < m and current[1] - vector[1] > -1:
            hops.append((current[0] + vector[0], current[1] - vector[1]))
        if current[0] + vector[1] < m and current[1] - vector[0] > -1:
            hops.append((current[0] + vector[1], current[1] - vector[0]))
        if current[0] - vector[0] > -1 and current[1] - vector[1] > -1:
            hops.append((current[0] - vector[0], current[1] - vector[1]))
        if current[0] - vector[1] > -1 and current[1] - vector[0] > -1:
            hops.append((current[0] - vector[1], current[1] - vector[0]))
        return hops
    q = deque()
    s = set()
    start = (pr, pc, 0)
    s.add((start[0], start[1]))
    q.appendleft(start)
    found = False
    while len(q) > 0:
        current = q.pop()
        if current[0] == br and current[1] == bc:
            found = True
            break
        hops = hop(current, (a, b), m, n)
        for new_current in hops:
            if new_current not in s:
                q.appendleft((new_current[0], new_current[1], current[2] + 1))
                s.add(new_current)
    return "Yes" if found else "No"

if __name__ == '_main_':
    first_multiple_input = input().rstrip().split()

    m = int(first_multiple_input[0])

    n = int(first_multiple_input[1])

    second_multiple_input = input().rstrip().split()

    a = int(second_multiple_input[0])

    b = int(second_multiple_input[1])

    third_multiple_input = input().rstrip().split()

    pr = int(third_multiple_input[0])

    pc = int(third_multiple_input[1])

    k = int(input().strip())

    positions = []

    for _ in range(k):
        positions.append(list(map(int, input().rstrip().split())))
    for i in positions:
        result.append(canReach(m,n,pr,pc,a,b,i[0],i[1]))
    print ("\n".join(result))