#!/usr/bin/python3
#This script uses a for loop to search through a predesignated file to find
#words with back to back vowels and prints them.
import re


f = open('vowel.txt', 'r')

for line in f:
    x = re.search(r"[aeiou]{2}",line)
    if x:
        print(line)
    else:
        pass


