#Temperature converter from F to C

inp = raw_input('Enter Fahrenheit Temperature:') 
try:

    fahr = float(inp) 
    cel=(fahr-32.0)*5.0/9.0
    print str(cel) +' C'
except:
    print 'Please enter a Number'
