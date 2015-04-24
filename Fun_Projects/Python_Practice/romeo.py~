#program that opens romeo.txt and reads it line by line, splitting each line.

fname = raw_input('Enter the file name: ')
romeo = list()
try:
    file = open(fname)
except:
    print 'File cannot be opened: ', fname
    exit()
for line in file:
    line = line.rstrip()
    spl = line.split()
    for i in spl:
        if i not in romeo:
            romeo.append[i]
    print romeo
