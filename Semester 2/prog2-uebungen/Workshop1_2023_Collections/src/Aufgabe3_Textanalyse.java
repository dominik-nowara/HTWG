import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Aufgabe3_Textanalyse {
    public static void loesung() throws IOException {
        //
        // a) (5P)
        // Die Datei Kafka_Der_Prozess.txt soll eingelesen werden und verschiedene Auswertungen erfolgen.
        // Ergänzen Sie die Funktion einlesen so, dass die eingelesenen Wörter als Liste zurückgeliefert werden.
        System.out.println("\nAufgabe 3a (4P):");
        long start = System.nanoTime(); // aktuelle Zeit in nsec
        List<String> lst_Kafka = einlesen("data/Kafka_Der_Prozess.txt");
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);

        // Geben Sie die Anzahl der eingelesenen Wörter aus. Benutzen Sie dazu Ihre eingelesene Liste.
        // Ihr Code: ...

        // Sortieren Sie die Liste und geben Sie die ersten 100 Wörter aus.
        start = System.nanoTime();
        // Ihr Code: ...
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);

        // Speichern Sie die Liste in eine TreeSet und geben Sie ersten die 100  Wörter aus.
        // Berücksichtigen Sie die Konstruktoren der Klasse TreeSet!
        // Ihr Code: ...


        //
        // b) (5P)
        // Verwenden Sie die bereits eingelesene Liste lst_Kafka und erstellen Sie eine Häufigkeitstabelle als SortedMap.
        // Wieviel unterschiedliche Wörter gibt es?
        // Geben Sie mit Hilfe von subMap alle Wörter (mit ihren Häufigkeiten) aus, die mit "Ver" beginnen.
        // Geben Sie die 20 häufigsten Wörter (mit ihren Häufigkeiten) aus.
        System.out.println("\nAufgabe 3b (4P):");
        start = System.nanoTime();
        SortedMap<String, Integer> fqTable_Kafka = ermittleHaeufigkeiten(lst_Kafka);
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
        // Ihr Code: ...


        //
        // c) (5P)
        // Ein Wort gilt als falsch geschrieben, wenn es nicht in der Wörterbuchdatei word_list_german_spell_checked.txt vorkommt.
        // Das Einlesen der Datei word_list_german_spell_checked.txt dauert etwas Zeit, da sie mehr als 2 Millionen Einträge enthält.
        // Ermitteln Sie für den Kafka-Text eine Häufigkeitstabelle der falsch geschriebenen Wörter.
        // Wieviel falsch geschriebene Wörter gibt es?
        // Geben Sie die 20 häufigsten falsch geschriebenen Wörter (mit ihren Häufigkeiten) aus.
        System.out.println("\nAufgabe 3c (3P):");

        start = System.nanoTime();
        // word_list_german_spell_checked.txt einlesen:
        // Ihr Code: ..
        System.out.println("Benötigte Zeit in msec: " + (double)(System.nanoTime()-start)/1.0e06);
        // Ihr Code:
        // ...


        //
        // d) (5P)
        // Ermitteln Sie auch für Harry_Potter_und_der_Stein_der_Weisen.txt eine Häufigkeitstabelle.
        // Ermitteln Sie eine Häufigkeitstabelle der Wörter, die sowohl in Kafka_Der_Prozess.txt als auch
        // in Harry_Potter_und_der_Stein_der_Weisen.txt vorkommen, indem Sie die Häufigkeiten der gemeinsamen Wörter addieren.
        // Wieviel unterschiedliche Wörter gibt es in jedem Buch?
        // Wieviel unterschiedliche gemeinsame Wörter gibt es?
        // Geben Sie die 20 häufigsten gemeinsamen Wörter (mit ihren Häufigkeiten) aus.
        System.out.println("\nAufgabe 3d (3P):");
        // Ihr Code:
        // ...
    }

    private static List<String> einlesen(String fileName) throws IOException {
        LineNumberReader in = new LineNumberReader(new FileReader(fileName, StandardCharsets.UTF_8));
        List<String> list = new ArrayList<>();
        String line;

        while ((line = in.readLine()) != null) {
            String[] wf = line.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü]+");
            for (String w: wf) {
                if (w.length() == 0 || w.length() == 1)
                    continue;
                // ...
            }
        }
        return list;
    }

    private static SortedMap<String, Integer> ermittleHaeufigkeiten(List<String> wListe)  {
        // Ihr Code:
        // ...
        return null;
    }
}
