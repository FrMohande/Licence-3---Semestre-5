#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <error.h>



int main(int argc,char *argv[]) {
	pid_t pid ;
	int i ;
	int j ;
	int status ;
	for(i = 0 ; i < 10 ; i++) {
		pid = fork() ;
		switch(pid) {
			case -1 :
				perror("erreur fork") ;
				exit(EXIT_FAILURE) ;
			case 0 :
			/* le fils */
				for( j = 0 ; j < 0x5F5E100; j++);
				printf("Je suis le processus fils de pid %d\n", getpid());
				fflush(stdout) ;
				for( j = 0 ; j < 0x5F5E100; j++);
				exit(EXIT_SUCCESS) ;
				break;
			default :
			/* le père */
				break ;
		}	
	}
	for( i = 1 ; i <= 10 ; i++) {
		pid = wait(&status) ;
		printf("%d°) Le processus de pid %d est arrrivé \n",i,pid) ;
		fflush(stdout) ;
	}
	exit(EXIT_SUCCESS) ;
}
