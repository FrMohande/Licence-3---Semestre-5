set title "Temps et vitesse d'execution"
set logscale x
set xlabel "Taille du génome"
set zlabel "Temps d'éxécution"
set ylabel "Nombre de thread"
set dgrid3d 30,30
set pm3d
set term png
set output "multi.png"
splot "multi.dat" using 2:3:1 with lines title "Temps"

