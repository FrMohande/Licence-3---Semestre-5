#include <unistd.h>
#include <error.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>



typedef int (*funct_t) (int) ;
int multif(funct_t f[],int args[],int n) {
	/* funct_t f = fonction 
	 * args = argument de la fonction f toutes les fonctions ont un seul argument 
	 *  n = nombre de fonction présente dans funct_t
	 */
	pid_t pid ;
	int i ;
	int status,error ;
	for(i = 1 ; i < n ; i++) {
		pid = fork() ;
		switch(pid) {
			case -1 :
				perror("erreur fork") ;
				exit(EXIT_FAILURE) ;
			case 0 :
			/* le fils */
			
			/* exit(1) => erreur 
			 * exit(0) => sucess
			 * On traite notre erreur avec le WEXITSTATUS(status) == 0
			 */
				exit(f[i](args[i]));
			default :
			/* le père */
				break ;
		}	
	}
	error = 0;
	for( i = 1 ; i < n ; i++) {
		pid = wait(&status) ;
		if(WIFEXITED(status)){
			/* Si le processus fils s'est bien terminé */
			if(WEXITSTATUS(status) == 0){	
				/* Vérifie si le processus fils s'est terminé avec une erreur
				 * Dans notre cas exit(0) => erreur  
				 */ 
				error += 1;
			}
		}
	}
	if(error > 0){
		return 1;
	}
	return 0; 
}



int f(int a) {
	/*  impaire renvoie 1  
	 * pair renvoie 0 */ 
	return a%2 ; 
} 

int main(int argc,char * argv[]) {
	if(argc > 1) {
		int i,retour;
		int * tab_args;
		funct_t * tab_func;
		tab_func = (funct_t *) malloc(argc * sizeof(funct_t)) ;
		tab_args = (int *) malloc(argc * sizeof(int)) ;
		assert(tab_args != NULL);
		assert(tab_func != NULL);
		/*printf("le nombre d'argument est de %d\n",argc) ;*/
		for(i = 1; i < argc ; i++) {
			tab_func[i] = f ; 
			tab_args[i] = atoi(argv[i]);
		}
		retour = multif(tab_func,tab_args,argc) ;
		free(tab_func);
		free(tab_args);
		printf("%d\n",retour);
		exit(retour);
	} else {
		exit(EXIT_FAILURE) ;
	}
}
