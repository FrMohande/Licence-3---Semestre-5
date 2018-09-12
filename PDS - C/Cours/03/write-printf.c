#include <stdio.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    write(STDOUT_FILENO, "a", 1);
    /* printf("b"); */
    fprintf(stdout, "b");
    fflush(stdout);
    write(STDOUT_FILENO, "c", 1);
    /* write(STDERR_FILENO, "a", 1); */
    /* fprintf(stderr, "b"); */
    /* write(STDERR_FILENO, "c", 1); */

    return 0;
}
