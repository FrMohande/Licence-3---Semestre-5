#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <signal.h>
#include <sys/stat.h>
#include <fcntl.h>


#include "makeargv.h"

/* void freeargv(char **argv)
  char **makeargv(const char *s) */
int main(int argc,char * argv[]) {
  int i;
  int j ;
  int k ;
  int c ;
  int cptargs ;
  pid_t * pid ;
  int status ;
  int nb_fils;
  int boolean ;
  int AndouOR ;
  int optionC ;
  int optionK ;

  AndouOR = -1 ;
  cptargs = 0 ;
  nb_fils = 0 ;
  boolean = 0 ;
  optionC = 0 ;
  optionK = 0 ;
  while ((c = getopt(argc , argv, "aock")) != -1) {
    switch (c) {
      case 'a' :
        cptargs++ ;
        AndouOR = 1 ;
        boolean = 1 ;
        break;
      case 'o' :
        cptargs++ ;
        AndouOR = 0 ;
        boolean = 0 ;
        break;
      case 'c' :
        cptargs++ ;
        optionC = 1 ;
        break;
      case 'k' :
        cptargs++ ;
        optionK = 1 ;
        break;
    }
  }

  /*printf(" tototototoototoototototototo : %d\n",boolean );*/
  pid = (pid_t*) malloc((argc-cptargs)*sizeof(pid_t)) ;
  for (i = cptargs+1; i < argc; i++) {
      /* traiter argv[i] */

      char **cmdargv;

      /* création du argv de l'argument i */
      cmdargv = makeargv(argv[i]);
      assert(cmdargv != NULL);


    /*  fprintf(stderr, "[%s]\t%% ", cmdargv[0]);
      for (arg = cmdargv; *arg; arg++)
          fprintf(stderr, "%s ", *arg);
      fprintf(stderr, "\n");*/
      switch(pid[nb_fils] = fork()) {
        case -1 :
        /* error */
        exit(EXIT_FAILURE) ;
        case 0 :
        /* fils */
          execvp(cmdargv[0],cmdargv) ;
          exit(EXIT_FAILURE) ;
        default :
        /* père */
          nb_fils++;

      }








      /* libération mémoire */
      freeargv(cmdargv);

  }
  for(j = 0; j < nb_fils ; j++ ) {
    wait(&status) ;
    if(WIFEXITED(status)) {
      if(AndouOR) {
		/* CAS AND */
        /*printf("%d\n", boolean);*/
        boolean = boolean && !WEXITSTATUS(status) ;
        if( (optionC == 1) && (boolean == 0) ) {
          if(optionK) {
            for(k = 0 ; k < nb_fils ;k++) {
              kill(pid[k], SIGKILL);
            }
          }
          free(pid) ;
          exit(EXIT_FAILURE) ;
        }
      } else {
		/* CAS OR */
        boolean = boolean || !WEXITSTATUS(status) ;
        if((optionC == 1) && (boolean == 1) ) {
          if(optionK) {
            for(k = 0 ; k < nb_fils ;k++) {
              kill(pid[k], SIGKILL);
            }
          }
          free(pid) ;
          exit(EXIT_SUCCESS) ;
        }
      }


    }
  }

  free(pid) ;
 /* printf("VALEUR DE RETOUR DE BOOLEAN %d\n",boolean) ;
  printf("LE NOMBRE DE FILS %d\n", nb_fils ); */
  if(boolean) {
    exit(EXIT_SUCCESS) ;
  } else {
    exit(EXIT_FAILURE) ;
  }

}
