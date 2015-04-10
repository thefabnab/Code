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

