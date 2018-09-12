CC      = gcc

CFLAGS  = -Wall -W
# Désactive un avertissement particulier, parce qu’on sait qu’il doit
# arriver
CFLAGS += -Wno-unused-parameter
CFLAGS += -g

LDFLAGS  = -g
LDFLAGS += -pthread

TOUS := aleazard compteur-gc tri-rapide

tout: ${TOUS}

tri-rapide: pile.o tri.o rapide.o main.o
	${CC} ${LDFLAGS} -o $@ $^


%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

clean:
	rm -f core *.o

realclean: clean
	rm -f ${TOUS}

.PHONY: all clean realclean
