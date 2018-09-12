#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <assert.h>
#include <error.h>
#include <limits.h>
#include <string.h> 
#include <dirent.h>
#include <stdio.h>
#include "list_chaine.h"




static int opt_follow_links = 0 ; /* -L */
static int opt_apparent_size = 0 ; 
/* si opt_apparent_size = 0 on fait du -B 512 sinon du -b */
static int taille_fichier = 0 ;
static Liste *maListe;

int du_file(const char * pathname) {
	DIR * rep ;
	struct stat st;
	struct dirent * rep_suiv ;
	char subdir_pathname[PATH_MAX] ;
	
	assert(lstat(pathname,&st) == 0) ;
	/*printf("%s  %d ",pathname,taille_fichier) ;*/
	taille_fichier += opt_apparent_size?st.st_size:st.st_blocks;
	/*printf("%d\n",st.st_blocks) ;*/
	if(S_ISDIR(st.st_mode)) {
	/* Si le fichier est un répértoire */
	
		rep = opendir(pathname) ;
		assert(rep != NULL) ;
		while( (rep_suiv = readdir(rep)) != NULL ) {
			if( (strcmp(".",rep_suiv->d_name) != 0) && (strcmp("..",rep_suiv->d_name) != 0) ) {
				snprintf(subdir_pathname,PATH_MAX,"%s/%s",pathname,rep_suiv->d_name) ;
				du_file(subdir_pathname) ;
			}
		
		}
		closedir(rep) ;
	}
	
	return taille_fichier ;
}

int du_symbolic_link(const char * pathname) {
/* 
 * 
 * stat
 * "on vérifie si c'est un lien_symbolique S_SLNK
 * on stock l'inoeud ( st_ino) et le périphérique (st_dev)
 * on regarde dans notre struct 
 * si l'inoeud et le périphérique n'est pas dans notre struct 
 *  On regarde si c'est le même fichier si c'est le cas ===> readlink 
 *  sinon on calcule 
 * */
	DIR * rep;
	struct stat st;
	struct dirent * rep_suiv;
	char subdir_pathname[PATH_MAX];
	dev_t periph;
	ino_t inode;
	ssize_t octet_buff_symbolic;
	
	
	if(stat(pathname,&st) != 0){
		perror("");
		exit(EXIT_FAILURE);
	}
	periph = st.st_dev;
	inode = st.st_ino;
	/*printf("%d %s %lu %lu %d\n",st.st_blocks,pathname,periph,inode,taille_fichier) ;*/
	
	
	
	if(!contain(maListe,periph,inode)){
		insertion(maListe,periph,inode);
		taille_fichier += opt_apparent_size?st.st_size:st.st_blocks;
		if(S_ISLNK(st.st_mode)) {
			octet_buff_symbolic = readlink(pathname,subdir_pathname,PATH_MAX) ;
			assert(octet_buff_symbolic != -1) ;
			subdir_pathname[octet_buff_symbolic] = 0 ;
			du_symbolic_link(subdir_pathname) ;
			
		}
		if(S_ISDIR(st.st_mode)) {
		/* Si le fichier est un répértoire */
	
		rep = opendir(pathname) ;
		assert(rep != NULL) ;
		while( (rep_suiv = readdir(rep)) != NULL ) {
			if( (strcmp(".",rep_suiv->d_name) != 0) && (strcmp("..",rep_suiv->d_name) != 0) ) {
				snprintf(subdir_pathname,PATH_MAX,"%s/%s",pathname,rep_suiv->d_name) ;
				du_symbolic_link(subdir_pathname) ;
			}
		
		}
		closedir(rep) ;
	}
	}
	return taille_fichier;
}


int main(int argc,char * argv[]) {
	int c ;
	while ((c = getopt(argc , argv, "bL")) != -1) {
		switch (c) {
			case 'b' :
				opt_apparent_size = 1 ;
				break;
			case 'L' :
				opt_follow_links = 1 ;
				break;
		}
	
	}
	maListe = initialisation();
	opt_follow_links?du_symbolic_link(argv[argc-1]):du_file(argv[argc-1]) ;
	printf("%d\n",taille_fichier) ;
	
	suppressionTotal(maListe) ;
	return 0 ;

}





