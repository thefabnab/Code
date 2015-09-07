#!/usr/bin/python3
#This is a script which takes any given file and searches within it for lines 
#which start with '>gi'. Those lines beginning with that string are the beginning
# of a fasta sequence and once found are added to the count of total number of 
#sequences found.


fasta = open('/home/jorvis1/e_coli_k12_dh10b.faa','r')
count = 0
for line in fasta:
    if line.startswith('>gi'):
        count = count + 1

print("There were {0} sequences within the file".format(count))
