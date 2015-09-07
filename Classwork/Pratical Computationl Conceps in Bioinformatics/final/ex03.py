#!/usr/bin/env python3
#A temperature conversion program to convert C to F and F to C upon request

 

def convert_temp(scale=None,source_temp=None):
    if scale == 'F':
        temperature_C = (source_temp- 32) * (5.0/9)
        temperature_C = round(temperature_C,1)
        print("{0} degrees {1} is {2} degrees C".format(source_temp,scale,temperature_C))
    if scale == 'C':
        temperature_F = (source_temp * 9.0/5) + 32
        temperature_F = round(temperature_F,1)
        print("{0} degrees {1} is {2} degrees F".format(source_temp,scale,temperature_F))
    else:
        pass

prompt1 = raw_input("Please enter Temperature scale (C or F): ")

if type(prompt1) == "C" or "F" or "f" or "c":
    prompt1 = prompt1.upper()
else:
    print("Please enter one of the two choices")
    quit()

try:
    prompt2 = float(raw_input("Please enter your temperature in {0}: ".format(prompt1)))
except NameError:
    print("Please enter a number")
    
convert_temp(scale=prompt1,source_temp=prompt2)  
