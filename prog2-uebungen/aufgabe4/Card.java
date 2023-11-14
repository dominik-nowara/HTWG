package aufgabe4;

import java.util.Random;

public abstract class Card {
    public static Random random = new Random();

    public enum Suit {
        KARO,
        HERZ,
        PIQUE,
        KREUZ
    }

    public enum Rank {
        SIEBEN,
        ACHT,
        NEUN,
        ZEHN,
        BUBE,
        DAME,
        KOENIG,
        ASS
    }

    Suit farbe;
    Rank wert;

    public Card(Suit farbe, Rank wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    @Override
    public String toString() {
        return farbe + " " + wert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Card card = (Card) o;
        return farbe == card.farbe && wert == card.wert;
    }
}
