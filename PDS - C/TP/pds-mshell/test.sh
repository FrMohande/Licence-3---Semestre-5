#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <getopt.h>





int main(int argc,char * argv[]) {
  int c ;
  int droit ;
  char * path  = argv[argc-1] ;

  while ((c = getopt(argc , argv, "rwxv")) != -1) {
    switch (c) {
      case 'r':
        droit = access(path,R_OK) ;
        if(!droit){
          printf("Le fichier \"%s\" est acce