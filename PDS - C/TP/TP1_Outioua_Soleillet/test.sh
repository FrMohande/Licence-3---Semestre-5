#!/bin/bash


make realclean
echo "Exercice n°1"
make prlimit
./prlimit

echo
echo


echo "Exercice n°2"
make maccess
mkdir -p test
cd test

#man access 

#EACESS
#-- 1    L'accès  serait  refusé  au fichier lui‐même
echo "Test erreur EACESS #1"
touch love
chmod -rwx love 
../maccess -rwxv love
echo 


#-- 2  l n'est pas permis de parcourir l'un des répertoires du préfixe du chemin de pathname
echo "Test erreur EACESS #2"
mkdir -p cat
chmod +rwx cat
touch cat/a
chmod -x cat

../maccess -rv cat/a
echo


##ELOOP
echo "Test erreur ELOOP"
ln -s foo foo
../maccess -rwxv foo
echo



##ENAMETOOLONG
echo "Test erreur ENAMETOOLONG"
fichier='toto'
for i in `seq 1 120`;
do
	c=$c$i
done
fichier=$fichier$c
../maccess -rwxv $fichier 
echo

##ENOENT
echo "Test erreur ENOENT"
ln -s toto titi
../maccess -rwv titi
echo




##ENOTDIR
echo "Test erreur ENOTDIR"
touch kiwi
../maccess -rv kiwi/toto
echo 




##EROFS
echo "EROFS ne peut pas être reproduites car nous ne pouvons pas monter/crée notre système de fichier en lecture seul"
echo
echo


#effacer le fichier test
echo "Voulez-vous supprimer le répertoire 'test' ?"
select yn in "Oui" "Non"; do
    case $yn in
        Oui ) cd ..;chmod -R 777 test;rm -r test; break;;
        Non ) exit;;
    esac
done

