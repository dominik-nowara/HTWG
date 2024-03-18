
#include "fachnote.h"
#include <stdexcept>

fachnote::fachnote(const std::string &f, const benotung &b) : fach(f), note(b) {
    if (f.empty()) {
        throw std::invalid_argument("unzulaessiges Fach");
    }
}