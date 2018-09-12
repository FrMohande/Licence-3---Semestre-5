#include "sys/wait.h"
#include "graphe.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/time.h>
#include "graphToVisu.h"

typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];


int * PlusCourteChaine(tGraphe graphe,tNomSommet nomSommetDebut) {
  tTabCouleurs couleurs ;
  int nbSommets ;
  int nbVoisins;
  tFileSommets file ;
  int * d;
  int i ;
  tNumeroSommet numSommetDebut ;
  tNumeroSommet numSommetCourant ;
  tNumeroSommet numSommetVoisin;
  tNumeroSommet pred[MAX_SOMMETS];
  nbSommets = grapheNbSommets(graphe) ;
  for(i = 0 ; i < nbSommets ; i++) {
    if(numSommetDebut != i) {
      couleurs[i] = BLEU ;
    }
  }
  d = (int *) malloc(nbSommets * sizeof(int));
  file = fileSommetsAlloue() ;
  numSommetDebut = grapheChercheSommetParNom(graphe,nomSommetDebut) ;
  d[numSommetDebut] = 0 ;
  pred[numSommetDebut] = -1 ;
  couleurs[numSommetDebut] = VERT ;
  fileSommetsEnfile(file,numSommetDebut);
  while(!fileSommetsEstVide(file)) {
    numSommetCourant = fileSommetsDefile(file) ;
    nbVoisins = grapheNbVoisinsSommet(graphe,numSommetCourant) ;

    for(i = 0 ; i < nbVoisins ; i++) {
      numSommetVoisin = grapheVoisinSommetNumero(graphe,numSommetCourant,i);

      if(couleurs[numSommetVoisin] == BLEU) {
        couleurs[numSommetVoisin] = VERT ;
        fileSommetsEnfile(file,numSommetVoisin);
        d[numSommetVoisin]  = d[numSommetCourant] +1;
        pred[numSommetVoisin] = numSommetCourant ;
      }
    }
    couleurs[numSommetCourant] = ROUGE ;

  }
  return d;
}


long nbMicroSecondesDepuisDebutHeure() {
  struct timeval tv;
  long us;
  gettimeofday(&tv, NULL);
  // tv.tv_sec : nbre de secondes depuis Epoch
  // tv.tv_usec : complement en microsecondes
  tv.tv_sec = tv.tv_sec % 3600; // on fait un modulo une heure (=3600s)
  us = (tv.tv_sec*1000000)+tv.tv_usec;
  return us;
}



int main(int argc, char *argv[]) {
  int * distance;
  char  c[20];
  double proba;
  tGraphe graphe;
  tNomSommet nomSommet;
  tNomSommet nomSommet1;
  long debut,fin;
  int i ;
  int nbArcs ;
  int nbSommets;
  if (argc<1) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }


  graphe = grapheAlloue() ;
  for(i =0; i < 50; i++){
    snprintf(c,20,"%s%d","niquetamere",i);
    proba = (double)rand() / (RAND_MAX + 1.);
    nbSommets = (rand() % 10)+1;
    grapheAleatoire(graphe,nbSommets,0,proba);
    graphe2visu(graphe, c) ;
    grapheRecupNomSommet(graphe,rand()% nbSommets,nomSommet1);
    nbArcs = grapheNbArcs(graphe) ;
    debut = nbMicroSecondesDepuisDebutHeure();
    distance = PlusCourteChaine(graphe,nomSommet1) ;
    fin = nbMicroSecondesDepuisDebutHeure();
    fin = fin - debut;
    /*printf("On a mis %ld secondes \n",fin);*/
    printf("%d %d %ld\n",nbSommets,nbArcs,fin) ;
}
  /*for(i = 0 ; i < nbSommets ; i++) {
    grapheRecupNomSommet(graphe,i,nomSommet);

    printf("La plus courte chaine pour aller de %s à %s la distance est de %d\n",nomSommet1,nomSommet,distance[i]);
  }*/
  free(distance);
  grapheLibere(graphe);
  exit(EXIT_SUCCESS);
}
