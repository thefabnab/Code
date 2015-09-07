#!/usr/bin/python3
#This program searches within a given file for specific AA sequences and counts
#how many of each there are. It specifically ignores lines beginning with >gi
#as they indicate the beginning of a new gene.

#Opening files, and initializing the counds and string variables
file = open("/home/jorvis/e_coli_k12_dh10b.faa", 'r')
amino_acids = ["A","R", "N", "D", "C", "E", "Q", "G", "H", "I", "L", "K", 
"M", "F", "P", "S", "T", "W", "Y", "V"]

counts = {}
string = ""

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

#Prints the total counts
for entry in counts:
    print(str(entry) + ":" + str(counts[entry]))

file.close()

