#include <assert.h>
#include <fcntl.h>
#include <math.h>
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#define WRITE   0
#define PRINTF  1
#define PRINTLN 2

void affiche(int mode, char *s) {
    switch (mode) {
    case WRITE:
        write(STDOUT_FILENO, s, strlen(s));
        break;
    case PRINTF:
        printf("%s", s);
        break;
    case PRINTLN:
        printf("%s\n", s);
        break;
    default:
        fprintf(stderr, "Mode non défini : %d\n", mode);
    }
}

int main(int argc, char *argv[]) {
    int fd;
    int mode = 0;

    if(argc > 1)
        mode = strtol(argv[1], NULL, 10);

    affiche(mode, "a");

    fd = open("tmp-1", O_WRONLY|O_CREAT|O_TRUNC, 0644);
    assert(fd != -1);
    assert(dup2(fd, STDOUT_FILENO) != -1);
    close(fd);

    affiche(mode, "b");

    fd = open("tmp-2", O_WRONLY|O_CREAT|O_TRUNC, 0644);
    assert(fd != -1);
    assert(dup2(fd, STDOUT_FILENO) != -1);
    close(fd);

    affiche(mode, "c");

    return 0;
}
