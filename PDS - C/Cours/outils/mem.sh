#!/bin/sh

if [ -z "$1" ]; then
    echo "Usage: mem.sh <pid>"
    echo "Affiche une « carte » de la mémoire virtuelle du processus <pid>"
    exit 1
fi

if which csvtool > /dev/null;
then
    sed 's/  */ /g' /proc/"$1"/maps | cut -d ' ' -f 1,2,6 | csvtool -t ' ' readable -
else
    sed 's/  */ /g' /proc/"$1"/maps | cut -d ' ' -f 1,2,6
fi
