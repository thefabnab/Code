#!/usr/bin/python3
#This program searches within a given file for specific AA sequences and counts
#how many of each there are. It specifically ignores lines beginning with >gi
#as they indicate the beginning of a new gene.

from decimal import *
import operator
getcontext().prec = 3

#Opening files, and initializing the counds and string variables
file = open("/home/jorvis/e_coli_k12_dh10b.faa", 'r')
amino_acids = ["A","R", "N", "D", "C", "E", "Q", "G", "H", "I", "L", "K", 
"M", "F", "P", "S", "T", "W", "Y", "V"]

counts = {}
string = ""
total = 0

#Produces a single string with only the AA sequences
for line in file:
    line.rstrip()
    if line.startswith(">gi"):
        pass
    else:
        string = string + line

#Produces the counts of each reference to each AA using a dictionary
for acid in amino_acids:
    count = string.count(acid)
    counts[acid] = count

#Produces a count of all the number of AA's counted
for numb in counts:
    total = total + counts[numb]
 
#Sorts the counts dictionary via producing a tuple and reverse sorting
#Then running a loop to provide the percentage of the sequence is of each
#of the top 5 AA's counted
sorted_counts = sorted(counts.items(), key=operator.itemgetter(1), reverse = True)
ticker = 0
for AA,count in sorted_counts:
    if ticker < 5:
        percentage = (Decimal(count)/Decimal(total) * 100)
        print(AA + ": " + str(count) + "(" + str(percentage) + "%)")
        ticker = ticker + 1
    else:
        break

file.close()

