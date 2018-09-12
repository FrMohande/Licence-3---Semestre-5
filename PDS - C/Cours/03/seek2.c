#include <assert.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

/* Essayer :
 * ./seek2 < seek2.c
 * ./seek2
 * cat seek2.c | ./seek2
 */

int main(int argc, char *argv[]) {
    off_t pos;
    char c;

    pos = lseek(STDIN_FILENO, 3, SEEK_SET);
    if (pos == -1) {
        perror("Erreur de lseek");
        exit(EXIT_FAILURE);
    }
    assert(read(STDIN_FILENO, &c, 1) == 1);

    return 0;
}
