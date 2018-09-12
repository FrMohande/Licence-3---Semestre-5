#!/bin/bash
make realclean all
fichier="mtailstupid.c"
nombre_ligne=-1
exe='mtail'

init(){
echo "Que voulez vous faire ?"
select yn in "Test de mtailstupid" "Test de mtail" "Quitter"; do
    case $yn in
        'Test de mtailstupid' ) mtail 'mtailstupid'; break;;
        "Test de mtail" ) mtail 'mtail'; break;;
        "Quitter" ) exit;;
    esac
done
}

mon_test(){
	
		if [ $nombre_ligne = -1 ] ; then
			echo
			echo "test de $exe"
			echo
			echo "test de $exe sans argument"
			echo "./$exe $fichier > mtail.txt"
			./$exe $fichier > mtail.txt
			cat mtail.txt
			echo
			echo "nous vérifions avec tail"
			echo "tail $fichier > realtail.txt"
			tail $fichier > realtail.txt
			cat realtail.txt
			echo
			echo "diff mtail.txt realtail.txt" 
			diff mtail.txt realtail.txt
			rm mtail.txt realtail.txt
			
		else
			echo
			echo
			echo "test de $exe avec argument -n "$nombre_ligne
			echo "./$exe -n $nombre_ligne $fichier > mtail.txt"
			./$exe -n $nombre_ligne $fichier > mtail30.txt
			cat mtail30.txt
			echo
			echo "nous vérifions avec tail"
			echo "tail -n $nombre_ligne $fichier > realtail.txt"
			tail -n $nombre_ligne $fichier > realtail30.txt
			cat realtail30.txt
			echo
			echo "diff mtail.txt realtail.txt" 
			diff mtail30.txt realtail30.txt
			rm mtail30.txt realtail30.txt
		fi
	echo
	init;
}


mtail(){
	exe=$1
	select yn in "Avec argument ? (Nombre de lignes)" "Sans argument" "Retour"; do
    case $yn in
        'Avec argument ? (Nombre de lignes)' ) nb_ligne 1 ; break;;
        "Sans argument" ) nb_ligne -1 ; break;;
        "Retour" ) init;;
    esac
done

}

nb_ligne(){
	if [ $1 != -1 ] ; then
		echo "Combien de lignes voulez-vous ?"
		read number
		if [[ $number = +([0-9]) ]] ; then
			nombre_ligne=$number
		fi
	fi
	echo "Sur quel fichier ?"
	read f
	fichier=$f
	mon_test
	
 }

init;


