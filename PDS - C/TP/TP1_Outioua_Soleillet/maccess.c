#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h> /* pour errno afficher le message d'erreur */
#include <string.h> 




int main (int argc, char * argv[]){
	/*  getopt() : récupérer les options gèrer les - ex -v -r nomfichier*/
	
	
	
	int c; 
	char * path;
	int ret;
	

	if(argc <= 2) {
		printf("il faut des arguments : exemple :\n ./maccess -rvx nom_du_fichier\n") ;
		exit(EXIT_FAILURE);
	}
	
	path = argv[argc-1] ; /* récupère le nom du fichier */
	
	while ((c = getopt(argc , argv, "rwxv")) != -1)
    switch (c) {
		
    case 'r':
		ret = access(path,R_OK);
		if(!ret){
			printf("Le fichier \"%s\" est accessible en lecture\n",path);	
		} else {
			printf("Le fichier \"%s\" est non accessible en lecture\n",path);	
			
		}
      break;
    case 'w':
		ret = access(path,W_OK);
		if(!ret){
			printf("Le fichier \"%s\" est accessible en écriture\n",path);	
		} else {
			printf("Le fichier \"%s\" est non accessible en écriture\n",path);	
			
		}
      break;
    case 'x':
		ret = access(path,X_OK);
    	if(!ret){
			printf("Le fichier \"%s\" est accessible en éxécution\n",path);	
		} else {
			printf("Le fichier \"%s\" est non accessible en éxécution\n",path);	
			
		}
      break;
    case 'v':
				/*traitement erreur */
				/* errno récupère le code erreur dès qu'il en n'a une erreur >> man access 
				 * strerror permet d'afficher le nom de l'erreur via le numéro qu'on lui donne */
				printf("%d %s\n",errno,strerror(errno));
		break;
    default:
      printf("Utilisez les arguments :\n -r : pour vérifier les droits de lecture\n -w : pour vérifier les droits d'écriture\n -x pour vérifier les droits d'éxécution\n -v pour les motifs d'erreurs\n");
      exit(EXIT_FAILURE);
      break;
    }
	return 0;
}
