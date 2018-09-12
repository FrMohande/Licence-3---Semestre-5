#include <assert.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

struct data_s {
    int i;
    char c;
    char *chaine;
    char chainebis[10];
};

int main(int argc, char *argv[]) {
    struct data_s *d;
    int fd;
    char cmd[1000];

    fd = open("/tmp/data", O_RDONLY);
    assert(fd != -1);
    d = (struct data_s *) mmap(NULL, sizeof(struct data_s), PROT_READ, MAP_SHARED, fd, 0);
    if(d == MAP_FAILED) {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    printf("i = 0x%x\nc = '%c'\nchaine = %p\nchainebis = %s\n", d->i, d->c, d->chaine, d->chainebis);

    snprintf(cmd, 1000, "../outils/mem.sh %d", getpid());

    system(cmd);
    return 0;
}
