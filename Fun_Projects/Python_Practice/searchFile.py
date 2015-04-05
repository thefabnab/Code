#program will search through mbox.txt file and output lines that start with
#from

fhand = open('mbox.txt')

for line in fhand:
    line = line.rstrip() #removes whitespace from right side of string
    #conversely could also use: if not line.startswith('From:') then continue
    if line.startswith('From:') :
        print line

