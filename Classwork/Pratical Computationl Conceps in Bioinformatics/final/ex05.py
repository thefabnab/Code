#!/usr/bin/env python3
import re
import argparse
import linecache


#searches through the given GFF3 file for the requested annotation
def search(type, attribute, value, source):
    results = []
    file = open(source,'r')
    for line in file:
        if re.search(attribute,line) and re.search(type,line) and re.search(value,line):
            result = line
            results.append(result)
    return results

#extracts the annotation and converts it to an indexed list object
def list_annotation(results):
    for a in results:
        list_annotation = a.split("\t")
    return list_annotation

#This extracts the sequence provided within the annotation file and produces
#A string from it, removing the unneeded >chr markers
def sequence_string(source):
    bases = ["A","T","G","C"]
    sequence = ""
    for x in range(23079, 175068):
        line = linecache.getline(source,x)
        line = line.rstrip()
        for i in line:
            if i in bases:
                sequence = sequence + i
            else:
                pass
    return sequence

#This method takes in the genome sequence string from the previous method, extracts the requested annotation
#coordinates provided via the list annotation method and finds the start and stop site of the segment in question
#it also takes into account the + or - direction of the request and outputs the sequence in 60bp line segments
def sequence_annotation(sequence, beginning, end,list):
    annotated_sequence = ""
    pairing = { 'A' : 'T', 'T':'A','G': 'C', 'C':'G'}
    compliment = []
    reverse_compliment_list = []
    reverse_compliment_string = ""
    count = 0
    for x in range(int(beginning) -1, int(end)):
        annotated_sequence += sequence[x]
    if list[6] == "+":
        for i in range(0, len(annotated_sequence)-1):
            if i % 60 == 0:
               annotated_sequence = annotated_sequence[:i] + "\n" + annotated_sequence[i:]
        return annotated_sequence
    else:
        for base in annotated_sequence:
            compliment.append(pairing[base])

        reverse_compliment_list = reversed(compliment)
        reverse_compliment_string = ''.join(reverse_compliment_list)
        for i in range(0, len(reverse_compliment_string)):
            if i % 60 == 0:
                reverse_compliment_string = reverse_compliment_string[:i] +"\n" + reverse_compliment_string[i:] 
        return reverse_compliment_string


#Command Line Arguments
parser = argparse.ArgumentParser()
parser.add_argument("--source_gff", "-s", action = 'store', dest = 'source_gff', help = "Path to GFF file",required=False)
parser.add_argument("--type", "-t", action = 'store',dest = 'type', help = "Type of site  to search for",required=False)
parser.add_argument("--attribute", "-a", action = 'store', dest = 'attribute', help = "Property of sequence",required=False)
parser.add_argument("--value", "-v", action = 'store', dest = 'value', help = "Gene Identifier Name", required=False)
args = parser.parse_args()

source_gff = args.source_gff
type = args.type
attribute = args.attribute
value = args.value

#Main Method
try:
    found_search = search(type, attribute, value,source_gff)
    found_list = list_annotation(found_search)
    sequence = sequence_string(source_gff)
    output = sequence_annotation(sequence, found_list[3],found_list[4], found_list)
    print(">gene:{0}:{1} \n{2}".format(attribute,value,output))
except:
    print("Your search did not return anything, please review your critera and try again.")
