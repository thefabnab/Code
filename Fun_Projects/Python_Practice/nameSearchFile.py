#program where use inputs what file to search within and what to search for

fname = raw_input('Enter the file name: ')
search = raw_input('What would you like to search: ')

try:
    fhand = open(fname)
except:
    print 'File cannot be opened:', fname
    exit()

count = 0

for line in fhand:
    if line.startswith(search) :
        count = count + 1
print ' There were', count, 'occurances of', search, 'within', fname
