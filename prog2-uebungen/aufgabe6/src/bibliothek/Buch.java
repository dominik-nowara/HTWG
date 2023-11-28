package bibliothek;

public class Buch {
    private final String name;
    private Person entleiher;

    public Buch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person getEntleiher() {
        return entleiher;
    }

    public boolean wirdAusgeliehen(Person p) {
        if (entleiher != null && !entleiher.equals(p)) {
            return false;
        }
        else if (entleiher == null) {
            this.entleiher = p;
            p.leihtAus(this);
        }

        return true;
    }

    public boolean wirdZurueckGegeben() {
        if (entleiher == null)
            return false;

        entleiher.gibtZurueck(this);
        entleiher = null;
        return true;
    }

    public void print() {
        if (entleiher != null) {
            System.out.println(name + " - " + entleiher.getName());
        }
        else {
            System.out.println(name + " - Nicht ausgeliehen");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Buch))
            return false;

        Buch b = (Buch) obj;

        return b.name.equals(this.name);
    }
}
