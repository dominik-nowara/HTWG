/*
 * notenspiegel.cpp
 *
 * Liest die Namen von Faechern mit den zugehoerigen Noten in eine
 * verkettete Liste ein und gibt dann einen Notenspiegel aus.
 *
 * Autor: H.Drachenfels
 * Version: 19.12.2019
 */
#include "benotung.h"
#include "fachnote.h"
#include "fachnoten_liste.h"
#include <iostream>
#include <iomanip>
#include <stdexcept>
#include <cstdio>
#include <string>

namespace
{
    std::ostream& operator<<(std::ostream& os, const benotung& n);
    void delete_fachnote(fachnote *);
}

int main(void)
{
    fachnoten_liste liste(delete_fachnote); // leere Liste

    //--------------------------------------------- Notenspiegel einlesen
    std::cerr << "Fach und Note zwischen "
              << benotung::beste.int_value()
              << " und "
              << benotung::schlechteste.int_value()
              << " eingeben (Ende mit Strg-D):\n";

    while (true)
    {
        try
        {
            // Fach und Note einlesen:
            std::string fach;
            if (!(std::cin >> fach))
            {
                break;
            }

            int note;
            if (!(std::cin >> note))
            {
                if (std::cin.eof())
                {
                    std::cerr << "Fach " << fach << " ohne Note ignoriert!\n";
                    break;
                }

                std::cin.clear();
                std::string s;
                std::cin >> s;
                std::cerr << "Eingabefehler: " << s << '\n';
                continue;
            }

            // neue Fachnote in Notenliste eintragen:
            fachnote *f = new fachnote{fach, benotung{note}};
            liste.insert(f);
        }
        catch (std::invalid_argument& x)
        {
            std::cerr << "Eingabefehler: " << x.what() << '\n';
            continue;
        }
    }

    //--------------------------------------------- Notenspiegel ausgeben
    std::size_t max_laenge = 0;
    for (fachnote *f : liste)
    {
        if (max_laenge < f->fach.length())
        {
            max_laenge = f->fach.length();
        }
    }

    std::cout << "\nNOTENSPIEGEL\n";
    for (fachnote *f : liste)
    {
        std::cout << std::setw(max_laenge) << std::left << f->fach << '\t'
                  << f->note << '\t';
        if (f->note == benotung::beste)
        {
            std::cout << "mit Bestnote bestanden\n";
        }
        else if (f->note.ist_bestanden())
        {
            std::cout << "bestanden\n";
        }
        else
        {
            std::cout << "nicht bestanden\n";
        }
    }
}

namespace
{
    std::ostream& operator<<(std::ostream& os, const benotung& n)
    {
        int note = n.int_value();
        os << note / 10 << ',' << note % 10;
        return os;
    }

    void delete_fachnote(fachnote *f)
    {
        delete f;
    }
}

