tout: creat creat2 environnement

creat: creat.o
	gcc -Wall -ansi -pedantic -g $^ -o $@

creat2: creat2.o
	gcc -Wall -ansi -pedantic -g $^ -o $@

environnement: environnement.o
	gcc -Wall -ansi -pedantic -g $^ -o $@

%.o: %.c
	gcc -Wall -ansi -pedantic -g -c $^

clean:
	rm -f *.o creat creat2 environnement
