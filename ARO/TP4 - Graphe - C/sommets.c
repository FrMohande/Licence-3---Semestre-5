#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "graphe.h"

void sommetSansVoisins(tGraphe graphe){
  tNumeroSommet i,j;
  tNomSommet nom;
  printf("Liste des sommets sans voisins:\n");
  if (!grapheEstOriente(graphe)) {
    for(i=0; i < grapheNbSommets(graphe);i++) {
      grapheRecupNomSommet(graphe, i, nom);
      if(grapheNbVoisinsSommet(graphe,i) == 0){
        printf("%s, ", nom);
      }
    }
  } else {
    for(i=0; i < grapheNbSommets(graphe);i++) {
      grapheRecupNomSommet(graphe, i, nom);
      if((grapheNbPredecesseursSommet(graphe,i) == 0) && (grapheNbSuccesseursSommet(graphe,i) == 0)){
        printf("%s, ", nom);
      }
    }
  }
  putchar('\n');
}

void sommetMaxVoisins(tGraphe graphe){
  tNumeroSommet i,j;
  int maxVoisins,tmp;
  tNomSommet nom;
  maxVoisins = -1;
  printf("Liste des sommets avec le plus de voisins:\n");
  if (!grapheEstOriente(graphe)) {
    for(i=0; i < grapheNbSommets(graphe);i++) {
      tmp = grapheNbVoisinsSommet(graphe,i);
      if( tmp > maxVoisins ){
        maxVoisins = tmp;
      }
    }
    for(i=0; i < grapheNbSommets(graphe);i++) {
      if(grapheNbVoisinsSommet(graphe,i) == maxVoisins ) {
        grapheRecupNomSommet(graphe, i, nom);
        printf("%s, ", nom);
      }
    }

  } else {
    for(i=0; i < grapheNbSommets(graphe);i++) {
      tmp = grapheNbPredecesseursSommet(graphe,i) + grapheNbSuccesseursSommet(graphe,i);
      if( tmp > maxVoisins ) {
        maxVoisins = tmp;
      }
    }

    for(i=0; i < grapheNbSommets(graphe);i++) {
      tmp = grapheNbPredecesseursSommet(graphe,i) + grapheNbSuccesseursSommet(graphe,i);
      if(tmp == maxVoisins ) {
        grapheRecupNomSommet(graphe, i, nom);
        printf("%s, ", nom);
      }
    }
  }
  putchar('\n');
}

int main(int argc, char *argv[]) {

  tGraphe graphe;

  if (argc<2) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }


  graphe = grapheAlloue();

  grapheChargeFichier(graphe,  argv[1]);
  sommetSansVoisins(graphe);
  putchar('\n');
  sommetMaxVoisins(graphe);
  grapheLibere(graphe);
  exit(EXIT_SUCCESS);
}
