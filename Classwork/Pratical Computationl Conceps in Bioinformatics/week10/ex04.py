#!/usr/bin/python3
#This script takes any given sentence with two adjectives separated by a "," and
#switches those two words using a regular expression search to find the two
#adjectives

import re

x = "The quick, brown fox jumped over some other non-descript animal."
y = x.split()
count = 0
for word in y:
    count = count + 1
    if re.search(r",$",word):
        adjective1 = word
        adjective2 = y[count]
    else:
        pass
print("Original string: \n" + x)
z = x.replace(adjective2, "temp")
z = z.replace(adjective1[:-1], adjective2)
z = z.replace("temp", adjective1[:-1])
print("Modified string: \n" + z)
