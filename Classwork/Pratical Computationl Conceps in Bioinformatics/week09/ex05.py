#!/usr/bin/python3
#This script has a fasta_sequence function defined which takes as an input
#a filename variable, opens it up, and searches through the file line by line
#for the term: '>gi'. That term indicates the beginning of a new fasta sequence
#The function then returns that value and I wrote a print statment to format it.

file = '/home/jorvis1/e_coli_k12_dh10b.faa'

def fasta_sequence(filename):
    fasta = open(filename,'r')
    count = 0
    for line in fasta:
        if line.startswith('>gi'):
            count = count + 1
    return count

seq_count = fasta_sequence(file)

print("There were {0} sequences within the file.".format(seq_count))
