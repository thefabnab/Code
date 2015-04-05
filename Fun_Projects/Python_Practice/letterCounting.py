#Creates a program to input a string and specific letter and output
# the number of times that letter pops up

inputWord = raw_input("Please select a word\n")

inputLetter = raw_input("Please slect a letter\n")

count = 0

for letter in inputWord:
    if letter  == inputLetter:
        count = count + 1
print 'The letter %s appears in %s %s times' % (inputLetter, inputWord, count)
