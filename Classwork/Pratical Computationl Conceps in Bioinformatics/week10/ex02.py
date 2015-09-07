#!/usr/bin/python3


f = open('users.txt', 'r')
x = []
for line in f:
    x = line.rsplit()
    who = x[0]
    where = x[1]
    when_and_IP = x[2] + " " +  x[3] + " " +  x[4]
    print(who + " on " + where + " at " + when_and_IP + ".")

