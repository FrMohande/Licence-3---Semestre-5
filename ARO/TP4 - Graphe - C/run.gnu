set title "Temps et vitesse d'execution"
set logscale x
set xlabel "Nombre de sommet "
set zlabel "Temps d'exécution"
set ylabel "Nombre d'arcs"
set dgrid3d 30,30
set pm3d
set term png
set output "multi.png"
splot "donne.dat" using 1:2:3 with lines title "Temps"
