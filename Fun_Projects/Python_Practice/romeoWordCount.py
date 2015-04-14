#This program takes the romeo text as input and ranks the top most frequent
#words within the doc
import string

file = open('romeo.txt')

counts = dict()
for line in file:
    line = line.translate(None, string.punctuation)
    line = line.lower()
    words = line.split()
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
#sorting dictionary by value

lst = list()
for key, val in counts.items():
    lst.append((val,key))
lst.sort(reverse=True)

for key,val in lst:
    print key,val
