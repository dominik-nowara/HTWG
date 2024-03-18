#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 18.7.2022
#

CC = gcc -g
CFLAGS = -Wall -Wextra -Werror -Wvla -std=$(STD) -pedantic
CPPCHECK = cppcheck --enable=warning,style --std=$(STD)
STD = c11

TARGET = stringsort

.PHONY: all clean cppcheck

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(TARGET).o

cppcheck: $(TARGET).c
	$(CPPCHECK) $<

