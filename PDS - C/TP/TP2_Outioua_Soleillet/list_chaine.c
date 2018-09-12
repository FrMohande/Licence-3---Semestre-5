#include "list_chaine.h"

Liste *initialisation() {
	Liste *liste = malloc(sizeof(Liste));
	
	if (liste == NULL ) {
		exit(EXIT_FAILURE);
	}
	liste->premier = NULL ;
	return liste;
}

void afficherListe(Liste *liste) {
	Element *actuel;
	if (liste == NULL) {
		exit(EXIT_FAILURE);
	}
	actuel = liste->premier;
	while (actuel != NULL) {
		printf("peripherique: %lu i_noeud: %lu\n", actuel->peripherique,actuel->i_noeuds);
		actuel = actuel->suivant;
	}

}
void insertion(Liste *liste,dev_t peripherique,ino_t i_noeuds) {
	Element * nouveau  ;
    /* Création du nouvel élément */
    nouveau = malloc(sizeof(Element));    
    if ( nouveau == NULL)
    {
        exit(EXIT_FAILURE);
    }
    nouveau->i_noeuds = i_noeuds;
    nouveau->peripherique = peripherique;
    if(liste->premier == NULL) {
		nouveau->suivant = NULL;
		liste->premier = nouveau ;
	} else {
		/* Insertion de l'élément au début de la liste */
		nouveau->suivant = liste->premier;
		liste->premier = nouveau;
	}
}
void suppression(Liste *liste) {
/* Supprime élement par élément */
	if (liste == NULL) {

		exit(EXIT_FAILURE);

	}
	if (liste->premier != NULL) {
		Element *aSupprimer = liste->premier;
		liste->premier = liste->premier->suivant;
		free(aSupprimer);
	}
}
void suppressionTotal(Liste * liste) {

	while(liste->premier != NULL) {
		suppression(liste) ;
	}

}
int contain(Liste *liste,dev_t peripherique,ino_t i_noeuds) {
	/* 0 l'élement n'est pas dedans 
	 * 1 l'élement est contenu dedans 
	*/
	Element *actuel;
	int res = 0 ;
	actuel = liste->premier;
	if (liste == NULL) {
		exit(EXIT_FAILURE);
	}
	while(actuel != NULL) {
		if( (actuel->i_noeuds == i_noeuds) && (actuel->peripherique == peripherique)) {
			return 1 ;
		}
		actuel = actuel->suivant ;
	}
	return res;
}


/* int main(int argc,char * argv[]) {
	
	Liste *maListe = initialisation();
	insertion(maListe,4,5);
	insertion(maListe,6,5);
	insertion(maListe,8,5);
	afficherListe(maListe) ;
	printf("\n") ;
	printf("%d\n",contain(maListe,4,5)) ;
	printf("%d\n",contain(maListe,8,5)) ;
	printf("%d\n",contain(maListe,2,6)) ;
	printf("%d\n",contain(maListe,4,3)) ;
	printf("%d\n",contain(maListe,-4,-3)) ;
	afficherListe(maListe) ;
	return 0;
} */
