
#ifndef FACHNOTEN_LISTE_H
#define FACHNOTEN_LISTE_H

#include "fachnote.h"

class fachnoten_liste final {
    fachnoten_liste(fachnoten_liste&) = delete;
    fachnoten_liste& operator=(fachnoten_liste&) = delete;
    fachnoten_liste(fachnoten_liste&&) = delete;
    fachnoten_liste& operator=(fachnoten_liste&&) = delete;

private:
    class element;
    element *head;
    void (*function)(fachnote*);

public:
    explicit fachnoten_liste(void (*f)(fachnote*));
    ~fachnoten_liste();
    fachnoten_liste& insert(fachnote*);

    class iterator final {
        private:
            element *current;
            explicit iterator(element*);
        public:
            bool operator!=(const iterator&) const;
            fachnote* operator*() const;
            iterator& operator++();

            friend class fachnoten_liste;
    };

    iterator begin();
    iterator end();
};

#endif
