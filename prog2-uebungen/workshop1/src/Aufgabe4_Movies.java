import com.sun.source.tree.Tree;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Aufgabe4_Movies {

    public static void loesung() throws IOException {
        // a) (1P)
        // Die record-Klasse Movie stellt zwei Konstruktoren zur Verfügung.
        // Testen Sie die beiden Konstruktoren und erklären Sie die Funktionsweise.
        System.out.println("\nAufgabe 4a (1P):");
        Movie movie1 = new Movie("101 Dalmatians", List.of("Benfield, John", "Braid, Hilda", "Capron, Brian"),1996);
        Movie movie2 = new Movie("101 Dalmatians (1996)/Benfield, John/Braid, Hilda/Capron, Brian");
        // Ausgabe von m1 und m2 sollte identisch sein:
        System.out.println(movie1);
        System.out.println(movie2);


        // b) (4P)
        // Die Datei data/movies-top-grossing.txt enthält einige erfolgreiche Kinofilme (jeweils eine Zeile) des 20. Jahrhunderts.
        // Jeder Film besteht aus einem Titel, einer Liste von Schauspieler und das Erscheinungsjahr.
        // Ergänzen Sie die gegebene statische Methode einlesen so, dass alle Filme in eine Liste eingelesen und zurückgegeben werden.
        // Geben Sie alle Filme nach Jahreszahlen sortiert aus.
        // Geben Sie dabei zuerst die Jahreszahl und dann den Filmtitel in einer Zeile aus (Schauspieler werden weggelassen).
        System.out.println("\nAufgabe 4b (2P):");
        List<Movie> movieList = einlesen("data/movies-top-grossing.txt");
        movieList.sort((s1, s2) -> s1.year() - s2.year());
        for (var movie : movieList) {
            System.out.println("(" + movie.year() + ") " + movie.title());
        }


        // c) (5P)
        // Erstellen aus der Liste movieList eine Map, die jeder Jahreszahl die entsprechende Menge der Filmtitel zurordnet.
        // Geben Sie die Map aus, indem Sie für jeden Eintrag zuerst die Jahreszahl und dann die Filmtitel eingerückt
        // in jeweils eine Zeile ausgeben.
        System.out.println("\nAufgabe 4c (5P):");
        Map<Integer, Set<String>> jahrToTitel = new TreeMap<>();
        for (var movie : movieList) {
            Set<String> titleSet;
            if (!jahrToTitel.containsKey(movie.year())) {
                titleSet = new TreeSet<>();
            }
            else {
                titleSet = jahrToTitel.get(movie.year());
            }
            titleSet.add(movie.title());
            jahrToTitel.put(movie.year(), titleSet);
        }

        for (var movieYears : jahrToTitel.keySet()) {
            Set<String> movies = jahrToTitel.get(movieYears);
            System.out.println("(" + movieYears + ")");

            for (var movie : movies) {
                System.out.println("\t" + movie);
            }
        }

        // d) (5P)
        // Erstellen aus der Liste movieList eine Map, die jedem/r Schauspieler/in die Menge der Filmtitel zurordnet,
        // in der er/sie mitgewirkt hat.
        // Geben Sie die Map für alle mit 'B' beginnende Schauspieler/innen aus,
        // indem Sie für jeden Eintrag zuerst der/die Schauspieler/in und dann die Filmtitel eingerückt in jeweils eine Zeile ausgeben.
        // Wieviel unterschiedliche Schaupieler gibt es?
        System.out.println("\nAufgabe 4d (5P):");
        SortedMap<String, Set<String>> actorToTitel = new TreeMap<>();

        for (var movies : movieList) {
            List<String> actors = movies.actors();
            for (String actor : actors) {
                Set<String> titleSet;
                if (!actorToTitel.containsKey(actor)) {
                    titleSet = new TreeSet<>();
                } else {
                    titleSet = actorToTitel.get(actor);
                }
                titleSet.add(movies.title());
                actorToTitel.put(actor, titleSet);
            }
        }

        for (var actors : actorToTitel.keySet()) {
            Set<String> movies = actorToTitel.get(actors);
            System.out.println(actors);
            for (var movie : movies) {
                System.out.println("\t" + movie);
            }
        }

        // e) (5P)
        // Ermitteln Sie aus der Map von d) die fünf Schauspieler/innen,
        // die in den meisten Filmen mitgewirkt haben.
        System.out.println("\nAufgabe 4e (5P):");
        List<Map.Entry<String, Set<String>>> sortedActors = new ArrayList<>(actorToTitel.entrySet());
        sortedActors.sort(((o1, o2) -> o2.getValue().size() - o1.getValue().size()));

        for (int i = 0; i < 5; i++) {
            System.out.println(sortedActors.get(i).getKey() + " (" + sortedActors.get(i).getValue().size() + ")");
        }
    }

    private static List<Movie> einlesen(String fn) throws IOException {
        List<Movie> movieList = new LinkedList<>();
        LineNumberReader in = new LineNumberReader(new FileReader(fn, StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            movieList.add(new Movie(line));
        }
        return movieList;
    }
}
