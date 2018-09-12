#include "graphe.h"
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];



void parcoursLargeur(tGraphe graphe,tNomSommet nomSommetDebut) {
    tFileSommets file ;
    tTabCouleurs couleurs ;
    int nbSommets ;
    int i ;
    int nbVoisins ;
    tNomSommet nom;
    tNumeroSommet numSommetDebut ;
    tNumeroSommet numSommetCourant ;
    tNumeroSommet numSommetVoisin;
    numSommetDebut = grapheChercheSommetParNom(graphe,nomSommetDebut) ;
    nbSommets = grapheNbSommets(graphe) ;

    for(i = 0 ; i < nbSommets ; i++) {
      if(numSommetDebut != i) {
        couleurs[i] = BLEU ;
      }
    }
    file = fileSommetsAlloue() ;
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
        }
      }
        couleurs[numSommetCourant] = ROUGE;
        grapheRecupNomSommet(graphe,numSommetCourant,nom);
        printf("le nom est %s et la couleur est %d\n",nom,couleurs[numSommetCourant]);

    }
    fileSommetsLibere(file) ;
}


int main(int argc, char *argv[]) {
  tGraphe graphe;

  if (argc<1) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }


  graphe = grapheAlloue();
  grapheChargeFichier(graphe,  argv[1]);
  parcoursLargeur(graphe,"A") ;
  grapheLibere(graphe);
  exit(EXIT_SUCCESS);
}
