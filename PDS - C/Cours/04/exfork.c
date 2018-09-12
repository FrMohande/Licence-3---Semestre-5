#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pidfils;
    int status;

    switch (pidfils = fork()) {
        case -1: exit(EXIT_FAILURE);

        case 0: /* Fils */
            printf("Je suis le fils, mon PID %d, le PID de mon père"
                   " %d\n", getpid(), getppid());
            /* assert(0); */
            /* exit(EXIT_FAILURE); */
            exit(EXIT_SUCCESS);

        default: /* Père */
            printf("Je suis le père, mon PID %d, le PID de mon père"
                   " %d, le pid de mon fils %d\n", getpid(), getppid(), pidfils);
            assert(wait(&status) != -1);
            if(WIFEXITED(status))
                printf("Mon fils s’est terminé en me retournant : %d\n",
                        WEXITSTATUS(status));
            printf("Valeur complète de status : %d\n", status);
    }

    return 0;
}
