#!/usr/bin/env python3
#A BLASTN parser. This parser opens the file in question and searches within it for all matches
#pertaining to the names, scores, and lengths using regular expression to list the top 10 results
#for a desired queuery.

import re

#Open file
with open('example_blast.txt','r') as file:
    text = file.read()

#Regular expression searchs
names = re.findall(r">(.*\|.*\|) ",text)
scores = re.findall(r"Score =.*(.[0-9]) ", text)
lengths = re.findall(r"Length=([0-9]*)",text)
query = re.findall(r"Query= (.*)",text)

#Lists the thing being searched against
print("Query ID: {0}".format(query[0]))
print("Query Length: {0}".format(lengths[0]))

#Output the correct data within the correct format
for a in range(0,10):
    print("Alignment #{0}: Accession = {1} (Length = {2}, Score = {3})".format((a+1),names[a],lengths[a+1],scores[a]))


