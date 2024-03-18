// Notenspiegel.java
package aufgabe5;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Notenspiegel liest die Namen von F&auml;chern mit den zugeh&ouml;rigen Noten
 * in eine verkettete Liste ein und gibt dann einen Notenspiegel aus.
 * @author Dominik Nowara
 * @version 1.0
 */
public final class Notenspiegel {
    private Notenspiegel() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        FachnotenListe liste = new FachnotenListe(); // leere Liste

        //--------------------------------------------- Notenspiegel einlesen
        System.err.printf(
            "Faecher mit Noten zwischen %d und %d eingeben "
            + "(Ende mit Strg-D):%n",
            Note.BESTE.intValue(), Note.SCHLECHTESTE.intValue());

        while (EINGABE.hasNext()) {
            try {
                //------------------------------------ Fach und Note einlesen

                String notenWert = EINGABE.next();

                if (EINGABE.hasNextInt()) {
                    var note = Note.valueOf(EINGABE.nextInt());
                    liste.insert(new Fachnote(notenWert, note));
                } else {
                    var note = Note.valueOf(EINGABE.next());
                    liste.insert(new Fachnote(notenWert, note));
                }

                //--------------------- neue Fachnote in Notenliste eintragen


            } catch (IllegalArgumentException x) {
                System.err.printf("Eingabefehler: %s%n", x.getMessage());
                continue;
            } catch (NoSuchElementException x) {
                System.err.println("Fach ohne Note ignoriert!");
                break;
            }
        }

        //--------------------------------------------- Notenspiegel ausgeben

        final int zehn = 10;

        System.out.println("NOTENSPIEGEL");
        var i = liste.new Iterator();
        while (i.hasNext()) {
            var next = i.next();

            var notenAusgabe = (next.note.istBestanden())
                    ? "bestanden" : "nicht bestanden";
            if (next.note.intValue() == zehn) {
                notenAusgabe = "mit Bestnote bestanden";
            }

            System.out.println(String.format("%-25s", next.fach)
                + String.format("%-5s", next.note.toString())
                + notenAusgabe);
        }

    } // main
}

