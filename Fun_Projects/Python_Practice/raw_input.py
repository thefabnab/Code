#program testing raw_input command

prompt = 'What is your age?\n'
prompt2 = 'What is your name?\n'

name = raw_input(prompt2)
age = int(raw_input(prompt))

print 'Hello! Your name is ' + name + ' and your age is ' + str(age)
