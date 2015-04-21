#For finding a desired frequencey number of a given kmer of a specified length

def PatternCount(Text, Pattern):
    count = 0
    i = 0
    for i in range(len(Text) - len(Pattern) + 1):
        if (Text[i:(i +len(Pattern))] == Pattern):
            count = count + 1
    return count

def ClumpFinding(k,Length,freq,Text):
    i = 0
    Length = int(Length)
    k = int(k)
    freq = int(freq)
    count = []
    freqPatterns = []
    for i in range(Length):
        Pattern = Text[i:(i+k)]
        count.append(PatternCount(Text,Pattern))
    i = 0
    for i in range(Length):
        if (count[i] == freq):
            freqPatterns.append(Text[i:(i+k)])
    fin = sorted(list(set(freqPatterns)))
    return " ".join(str(x) for x in fin)
k = 3
Length = 566
freq = 39
f = open('text.txt','r')
Text = f.read()
print ClumpFinding(k,Length,freq,Text)
