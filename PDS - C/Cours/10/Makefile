CC      = gcc

CFLAGS  = -Wall -W
# Désactive un avertissement particulier, parce qu’on sait qu’il doit
# arriver
CFLAGS += -Wno-unused-parameter
CFLAGS += -g

LDFLAGS  = -g
LDFLAGS += -pthread

TOUS := exit
TOUS += thread-term thread-term2
TOUS += thread-fork-vis
TOUS += serveur client serveur-mt
TOUS += thread-prodcons
TOUS += test-fifo
TOUS += Synch.class
TOUS += writestruct mmapstruct
TOUS += explorepile

tout: ${TOUS}

# Ajoute des dépendances
serveur.o: serveur.h
serveur-mt.o: serveur.h
client.o: serveur.h

%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

%.class: %.java
	javac $^

clean:
	rm -f core *.o

realclean: clean
	rm -f ${TOUS}

.PHONY: all clean realclean
