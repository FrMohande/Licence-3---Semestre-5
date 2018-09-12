#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <assert.h>
#include <stdlib.h>

/* Teste si son premier argument est un fichier "ordinaire" (ou
 * "normal") en utilisant l’appel système stat
 */

int main(int argc, char *argv[]) {
    struct stat st;
    /* struct stat *p_st; */

    /* p_st = (struct stat *)malloc(sizeof(struct stat)); */
    /* assert(p_st != NULL); */

    printf("%ld\n", sizeof(struct stat *));
    printf("%ld\n", sizeof(struct stat));

    assert(stat(argv[1], &st) != -1);
    /* stat(argv[1], p_st); */

    if(S_ISREG(st.st_mode))
        printf("Fichier normal\n");

    return 0;
}
