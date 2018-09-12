#include <unistd.h>
#include <stdlib.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <assert.h>



#define TAILLE 16 

static int nbretour = 0;
static int fin_fichier = 0 ;

void affichageLigne(int fd, int nb_ligne, int n) {
	off_t pos;
	int nb_octet_fichier;
	char tampon[TAILLE] ;
	int i;
	if(fin_fichier){
		pos = lseek(fd,0,SEEK_SET);
		while( (nb_octet_fichier = read(fd,tampon,TAILLE)) > 0){
			for(i=0;i<nb_octet_fichier;i++) {
				putchar(tampon[i]) ;
			}
		}
		exit(EXIT_SUCCESS) ;
	}
	if(nbretour > nb_ligne){
		/* fd garde déjà la position du lseek pas besoin de return pos */
		while( (nb_octet_fichier = read(fd,tampon,TAILLE)) > 0){
			for(i=0;i<nb_octet_fichier;i++) {
				putchar(tampon[i]);
			}
		}
		
	}else{
		pos = lseek(fd, -(n*TAILLE), SEEK_END);
		if(pos ==-1)fin_fichier=1;
		nb_octet_fichier = read(fd,tampon,TAILLE) ;
		for(i=0;i<nb_octet_fichier;i++) {
			if( tampon[i] == '\n') {
				nbretour++;
				if(nbretour > nb_ligne) {
					
					/*On positionne la tête sur la n-ieme ligne avant la fin*/
					/* (pos +i) :: i correponds à l'octet sans les \n en "trop", donc évites d'afficher des lignes supplémentaires dans le tampon */ 
					/*On se place au début la ligne suivante, car à ce moment si on est sur le car '\n' de la ligne avant qu'on ne veux pas afficher*/
					/* On lit à reculon */
					pos = lseek(fd,(pos + i+1),SEEK_SET);
					assert(pos!= -1);
				}
				
				
			}
		}
		affichageLigne(fd,nb_ligne,n +1 ) ;	
	}
  
}
int main(int argc,char * argv[]) {
	int c ;
	int fd_source ;
	int nb_ligne;
	nb_ligne = 10;
	while ((c = getopt(argc , argv, "n")) != -1) {
		switch (c) {
			case 'n' :
				nb_ligne = atoi(argv[argc-2]) ;
				break;
		}
	
	}
	fd_source = open(argv[argc-1],O_RDONLY) ;
	assert( fd_source != -1) ;
	/*printf("%d\n",nb_ligne);*/
	affichageLigne(fd_source,nb_ligne,0);
	close(fd_source);
	
	exit(EXIT_SUCCESS) ;
}
