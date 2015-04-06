#this program creates a file and outputs a few strings into it

fout = open('output.txt', 'w') #the w designates write

print fout

line1 = 'This is what I\'m writing to the file.\n'
#\n is required to designate new line...line1 will be appended to doc
fout.write(line1)

line2 = 'what\'s the deal with airline food?'
fout.write(line2)

fout.close() #ensures data is written to disk

fRead = open('output.txt')

for line in fRead:
    line = line.rstrip()
    print line
