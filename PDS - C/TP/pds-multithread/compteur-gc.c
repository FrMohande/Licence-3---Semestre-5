#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>
#include <assert.h>

typedef struct args_s {
  char * bloc ;
  unsigned long taille ;
  unsigned long compteur_de_genome ;
} arguments;

unsigned long compteur_gc(char *bloc, unsigned long taille) {
    unsigned long i, cptr = 0;
    for (i = 0; i < taille; i++)
        if (bloc[i] == 'G' || bloc[i] == 'C')
            cptr++;

    return cptr;
}
void * compteur_gc_wrapper(void * args) {
  arguments * a ;
  a = (arguments *) args ;
  a->compteur_de_genome = compteur_gc(a->bloc,a->taille) ;
  return NULL;

}
int calcul_nombre_genome(char * contenu,int taille,int nbThread) {
  int cpt;
  if(nbThread > 0 ) {
    int i ;
    pthread_t * id_thread;
    struct args_s * a ;
    cpt = 0;
    id_thread = (pthread_t*)malloc(nbThread * sizeof(pthread_t)) ;
    a = (arguments *)malloc(sizeof(arguments) * nbThread);
    for(i=0;i<nbThread;i++) {
      a[i].taille = taille/nbThread ;
      a[i].bloc = contenu+(i*(taille/nbThread));
      assert(pthread_create(&id_thread[i],NULL,compteur_gc_wrapper,&a[i]) == 0) ;
    }
    for(i = 0; i < nbThread; i++){
      assert(pthread_join(id_thread[i],NULL) == 0);
      cpt += a[i].compteur_de_genome;
    }
    free(a) ;
    free(id_thread);
    return cpt;

  } else {
    perror("il faut au moins 1 seul thread ") ;
    exit(EXIT_FAILURE);
  }

}


int main(int argc, char *argv[]) {
    struct stat st;
    int fd;
    char *tampon;
    int lus;
    int nbThread;
    unsigned long cptr = 0;
    off_t taille = 0;
    struct timespec debut, fin;

    assert(argv[1] != NULL);
    assert(argv[2] != NULL);

    nbThread = atoi(argv[2]);
    /* Quelle taille ? */
    assert(stat(argv[1], &st) != -1);
    tampon = malloc(st.st_size);
    assert(tampon != NULL);

    /* Chargement en mémoire */
    fd = open(argv[1], O_RDONLY);
    assert(fd != -1);
    while ((lus = read(fd, tampon + taille, st.st_size - taille)) > 0)
        taille += lus;
    assert(lus != -1);
    assert(taille == st.st_size);
    close(fd);

    /* Calcul proprement dit */
    assert(clock_gettime(CLOCK_MONOTONIC, &debut) != -1);
    if((taille % nbThread) == 0){
       cptr = calcul_nombre_genome(tampon,taille,nbThread);
    }else{
      cptr = calcul_nombre_genome(tampon,(taille-(taille % nbThread)),nbThread);
      cptr += compteur_gc(tampon+(taille-(taille % nbThread)), taille % nbThread);
    }
    assert(clock_gettime(CLOCK_MONOTONIC, &fin) != -1);

    /* Affichage des résultats */
    /*printf("Nombres de GC:   %ld\n", cptr);
    printf("Taux de GC:      %lf\n", ((double) cptr) / ((double) taille));*/

    fin.tv_sec  -= debut.tv_sec;
    fin.tv_nsec -= debut.tv_nsec;
    if (fin.tv_nsec < 0) {
        fin.tv_sec--;
        fin.tv_nsec += 1000000000;
    }
  /*  printf("Durée de calcul: %ld.%09ld\n", fin.tv_sec, fin.tv_nsec);*/
  /*  printf("(Attention: très peu de chiffres après la virgule sont réellement significatifs !)\n");*/
    printf("%ld.%09ld  ", fin.tv_sec, fin.tv_nsec);

    return 0;
}
