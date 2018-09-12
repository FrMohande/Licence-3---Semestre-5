#!/bin/bash
make realclean all
clear



echo "Ce qui se passe quand vous exécutez une seule commande"
./do -a ls 
echo $?
echo
./do -a "ls toto" 
echo $?
echo
./do -o ls
echo $?
echo
./do -o "cat toto" 
echo $?

echo
echo

#CAS AND
echo "Ce qui se passe quand vous êtes en mode AND et qu'au moins l'une des commandes échoue"

./do -a ls "ls toto" 
echo $?
echo 

echo "Ce qui se passe quand vous êtes en mode AND eet que toutes les commandes réussisent" 
./do -a ls ls 
echo $?

#CAS OR
echo "Ce qui se passe quand vous êtes en mode OR et qu'au moins l'une des commandes échoue"

./do -o ls "ls toto" "ls toto" 
echo $?
echo

./do -o ls "ls toto" ls 
echo $?
echo


echo "Ce qui se passe quand vous êtes en mode OR eet que toutes les commandes réussisent" 

./do -o ls ls 
echo $?
echo

echo
echo
#----------------------- CAS CC --------------------------------------
#CAS AND CC
echo "Ce qui se passe quand vous êtes en mode AND CC et qu'au moins l'une des commandes échoue"
./do -a -c "cat toto" ls ls ls ls ls  
echo $?
echo

#Cas OR CC
echo "Ce qui se passe quand vous êtes en mode OR CC et qu'au moins l'une des commandes réussit"
./do -o -c "cat toto" "cat toto" "cat toto" "cat toto" ls
echo $?
echo 
# ------------------------------------- CAS KILL -----------------------------
#Cas AND CC KILL
echo "Ce qui se passe quand vous êtes en mode AND OR l'un des commandes échoue" 
./do -a -c -k xterm cat xterm "cat toto" 
echo $?
echo 
echo "Ce qui se passe quand vous êtes en mode AND OR l'un des commandes échoue mais un réussis " 
./do -a -c -k xterm cat ls xterm "cat toto" 
echo $?
echo
#CAS OR CC KILL
echo "Ce qui se passe quand vous êtes en mode AND OR l'un des commandes réussit" 
./do -o -c -k xterm cat ls "ls toto" 
echo $?
echo "Remarque :: xterm et cat sont des processus bloquants celà prouve que kill fonctionne"
 

echo 

