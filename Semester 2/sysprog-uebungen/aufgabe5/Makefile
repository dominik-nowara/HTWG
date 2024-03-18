#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 30.8.2021
#

CXX = g++ -g #-fno-elide-constructors
CXXFLAGS = -Wall -Wextra -Werror -Weffc++ -Wold-style-cast -std=$(STD) -pedantic
CPPCHECK = cppcheck --enable=warning,style --std=$(STD)
STD = c++11

TARGET = notenspiegel
OBJECTS = \
    benotung.o \
    fachnote.o \
    fachnoten_liste.o \

.PHONY: all clean cppcheck

all: $(TARGET)

clean:
	rm -f $(TARGET) $(TARGET).o $(OBJECTS)

$(TARGET): $(TARGET).o $(OBJECTS)
	$(CXX) $(LDFLAGS) -o $@ $^

cppcheck: $(TARGET).cpp $(OBJECTS:.o=.cpp)
	$(CPPCHECK) $^

benotung.o: benotung.cpp benotung.h
fachnote.o: fachnote.cpp fachnote.h benotung.h
fachnoten_liste.o: fachnoten_liste.cpp fachnoten_liste.h fachnote.h benotung.h
notenspiegel.o: notenspiegel.cpp fachnoten_liste.h fachnote.h benotung.h

