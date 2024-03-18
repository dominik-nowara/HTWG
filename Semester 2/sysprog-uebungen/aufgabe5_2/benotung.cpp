
#include <stdexcept>
#include "benotung.h"

const benotung benotung::beste = benotung(10);
const benotung benotung::schlechteste = benotung(50);

benotung::benotung(int n) : note(n) {
    std::string errorMessage = std::string("unzulaessige Note ") + std::to_string(n);

    if (n < 10 || n > 50 || (n % 10 != 0 && n % 10 != 3 && n % 10 != 7)) {
        throw std::invalid_argument(errorMessage);
    }
}

int benotung::int_value() const {
    return this->note;
}

bool benotung::ist_bestanden() const {
    return note >= 40;
}

bool operator==(const benotung& l, const benotung& r) {
    return &l == &r || l.int_value() == r.int_value();
}