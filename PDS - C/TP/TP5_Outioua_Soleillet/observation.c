#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <error.h>
#include <stdio.h>
#include <assert.h>






int main(int argc,char * argv[]) {
	int i ;
	pid_t pid ; 
	int status ;
	char a ;
	assert(argc > 1) ; 
	for( i = 0 ; i < atoi(argv[1]) ; i++) {
		pid = fork() ;
		switch(pid) {
			case -1 :
				perror("erreur fork") ;
				exit(EXIT_FAILURE) ;
			case 0 :
			/* le fils */
				while(1) {
					printf("%d\n",getpid()) ;
					fflush(stdout);
					sleep(5) ;
				}
				exit(EXIT_SUCCESS) ;
				
			default :
			/* le père */
				break ;
		}	
	}
	system("ps -a") ;
	for(i = 0 ; i < atoi(argv[1]) ; i++) {	
		while(scanf("%c", &a) == 1) { 
			pid = wait(&status) ;
			a = getchar() ;
			printf("la lettre saisie est : %c\n",a) ; 
			printf("le processus fils avec le pid : %d s'est terminé\n",pid) ; 		
			system("ps -a") ;
		}

	}
	exit(EXIT_SUCCESS) ;
}
