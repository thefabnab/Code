#This is a program to search through the mail and return the most frequent
#senders email

file = open('mbox.txt')

counts = list()

for line in file:
    line = line.rstrip()
    line = line.lower()
    if line.startswith('from') in file:
        counts = line.append()
        print counts
