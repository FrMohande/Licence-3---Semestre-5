#include <math.h>
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/* Observer les filiations */
/* En jouant sur la durée d’endormissement, on doit voir ce qui se
 * passe quand :
 * - quand le père se termine avant le fils,
 * - quand le fils se termine avant le père. */

int main() {
    switch (fork()) {
        case -1: exit(EXIT_FAILURE);

        case 0: /* Fils */
            printf("Le fils démarre\n");
            sleep(10);
            sleep(10);
            printf("Le fils se termine (son père est : %d)\n", getppid());
            exit(EXIT_SUCCESS);

        default: /* Père */
            /* sleep(10); */
            sleep(10);
            printf("Le père se termine\n");
    }

    return 0;
}
