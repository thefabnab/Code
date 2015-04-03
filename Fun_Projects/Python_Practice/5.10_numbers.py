#Program that takes in numbers input until done is typed and outputs
# an average of the numbers input

total = 0
count = 0
while True:
    line = raw_input('Enter a number: ')
    if line == 'done':
        break
    else:
        try:
            total = total + int(line)
            count = count + 1
        except: print 'Please enter a number'
print 'Done!'
print total
print count
        
