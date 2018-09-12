#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    int fds[2];
    unsigned int val;

    assert(pipe(fds) != -1);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        /* calcule une valeur */
        val = 0x123456;
        assert(write(fds[1], &val, sizeof(unsigned int)) == sizeof(unsigned int));
        printf("Fin du fils, val (du fils) = %x\n", val);
        exit(EXIT_SUCCESS);

    default:                   /* Père */
        sleep(1);
        printf("Avant le read, val (du père) = %x\n", val);
        assert(read(fds[0], &val, sizeof(unsigned int)) == sizeof(unsigned int));
        printf("Après le read, val (du père) = %x\n", val);
    }

    return 0;
}
