// LeistungsListe.java
package aufgabe6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LeistungsListe kapselt eine Liste von Leistungen.
 * @author Dominik Nowara
 * @version 13.06.2023
 */
public final class LeistungsListe implements Iterable<Leistung> {
    private Element head = null; // leere Liste
    /**
     * F&uuml;gt eine Zahl am Listenanfang ein.
     * Der heimliche Paramter this verweist auf das Objekt des Aufrufs.
     * @param n die einzuf&uuml;gende Zahl
     *  @return die Liste
     */
    public LeistungsListe insert(Leistung n) {
        this.head = new Element(this.head, n);
        return this;
    }

    /**
     * Element speichert eine einzelne Zahl als Teil einer Liste.
     * Beipiel f&uuml;r eine statisch eingebettete Klasse.
     */
    private static final class Element {
        private final Element next;
        private final Leistung n;

        private Element(/* final Element this, */ Element e, Leistung n) {
            this.next = e;
            this.n = n;
        }
    }

    /**
     * Iterator speichert den aktuellen Zustand einer Listeniteration.
     * Beipiel f&uuml;r eine innere Klasse.
     */
    @Override
    public Iterator<Leistung> iterator() {
        return new Iterator<Leistung>() {
            private Element current = LeistungsListe.this.head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public Leistung next() {
                if (this.current == null) {
                    throw new NoSuchElementException();
                }
                Element e = this.current;
                this.current = this.current.next;
                return e.n;
            }
        };
    }
}

