#
# Makefile
#

# Kommando-Variablen
CXX = g++
CXXFLAGS = -g -W -Wall -Weffc++ -Wold-style-cast -std=$(STD) -fpic
CPPFLAGS = -I.
RM = rm -f
STD = c++11
SL = ar rvs
DL = -shared

# Hilfsvariablen
TARGET = lib$(LIBNAME)
OBJECTS =  \
    benotung.o \
    fachnote.o \
    fachnoten_liste.o
SOURCES = $(OBJECTS:.o=.cpp)
HEADERS = $(OBJECTS:.o=.h)
LIBNAME = aufgabe6
LIBTYPE = a

# Musterregeln
%.o: %.cpp
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

# Standardziele
.PHONY: all clean

all: $(TARGET).$(LIBTYPE)

clean:
	$(RM) $(TARGET).a $(TARGET).so $(OBJECTS) depend

depend: $(SOURCES) $(HEADERS)
	$(CXX) $(CPPFLAGS) -MM $(SOURCES) > $@

# Ziele zur Programmerstellung
$(TARGET).a: $(OBJECTS)
	$(SL) $@ $^

$(TARGET).so: $(OBJECTS)
	$(CXX) $(DL) -o $@ $^

# Abhaengigkeiten
include depend
