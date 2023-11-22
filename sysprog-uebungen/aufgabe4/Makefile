#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 14.9.2023
#

CC = gcc -g
CPPFLAGS =  -D_POSIX_C_SOURCE=200112L
CFLAGS = -Wall -Wextra -Werror -std=$(STD) -pedantic
CPPCHECK = cppcheck --enable=warning,style --std=$(STD)
STD = c11

TARGET = listfiles
OBJECTS = fileinfo.o

.PHONY: all clean cppcheck

all: $(TARGET)

clean:
	rm -f $(TARGET) $(TARGET).o $(OBJECTS)

cppcheck: $(TARGET).c $(OBJECTS:.o=.c)
	$(CPPCHECK) $^

$(TARGET): $(TARGET).o $(OBJECTS)
	$(CC) $(LDFLAGS) $^ -o $@

listfiles.o: listfiles.c fileinfo.h
fileinfo.o: fileinfo.c fileinfo.h
