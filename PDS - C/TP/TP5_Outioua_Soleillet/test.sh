#!/bin/bash
make realclean all

echo "Exercice n°5 Multi-fourche"
echo "dès que notre programme rencontre un chiffre paire celui ci renvoie 1 donc une erreur"
echo "Sinon dès qu'il rencontre un chiffre impaire celui ci renvoie 0"
echo
echo "./multif 1"
./multif 1 
echo
echo "./multif 2"
./multif 2
echo
echo "./multif 1 5 7 9"
./multif 1 5 7 9
echo
echo "./multif 1 3 5 7 2"
./multif 1 3 5 7 2
echo

echo "Exercice n°7 A vos marques,prêts"
echo
echo "./race"
./race
echo
echo "./race"
./race
echo "on remarque que les processus fils crée en premier ne sont pas forcément ceux qui finissent en premier."
