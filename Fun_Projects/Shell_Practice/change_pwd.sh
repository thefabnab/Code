#! /bin/bash
# shell changes the users pwd via user input

cat <<EOF
-------------------------------
PWD is currently set as $PWD

lets change that.....
EOF
read new
PWD=${new:-$PWD}
cat <<EOF
The new PWD is set to $PWD
-------------------------------
EOF