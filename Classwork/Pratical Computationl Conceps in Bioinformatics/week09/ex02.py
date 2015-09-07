#!/usr/bin/python3
#The string counts is converted to a list using the split and , delimiter
#Then it is iterated upon using the for loop to print out a specified format


counts = '8,3,6,2,12,5'

list = counts.split(",")

for i in list:
    sum = 48/int(i)
    print("48/{0} = {1}".format(i,sum))



