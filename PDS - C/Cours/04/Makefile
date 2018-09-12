CC      = gcc

CFLAGS  = -Wall -W -ansi -pedantic
CFLAGS += -g

LDFLAGS = -g

TOUS:= exfork

tout: ${TOUS}

%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

clean:
	rm core *.o || true

realclean: clean
	rm ${TOUS} || true

.PHONY: all clean realclean
