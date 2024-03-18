
#include "benotung.h"
#include <string>

#ifndef FACHNOTE
#define FACHNOTE

class fachnote {
public: 
    const std::string fach;
    const benotung note;

    fachnote(const std::string&, const benotung&);

    fachnote(const fachnote&) = delete;
    fachnote& operator=(const fachnote&) = delete;
};

#endif