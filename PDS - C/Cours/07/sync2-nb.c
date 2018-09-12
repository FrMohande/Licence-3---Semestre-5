#define _GNU_SOURCE
#include <assert.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

/* Ce programme affiche toujours beaucoup de choses */
/* C’est l’occasion d’utiliser les redirections dans un tube, avec
 * less pour afficher écran par écran, voire :
 * ./sync2-nb 5 2>&1 | less
 * pour avoir à la fois les sorties standard et d’erreur redirigées
 * dans le tube */

#define TAILLE 10485760

unsigned char *tampon;
unsigned long compteur;

void boucle_rw(int fd_r, int taille_r, int fd_w, int taille_w) {
    long int res;
    assert(taille_w < TAILLE);
    assert(taille_r < TAILLE);

    /* boucle infinie */
    for (compteur = 0;; compteur++) {
        printf("%ld: ", compteur);
        printf("w%ld ", res = write(fd_w, tampon, taille_w));
        fflush(stdout);
        if(res == -1) perror("écriture");
        printf("r%ld\n", res = read(fd_r, tampon, taille_r));
        fflush(stdout);
        if(res == -1) perror("lecture");
    }
}

int main(int argc, char *argv[]) {
    int fds[2];
    long l;

    /* création d’un tube en mode non-bloquant */
    /* autre possibilité : utiliser fcntl(2) après la création du tube */
    assert(pipe2(fds, O_NONBLOCK) != -1);
    assert(argc > 1);
    tampon = (unsigned char *) malloc(TAILLE * sizeof(unsigned char));
    assert(tampon != NULL);

    switch (l = strtol(argv[1], NULL, 10)) {
    case 0:
        boucle_rw(fds[0], 1, fds[1], 1);
        break;
    case 1:
        boucle_rw(fds[0], 2, fds[1], 1);
        break;
    case 2:
        boucle_rw(fds[0], 5, fds[1], 2);
        break;
    case 3:
        boucle_rw(fds[0], 5, fds[1], 0);
        break;
    case 4:
        boucle_rw(fds[0], 10, fds[1], 20);
        break;
    case 5:
        boucle_rw(fds[0], 1, fds[1], 1024);
        break;
    case 6:
        boucle_rw(fds[0], 1, fds[1], 2048);
        break;
    case 7:
        boucle_rw(fds[0], 1, fds[1], 65536);
        break;
    case 8:
        boucle_rw(fds[0], 1, fds[1], 65537);
        break;
    default:
        fprintf(stderr, "Cas non défini l = %ld\n", l);
    }

    return 0;
}
