package bibliothek;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String name;
    private final List<Buch> ausgelieheneBuecher = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean leihtAus(Buch b) {
        if (b.getEntleiher() != null)
            return false;

        for (var buch : ausgelieheneBuecher) {
            if (buch.equals(b)) {
                return false;
            }
        }

        ausgelieheneBuecher.add(b);
        b.wirdAusgeliehen(this);
        return true;
    }

    public boolean gibtZurueck(Buch b) {
        if (!ausgelieheneBuecher.contains(b))
            return false;

        ausgelieheneBuecher.remove(b);
        b.wirdZurueckGegeben();
        return true;
    }

    public void print() {
        StringBuilder stringBuilder = new StringBuilder("Person: ");
        stringBuilder.append(name);
        stringBuilder.append(" ");

        for (int i = 0; i < ausgelieheneBuecher.size(); i++) {
            stringBuilder
                    .append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(ausgelieheneBuecher.get(i).getName());
        }

        if (ausgelieheneBuecher.isEmpty()) {
            stringBuilder
                    .append("\n")
                    .append("Keine Bücher ausgeliehen");
        }

        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person))
            return false;

        Person p = (Person) obj;

        return this.name.equals(p.name);
    }
}
