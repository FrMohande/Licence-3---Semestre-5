/* pas d'argument on affiche uniquement les 10 derniers lignes */
/* -n */
/* On ouvre le fichier une fois on lit le nombre de ligne on ferme le fichier */
/* On reouvre le fichier - 10 */ 


#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>


#define TAILLE 16

static int nb_ligne = 10 ;

void affichageLigne(char * pathname) {
	int fd_source ;
	int cpt ;
	int i ;
	int offset;
	int affiche;
	ssize_t nb_octet_fichier ;
	char tampon[TAILLE] ;
	cpt = 0 ;
	
	
	fd_source = open(pathname,O_RDONLY) ;
	assert(fd_source != -1) ;
	while( (nb_octet_fichier = read(fd_source,tampon,TAILLE)) > 0){
		for(i=0;i<nb_octet_fichier;i++) {
			if( tampon[i] == '\n') {
				cpt++;
			}
		}
	}
	
	close(fd_source);
	
	offset=(nb_ligne>cpt)?0:(cpt-nb_ligne);
	/*printf("%d\n",cpt);
	printf("%d",offset);*/
	
	fd_source = open(pathname,O_RDONLY) ;
	assert(fd_source != -1) ;
	affiche=(nb_ligne>cpt)?1:0;
	cpt = 0 ;
	while( (nb_octet_fichier = read(fd_source,tampon,TAILLE)) > 0){
		for(i=0;i<nb_octet_fichier;i++) {
			if(affiche){
				putchar(tampon[i]);
			}
			if( tampon[i] == '\n') {
				cpt++;
				if(cpt >= offset){
					affiche = 1;
				}
			}
		}
	}
	close(fd_source) ;

	
	
}
int main(int argc,char * argv[]) {
	
	int c ;
	while ((c = getopt(argc , argv, "n")) != -1) {
		switch (c) {
			case 'n' :
				nb_ligne = atoi(argv[argc-2]) ;
				break;
		}
	
	}
	affichageLigne(argv[argc-1]) ; 
	exit(EXIT_SUCCESS) ;
}
