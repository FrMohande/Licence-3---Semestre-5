#include "graphe.h"
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

tNomSommet * ParcoursProfondeur(tGraphe graphe,tNomSommet nomSommetDebut, tNomSommet nomSommetFin) {
/* ALGORITHME PAGE 72  modifier pour la question 3 on retourne un tableau */
  int nbSommets ;
  int i,j ;
  int trouvee ;
  int nbVoisins;
  tNomSommet * ret;

  tTabCouleurs couleurs ;
  tNumeroSommet numSommetDebut ;
  tNumeroSommet numSommetcurrent ;
  tNumeroSommet numSommetVoisin ;
  tPileSommets pile ;
  tNomSommet nom;

  nbSommets = grapheNbSommets(graphe) ;
  ret = (tNomSommet *) malloc(sizeof(tNomSommet)* nbSommets);
  numSommetDebut = grapheChercheSommetParNom(graphe,nomSommetDebut) ;
  j = 0;
  for(i=0;i< nbSommets ;i++) {
    if(numSommetDebut != i) {
      couleurs[i] = BLEU ;
    }
  }
  i=0;
  pile = pileSommetsAlloue();
  couleurs[numSommetDebut] = VERT ;
  pileSommetsEmpile(pile,numSommetDebut) ;
  while(!pileSommetsEstVide(pile)) {
    numSommetcurrent = pileSommetsTete(pile) ;

    nbVoisins = grapheNbVoisinsSommet(graphe,numSommetcurrent) ;
    trouvee = 0 ;
    for(i = 0; (i< nbVoisins)&& !(trouvee); i++){
      numSommetVoisin = grapheVoisinSommetNumero(graphe,numSommetcurrent,i) ;
      if(couleurs[numSommetVoisin] == BLEU ) {
        trouvee = 1 ;
      }
    }
    if(trouvee) {
        couleurs[numSommetVoisin] = VERT ;
        pileSommetsEmpile(pile,numSommetVoisin) ;

    } else {
      couleurs[numSommetcurrent] = ROUGE ;
      pileSommetsDepile(pile) ;
      grapheRecupNomSommet(graphe,numSommetcurrent,nom);
      if(strcmp(nom,nomSommetFin) == 0){
        grapheRecupNomSommet(graphe,numSommetcurrent,nom);
        strcpy(ret[j],nom);
        j++;
        while(!pileSommetsEstVide(pile))
        {
          numSommetcurrent = pileSommetsTete(pile) ;
          pileSommetsDepile(pile) ;
          grapheRecupNomSommet(graphe,numSommetcurrent,nom);
          strcpy(ret[j],nom);
          j++;
        }
        break;
      }
    /*  printf("le nom est %s et la couleur est %d\n",nom,couleurs[numSommetcurrent]);*/
    }
  }

  pileSommetsLibere(pile);
  strcpy(ret[j],"");
  return ret;
}

/* Question 3 */
/*int ExistChaine(tGraphe graphe,tNomSommet nomSommetA,tNomSommet nomSommetB) {
  tNomSommet * ret;
  int nbSommets,j;
  int exist;
  ret = ParcoursProfondeur(graphe,nomSommetB);
  exist = 0;
  nbSommets = grapheNbSommets(graphe);
  for(j=0; j < nbSommets; j++){
    if(strcmp(ret[j],nomSommetA) ==0){
      exist = 1;
      break;
    }
  }
  free(ret);
  return exist;

}*/
int main(int argc,char * argv[]) {
  int i,j;
  tNomSommet * chemin;
  tGraphe graphe;
  if (argc<3) {
    halt("Usage : %s FichierGraphe nomSommetDebut nomSommetFin\n", argv[0]);
  }
  i = 0;



  graphe = grapheAlloue();
  grapheChargeFichier(graphe,  argv[1]);
/*  ParcoursProfondeur(graphe,argv[2]) ;*/
  chemin =  ParcoursProfondeur(graphe,argv[2],argv[3]);
  if(chemin != NULL){
    while(strcmp(chemin[i],"") != 0){
      i++;
    }

    for(j = i-1; j >= 1; j--){
      printf("%s->",chemin[j] );
    }
    if(j != -1){
      printf("%s\n",chemin[j]);
    }else{
      printf("%s\n", "Pas de chemin de sortie possible !");
    }
  }
  grapheLibere(graphe);
  exit(EXIT_SUCCESS);
}
