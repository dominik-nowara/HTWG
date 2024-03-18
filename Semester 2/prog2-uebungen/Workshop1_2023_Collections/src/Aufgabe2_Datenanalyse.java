import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Aufgabe2_Datenanalyse {

    public static void loesung() throws IOException {

        // a) (2P)
        // Werfen Sie einen Blick in die Statistik-Datei:
        //      data/12411-0017-KREISE_$F.csv bzw. data/12411-0017-KREISE_$F.xlsx
        // In der Datei sind alle Landkreise von Deutschland mit einer Altersverteilung aufgelistet.
        // Die gegebene statische Methode einlesen liest alle Landkreise in eine Liste ein.
        // Geben Sie die Liste (jeder Landkreis in eine Zeile) aus und bestimmen Sie die Anzahl der Landkreise.
        System.out.println("\nAufgabe 2a (2P):");
        // Ihr Code
        // ...


        // b) (2P)
        // Sortieren Sie die Liste nach dem Namen und geben Sie die Liste (jeder Landkreis in eine Zeile) aus:
        System.out.println("\nAufgabe 2b (2P):");
        // Ihr Code
        // ...


        // c) (2P)
        // Sortieren Sie die Liste absteigend nach der Einwohnerzahl und geben Sie die Liste (jeder Landkreis in eine Zeile) aus:
        System.out.println("\nAufgabe 2c (2P):");
        // Ihr Code
        // ...


        // d) (3P)
        // Erstellen Sie eine Map, die für jeden Landkreisnamen die Einwohnerzahl speichert.
        // Geben Sie für alle mit 'K' beginnenden Landkreise den Namen und die Einwohnerzahl aus.
        // Benutzen Sie dazu subMap!
        System.out.println("\nAufgabe 2d (3P):");
        // Ihr Code
        // ...

        // e) (1P)
        // Geben Sie die Menge (Set) aller Landkreisnamen aus:
        System.out.println("\nAufgabe 2e (1P):");
        // Ihr Code
        // ...
    }

    private static List<Landkreis> einlesen(String fn) throws IOException {
        List<Landkreis> kreiseBRD = new LinkedList<>();

        // lese ";"-separated file
        LineNumberReader in = new LineNumberReader(new FileReader(fn, StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            Scanner lineScanner = new Scanner(line).useDelimiter(";");
            // Zeilen, die keinen Landkreis beschreiben, werden ausgelassen:
            if (!lineScanner.hasNextInt())
                continue;
            int plz = lineScanner.nextInt();
            String name = lineScanner.next();
            // Landkreise ohne Einwohnerzahlen werden ausgelassen:
            if (!lineScanner.hasNextInt())
                continue;
            // Lese die Einwohnerzahl ewz in der letzten Spalte:
            int ewz = 0;
            while (lineScanner.hasNextInt())
                ewz = lineScanner.nextInt();
            kreiseBRD.add(new Landkreis(name,plz,ewz));
        }

        return kreiseBRD;
    }
}
