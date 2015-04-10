#! /bin/bash -x
# Search of home directory for files ending with specific extensions
# and files containing specific search terms

find $HOME -type f -name "*.$1" -exec grep "$2" {} \;