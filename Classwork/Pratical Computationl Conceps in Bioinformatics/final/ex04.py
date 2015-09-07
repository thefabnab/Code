#!/usr/bin/env python3
#A reverse compliment producer. THis program takes user input and outputs its reverse compliment
#It will continue to do so until the program obtains user input which is not A T G or C. The program
#accepts both upper and lowercase DNA bases.

import re

def reverse_compliment(sequence):
    pairing = { 'A' : 'T', 'T':'A','G': 'C', 'C':'G'}
    compliment = []
    reverse_compliment_list = []
    reverse_compliment_string = ""

#Produce compliment
    for base in sequence:
        compliment.append(pairing[base])

#Reverse sequence to produce reverse compliment
    reverse_compliment_list = reversed(compliment)
    reverse_compliment_string = ''.join(reverse_compliment_list)
    print("Your reverse compliment: " + reverse_compliment_string)

#Accepts user input and by default uppercases input then searches for A,T,G or C bases in input, otherwise breaks program loop

while True:
    sequence = raw_input("Please enter your oligo sequence: ")
    sequence = sequence.upper()
    match = re.search(r"[^A|T|G|C]",sequence)
    if match:
        print("{} isn't a DNA base".format(match.group()))
        break
    else:
        reverse_compliment(sequence)

