#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[]);

void explorepile(int arg) {
    int var = 0x12345678;
    char *p = (char *)&arg;
    int i;
    printf("\narg @ %p = %x\nvar @ %p = %x\n", &arg, arg, &var, var);
    printf("explorepile @ %p\nmain        @ %p\nprintf      @ %p\n", &explorepile, &main, &printf);

    for(i = 0; i < 64; i++) {
        if(i % 16 == 0) printf("\n");
        else if(i % 8 == 0) printf("  ");
        else printf(" ");
        printf("%02x", p[i] & 0xff);
    }
    printf("\n");
}

int main(int argc, char *argv[]) {
    char memoire[1000];
    snprintf(memoire, 1000, "../outils/mem.sh %d", getpid());
    system(memoire);

    explorepile(0x90ABCDEF);

    return 0;
}
