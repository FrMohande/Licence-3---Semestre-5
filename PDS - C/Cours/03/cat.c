#include <assert.h>
#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#define TAILLE 16

int main(int argc, char *argv[]) {
    int fd_source;
    /* int i; */
    ssize_t nb_octets_lus;
    char tampon[TAILLE];

    assert(argc >= 2);

    fd_source = open(argv[1], O_RDONLY);
    assert(fd_source != -1);

    while ((nb_octets_lus = read(fd_source, tampon, TAILLE)) > 0) {
        /* BUG : printf("%s", tampon); */

        /* for (i = 0; i < nb_octets_lus; i++) { */
        /*     printf("%c", tampon[i]); */
        /* } */

        assert(write(STDOUT_FILENO, tampon, nb_octets_lus) == nb_octets_lus);
    }

    if(nb_octets_lus == -1) {
        perror("Erreur de lecture:");
        exit(EXIT_FAILURE);
    }

    close(fd_source);

    return 0;
}
