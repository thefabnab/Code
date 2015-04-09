#this is a program that produces a list and modifies it using the range and len
# functions

numbers = [1,2,3,4,5]

print 'The original lists numbers is as follows: ', numbers

for i in range(len(numbers)):
    numbers[i] = numbers[i] * 2

print 'Now the modified list multiplied by two: ', numbers
