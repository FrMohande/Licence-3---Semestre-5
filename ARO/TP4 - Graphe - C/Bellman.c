#include "graphe.h"
#include <stdlib.h>
#include <stdio.h>


tNumeroSommet sommetsBleu(tTabCouleurs couleurs,int nbSommets) {
  /* 0 = le tableau n'a plus de sommets BLEU
  * sinon la fonction retourne le premier sommets bleu rencontré  */
  int i ;
  int existeBleu;
  existeBleu = 0 ;
  for(i=0;i<nbSommets;i++) {
    if(couleurs[i] == BLEU) {
      return i ;
    }
  }
  return existeBleu ;
}

tNumeroSommet * Trie_topo(tGraphe graphe) {
    int nbSommets ;
    int i;
    int j ;
    int nbVoisins,trouvee ;
    tTabCouleurs couleurs ;
    tPileSommets pile ;
    tNumeroSommet s ;
    tNumeroSommet x ;
    tNumeroSommet y ;
    tNumeroSommet * tab ;
    nbSommets = grapheNbSommets(graphe) ;

    tab = (tNumeroSommet*) malloc(sizeof(tNumeroSommet)* nbSommets);
    j = nbSommets -1 ;
    for(i=0;i<nbSommets;i++) {
      couleurs[i] = BLEU ;
    }
    pile = pileSommetsAlloue();

    while( (s = sommetsBleu(couleurs,nbSommets)) )  {
      couleurs[s] = VERT ;
      pileSommetsEmpile(pile,s) ;
      while(!pileSommetsEstVide(pile)) {
        x = pileSommetsTete(pile) ;
        nbVoisins = grapheNbVoisinsSommet(graphe,x) ;
        trouvee = 0 ;
        for(i = 0; (i< nbVoisins)&& !(trouvee); i++){
          y = grapheVoisinSommetNumero(graphe,x,i) ;
          if(couleurs[y] == BLEU ) {
            trouvee = 1 ;
          }
        }
        if(trouvee) {
            couleurs[y] = VERT ;
            pileSommetsEmpile(pile,y) ;

        } else {
          couleurs[x] = ROUGE ;
          pileSommetsDepile(pile) ;
          tab[j] = x ;
          j--;

      }
    }
  }
  return tab;
}
int main(int argc,char * argv[]) {
  tNumeroSommet * chemin;
  int nbSommets ;
  int i ;
  tGraphe graphe ; 


  graphe = grapheAlloue();
  grapheChargeFichier(graphe,  argv[1]);
  nbSommets = grapheNbSommets(graphe) ;
  chemin =  Trie_topo(graphe);

  for(i=0;i<nbSommets;i++) {
    printf("%d->",chemin[i]) ;
  }
  printf("\n") ;
  free(chemin) ;
  return 0;
}
