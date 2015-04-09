#takes user number input into a list and produces a list and calculates the 
# average

numlist = list()

while (True):
    input = raw_input('Enter a number: ')
    if input == 'done':
        break
    value = float(input)
    numlist.append(value)
average = sum(numlist) / len(numlist)

print 'Average: ', average
