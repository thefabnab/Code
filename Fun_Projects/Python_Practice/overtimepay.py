#Script to calculate overtime pay for employee

hours = float(raw_input('How many hours did you work?\n'))
pay = float(raw_input('How much do you get paid per hour?\n'))

try:
    if hours > 40:
        print (hours - 40) * (pay*1.5) + (hours * pay)
    elif hours < 40:
        print hours * pay
except:
   print 'Please input a number'
