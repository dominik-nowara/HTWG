
#ifndef BENOTUNG_H
#define BENOTUNG_H

class benotung {
private:
    int note;

public:
    static const benotung beste;
    static const benotung schlechteste;

    explicit benotung(int n);

    int int_value() const;
    bool ist_bestanden() const;

    friend bool operator==(const benotung&, const benotung&);
};

#endif