#include <assert.h>
#include <unistd.h>
#include <string.h>

struct data_s {
    int i;
    char c;
    char *chaine;
    char chainebis[10];
};

int main(int argc, char *argv[]) {
    struct data_s d;

    d.i = 0x123456;
    d.c = '0';
    d.chaine = "bobobob";
    /* d.chainebis = "bobobob" */
    strncpy(d.chainebis, "bobobob", 10);

    assert(write(STDOUT_FILENO, &d, sizeof(struct data_s)) == sizeof(struct data_s));

    return 0;
}
