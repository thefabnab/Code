#!/usr/bin/python3
#This script searches within a given string and returns the first codon sequence
#namely the first 3 nucleotides in the sequence.

sequence = 'AUGCTTATATAT'

def start_codon(seq):
    codon = seq[0:3]
    return codon

print(start_codon(sequence))


