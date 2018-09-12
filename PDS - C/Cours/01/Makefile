tout: normal

normal: normal.o
	gcc -Wall -ansi -pedantic -g $^ -o $@

normal-static: normal.o
	gcc -Wall -ansi -pedantic -g -static $^ -o $@

%.o: %.c
	gcc -Wall -ansi -pedantic -g -c $^

clean:
	rm -f *.o normal
