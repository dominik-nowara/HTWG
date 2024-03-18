// HtmlNotenspiegel.java
package aufgabe6;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Die Main-Klasse HtmlNotenspiegel liest die Namen von F&auml;chern
 * mit den zugeh&ouml;rigen Noten in eine verkettete Liste ein und
 * gibt dann einen Notenspiegel im HTML-Format aus.
 * Aufruf mit dem Namen der Studentin / des Studenten als Argument(e)
 * @author H.Drachenfels
 * @version 28.7.2021
 */
public final class HtmlNotenspiegel {
    private HtmlNotenspiegel() { }

    /**
     * liest die Namen von F&auml;chern mit den zugeh&ouml;rigen Beurteilungen
     * ein und schreibt einen Notenspiegel als Datei im HTML-Format.
     * <p>
     * Jede Eingabezeile muss einen Fachnamen und eine Beurteilung enthalten.
     * Fachnamen m&uuml;ssen laut
     * {@link aufgabe6.Faecher#istZulaessig} erlaubt sein.
     * Bei unbenoteten F&auml;chern muss die Beurteilung
     * <code>BE</code> f&uuml;r bestanden oder
     * <code>NB</code> f&uuml;r nicht bestanden lauten.
     * Bei benoteten F&auml;chern muss die Note laut
     * {@link aufgabe5.Note#valueOf(java.lang.String)} erlaubt sein.
     * Der Dateinname des Notenspiegels wird aus den Argumenten gebildet.</p>
     * @param args Vorname(n) Nachname (mindestens zwei Argumente)
     * @throws IOException bei Einleseproblemen
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println(
                "Aufruf: java HtmlNotenspiegel Vorname(n) Nachname");
            System.exit(1);
        }

        LeistungsListe leistungen = new LeistungsListe();
        einlesen(leistungen);
        ausgeben(args, leistungen);
    }

    private static void einlesen(LeistungsListe leistungen)
        throws IOException {

        System.err.println("Faecher mit Noten eingeben (Ende mit Strg-D):");
        BufferedReader eingabe
            = new BufferedReader(new InputStreamReader(System.in));

        String zeile;
        while ((zeile = eingabe.readLine()) != null) {
            zeile = zeile.trim();
            if (zeile.length() == 0) {
                continue; // Leerzeile
            }

            try {
                String[] woerter = zeile.split("\\s+");
                if (woerter.length < 2) {
                    throw new IllegalArgumentException(
                        "unvollstaendige Zeile " + zeile);
                }

                String note = woerter[woerter.length - 1];
                String fach = zeile.substring(0, zeile.lastIndexOf(note));

                Leistung fachnote;
                if (note.equals("BE")) {
                    fachnote = new UnbenoteteLeistung(fach.trim(), true);
                } else if (note.equals("NB")) {
                    fachnote = new UnbenoteteLeistung(fach.trim(), false);
                } else {
                    fachnote = new BenoteteLeistung(fach.trim(), note);
                }

                leistungen.insert(fachnote);
            } catch (IllegalArgumentException x) {
                System.err.printf("Falscheingabe ignoriert: %s%n",
                                  x.getMessage());
                continue;
            }
        }
    }

    private static void ausgeben(String[] name, LeistungsListe leistungen)
        throws IOException {

        StringBuilder sb = new StringBuilder();
        for (String s : name) {
            sb.append(s);
        }

        try {
            PrintWriter ausgabe
                = new PrintWriter(sb.toString() + ".html", "UTF-8");
            HtmlFormat.ausgeben(ausgabe, name, leistungen);
            ausgabe.close();
        } catch (FileNotFoundException x) {
            System.err.println(x.getMessage());
            System.exit(1);
        }
    }
}

