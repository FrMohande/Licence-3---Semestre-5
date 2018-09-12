#include <assert.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <dirent.h>
#include <limits.h>

int main(int argc, char *argv[]) {
    DIR *rep;
    struct dirent *de;
    struct stat st;
    char chemin[PATH_MAX];
    char cible[PATH_MAX+1];
    ssize_t longueur;

    assert(argc >= 2);
    rep = opendir(argv[1]);
    assert(rep != NULL);

    while ((de = readdir(rep)) != NULL) {
        snprintf(chemin, PATH_MAX, "%s/%s", argv[1], de->d_name);
        /* assert(stat(chemin, &st) != -1); */
        assert(lstat(chemin, &st) != -1);
        if(S_ISLNK(st.st_mode)) {
            longueur = readlink(chemin, cible, PATH_MAX);
            assert(longueur != -1);
            cible[longueur] = '\0';
            printf("%s -> %s\n", de->d_name, cible);
        } else {
            printf("%s\n", de->d_name);
        }
    }

    assert(closedir(rep) != -1);

    return 0;
}
