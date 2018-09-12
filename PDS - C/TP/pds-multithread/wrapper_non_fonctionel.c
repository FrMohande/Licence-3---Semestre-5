rapide_wrapper



struct timeval tv;
struct timespec ts;
int timeInMs ;

timeInMs = 1 ;
gettimeofday(&tv, NULL);
ts.tv_sec = time(NULL) + timeInMs / 1000;
ts.tv_nsec = tv.tv_usec * 1000 + 1000 * 1000 * (timeInMs % 1000);
ts.tv_sec += ts.tv_nsec / (1000 * 1000 * 1000);
ts.tv_nsec %= (1000 * 1000 * 1000);
/* ------------------------------------------------ */
int i,expression;
unsigned long taille;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER ;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
bloc_t bloc_a_decouper;
bloc_t bloc_decouper[2];
bloc_t tmp;
int nbdecouper;
while(1){
  pthread_mutex_lock(&mutex);
    expression = pile_vide(&p);
  pthread_mutex_unlock(&mutex);
    while(expression){
      pthread_mutex_lock(&mutex);
      expression = pile_vide(&p);

      printf("La pile est vide ? %d, je suis le thread %ld\n",expression,pthread_self()) ;
      if( (pthread_cond_timedwait(&cond,&mutex,&ts)) == ETIMEDOUT) {
        printf("%s\n","éjkrebkejbke" );

        return NULL ;
      }

      pthread_mutex_unlock(&mutex);
    }
    printf("%s\n", "on dépile");
    pthread_mutex_lock(&mutex);
    printf("La pile est vide ? %d, je suis le thread %ld\n",expression,pthread_self()) ;
    tmp = depile(&p);
    pthread_mutex_unlock(&mutex);
    /*Si le travail est un bloc < au seuil */
    taille = tmp.fin - tmp.debut;
    printf("%d %d\n",taille,seuil_bloc_long);
    if( taille <= seuil_bloc_long){
      printf("%s\n","NTM") ;
      rapide_seq(tmp);

      return NULL;
    }else{
      /* Sinon on le découpe en deux et on empile les deux blocs */
      bloc_a_decouper = tmp;
      nbdecouper = rapide_decoupebloc(bloc_a_decouper,bloc_decouper);
      printf("%d\n", nbdecouper);
        for(i = 0; i < nbdecouper ; i++){
          pthread_mutex_lock(&mutex);
            empile(&p,bloc_decouper[i]);
            pthread_cond_signal(&cond) ;
          pthread_mutex_unlock(&mutex);


        }
    }
}
return NULL;

}
