#include <unistd.h>
#include <stdlib.h>
#include <assert.h>	
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>


int main(int argc,char*argv[]) {
	int taille ;
	char * buffer;
	int fd ;
	int nb_oct_lu ;
	char * mcat_bufsiz;
	mcat_bufsiz = NULL ;
	
	mcat_bufsiz = getenv("MCAT_BUFSIZ");
	assert(mcat_bufsiz != NULL);
	assert((taille = atoi(mcat_bufsiz)) != 0) ;
	buffer = (char*)malloc(sizeof(char)*taille);
	assert(buffer != NULL) ;
	if(argc >= 1) {
		assert( (fd = open(argv[1],O_RDONLY)) != -1)  ;
		while( (nb_oct_lu = read(fd,buffer,taille))){
			assert( write(STDOUT_FILENO,buffer,nb_oct_lu) == nb_oct_lu);
		}
		
		assert(close(fd) != -1);
		
	}	

	free(buffer) ;
	exit(EXIT_SUCCESS) ;
}
