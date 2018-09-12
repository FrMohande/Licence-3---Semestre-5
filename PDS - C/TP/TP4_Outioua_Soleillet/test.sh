#!/bin/bash
#
# mcat -- campagne d'appels à mcat-scd
#
# squelette de shell script

rm -f bigfile
make realclean all
# La commande à tester
MCAT=./mcat-scd
#création du bigfile
dd if=mcat-scd.c of=bigfile seek=20480
# le fichier à lui mettre en entrée
MCAT_INPUT=bigfile
# Le fichier de temps à générer
TIME_FILE=mcat-tm.dat

#Taille fichier
taille=$(du -b bigfile | awk '{print $1}')
# la commande gnu time
TIME_CMD="/usr/bin/time"
# les options de cette commande
TIME_OPT="-f %e"

# initialisation du fichier de résultats
# vitesse = taille total fichier / temps
rm -f $TIME_FILE && echo "# taille duree" > $TIME_FILE

# un exemple de boucle
#for str in  foo bar gee ; do
    #echo $str
#done

# un autre exemple de boucle
for size in `awk 'BEGIN { for( i=1; i<=8388608; i*=2 ) print i }'`; do
    export MCAT_BUFSIZ=$size
    echo -n "$size " >> $TIME_FILE
    `$TIME_CMD "$TIME_OPT" ./mcat-scd $MCAT_INPUT >/dev/null 2>> $TIME_FILE`
    #echo "scale=2;$taille/$temps" | bc >> $TIME_FILE 
done
gnuplot run.gnu 
# un exemple de redirection des sorties standard et d'erreur
#$TIME_CMD "$TIME_OPT" ls > /dev/null 2>> $TIME_FILE

# eof
