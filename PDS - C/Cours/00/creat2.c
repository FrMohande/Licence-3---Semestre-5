#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <assert.h>

int main(int argc, char *argv[]) {
    int res;
    /* Attention aux failles de sécurité */
    char chemin[100];

    strcpy(chemin, "/tmp/");
    assert(argc >= 2);
    strncat(chemin, argv[1], 94);
    res = creat(chemin, S_IRWXU);
    if(res == -1) {
        perror("creat");
        exit(EXIT_FAILURE);
    }
    return EXIT_SUCCESS;
}
