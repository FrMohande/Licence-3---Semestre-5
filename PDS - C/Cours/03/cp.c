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
    int fd_source, fd_dest;
    ssize_t nb_octets_lus;
    char tampon[TAILLE];

    assert(argc >= 3);

    fd_source = open(argv[1], O_RDONLY);
    assert(fd_source != -1);
    /* BUG ! fd_dest = open(argv[2], O_WRONLY | O_CREAT); */
    fd_dest = open(argv[2], O_WRONLY | O_CREAT, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
    assert(fd_dest != -1);

    while ((nb_octets_lus = read(fd_source, tampon, TAILLE)) > 0) {
        assert(write(fd_dest, tampon, nb_octets_lus) == nb_octets_lus);
    }

    if(nb_octets_lus == -1) {
        perror("Erreur de lecture:");
        exit(EXIT_FAILURE);
    }

    close(fd_dest);
    close(fd_source);

    return 0;
}
