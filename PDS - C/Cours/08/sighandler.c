#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <assert.h>

#define ITER 1000000

typedef struct mempartagee {
    int c1, c2, c3;
} mempartagee;

mempartagee *m;

void usr1(int arg) {
    m->c1++;
    m->c2++;
}

int main(int argc, char *argv[]) {
    int i;
    struct sigaction sa;

    m = (mempartagee *) malloc(sizeof(mempartagee));
    assert(m != NULL);

    sa.sa_handler = &usr1;
    sa.sa_flags = 0;
    assert(sigemptyset(&sa.sa_mask) == 0);
    assert(sigaction(SIGUSR1, &sa, NULL) == 0);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        /* Le fils ne partage pas de données, il sert juste à envoyer
         * des signaux au père */
        for (i = 0; i < ITER; i++) {
            kill(getppid(), SIGUSR1);
        }
        exit(EXIT_SUCCESS);
    }

    for (i = 0; i < ITER; i++) {
        m->c1++;
        m->c3++;
    }

    assert(wait(NULL) != -1);
    printf("%d + %d = %d = %d ?\n", m->c2, m->c3, m->c2 + m->c3, m->c1);

    return 0;
}
