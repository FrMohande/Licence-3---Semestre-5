#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <pthread.h>

/* On veut exécuter deux appels à la fonction « fonct » de façon
 * concurrente */

int fonct(char c, unsigned int nb) {
    unsigned int i;

    for (i = 0; i < nb; i++) {
        putchar(c);
    }

    return 1234;
}

struct fonct_arg_s {
    char c;
    unsigned int nb;

    int ret;
};

void *fonct_wrapper(void *arg) {
    struct fonct_arg_s *a;
    a = (struct fonct_arg_s *) arg;
    a->ret = fonct(a->c, a->nb);
    return NULL;
}

int main(int argc, char *argv[]) {
    struct fonct_arg_s *a;
    pthread_t tid;

    /* Un premier appel, « fonct('|', 100000); » devient : */
    a = (struct fonct_arg_s *) malloc(sizeof(struct fonct_arg_s));
    assert(a != NULL);
    a->c = '|';
    a->nb = 100000;
    assert(pthread_create(&tid, NULL, &fonct_wrapper, a) == 0);

    fonct('.', 100000);

    assert(pthread_join(tid, NULL) == 0);
    printf("Résultat : %d\n", a->ret);

    /* Si on exécute fonct après pthread_join, l’exécution n’est pas
     * concurrente */
    /* fonct('.', 100000); */

    return 0;
}
