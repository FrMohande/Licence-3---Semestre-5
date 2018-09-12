#ifndef LIST_CHAINE_H
#define LIST_CHAINE_H

#include <sys/types.h>
#include <stdio.h> 
#include <stdlib.h>


typedef struct Element Element;

struct Element

{

    ino_t i_noeuds;
    dev_t peripherique ;
    Element *suivant;

};
typedef struct Liste Liste;

struct Liste {

    Element *premier;

};

Liste *initialisation() ;
void insertion(Liste *,dev_t,ino_t) ;
void suppression(Liste *) ;
void suppressionTotal(Liste *);
int contain(Liste *,dev_t,ino_t) ;
void afficherListe(Liste *);

#endif 
