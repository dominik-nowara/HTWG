#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 23.2.2022
#

CC = gcc -g -fno-stack-protector -Wwrite-strings
CFLAGS = -Wall -Wextra -Werror -std=$(STD) -pedantic
CPPCHECK = cppcheck --enable=warning,style --std=$(STD)
STD = c11

TARGET = aufgabe1

.PHONY: all clean cppcheck

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(TARGET).o

cppcheck: $(TARGET).c
	$(CPPCHECK) $<

