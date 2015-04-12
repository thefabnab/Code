#! /bin/bash
# a quick overview of variable substitution!

hat="Look at my sick hat"
echo $hat

echo "The :- only kicks in with an empty variable"
echo ${hat:-text}

echo "The := substitution will be ignored"
echo ${hat:=text}

echo "however in the presence of a :+ substitution hat will echo differently"
text="I'm not working"
echo ${hat:+text}

echo "filnally I shall calculate lengths and slices of 'strings'"
echo ${#hat} " is the length of hat (0 index)"
echo ${hat:3} " is the string from position 3 on"
