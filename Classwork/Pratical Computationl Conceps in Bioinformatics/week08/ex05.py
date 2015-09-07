#!/usr/bin/python3
#This program is going to count the number of instances of a specific 
#substring within a givin nucleotide sequence

dna = 'ATGGAACCAACGTCAGTGACTTCGTCAG'
motif = 'AA'
counts = dna.count(motif)

print("There are " + str(counts) + " instances of the motif '" + motif + "'"
)



