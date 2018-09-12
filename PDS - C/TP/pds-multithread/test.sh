#!/bin/bash
make realclean
make
cpt=1
i=1
mult=1000000
while((cpt<=50))
do

  ./aleazard $(($cpt*$mult)) > test.txt
  ((cpt+=1))
  while((i<=10))
  do


    ./compteur-gc test.txt $i >> 'multi.dat'
    echo $(($cpt*10)) $i >> 'multi.dat'
    ((i+=1))
  done
  ((i=1))
done
gnuplot run.gnu
