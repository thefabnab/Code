#reading the mbox.txt file and printing out the number of files

fhand = open('mbox.txt')
count = 0
for line in fhand:
    count = count + 1
print 'Line Count: ', count
