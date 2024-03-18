#
# Makefile
#

# Kommando-Variablen
CXX = g++
CXXFLAGS = -g -W -Wall -Weffc++ -Wold-style-cast -std=$(STD) -fpic
CPPFLAGS = -I$(LIBDIR)
# erster Parameter sagt dem statischen Linker wo die Bibliothek ist. Zweiter Parameter sagt dem dynamischen Linker (Loader) wo die Bibliothek ist
LDFLAGS = -L$(LIBDIR) -Wl,-rpath=$(LIBDIR)
RM = rm -f
STD = c++11

# Hilfsvariablen
TARGET = notenspiegel
OBJECTS = \
	$(TARGET).o
SOURCES = $(OBJECTS:.o=.cpp)
HEADERS = \
	$(LIBDIR)/fachnoten_liste.h \
	$(LIBDIR)/benotung.h \
	$(LIBDIR)/fachnote.h
LIBDIR = ../lib
LIBNAME = aufgabe6
LIBTYPE = a

# Musterregeln
%.o: %.cpp
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

# Standardziele
.PHONY: all clean

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(OBJECTS) depend

depend: $(SOURCES) $(HEADERS)
	$(CXX) $(CPPFLAGS) -MM $(SOURCES) > $@

# Ziele zur Programmerstellung
$(TARGET): $(OBJECTS) $(LIBDIR)/lib$(LIBNAME).$(LIBTYPE)
	$(CXX) $(CPPFLAGS) $(LDFLAGS) -o $@ $< -l:lib$(LIBNAME).$(LIBTYPE)

# Abhaengigkeiten
include depend
