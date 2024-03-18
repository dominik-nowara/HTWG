import java.util.*;

public class Aufgabe1_WarmUp {
    public static void loesung() {
        System.out.println("\nAufgabe 1a (2P):");
        aufgabe_a();

        System.out.println("\nAufgabe 1b (3P):");
        aufgabe_b();

        System.out.println("\nAufgabe 1c (2P):");
        aufgabe_c();

        System.out.println("\nAufgabe 1d (3P):");
        aufgabe_d();
    }

    private static void aufgabe_a() {
        // Schreiben Sie eine statische Methode append(l1,l2),
        // die zwei Listen l1 und l2 aneinanderhängt und zurückliefert.
        // l1 und l2 bleiben unverändert!

        List<Integer> l1 = new LinkedList<>();
        l1.add(4);
        l1.add(500);
        l1.add(1);
        l1.add(5);
        l1.add(8);

        List<Integer> l2 = new ArrayList<>();
        l2.add(144);
        l2.add(500);
        l2.add(1);
        l2.add(1);
        l2.add(7);

        List<Integer> l = append(l1, l2);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l);

        l = append(l, l);
        System.out.println(l);
    }

    private static <T> List<T> append(List<T> l1, List<T> l2) {
        // Ihr Code:
        // ...
        return null;
    }

    private static void aufgabe_b() {
        // Schreiben Sie eine statische Methode removeDuplicates(l),
        // die eine Liste zurückliefert mit allen Elementen
        // aus der Liste l ohne Duplikate.
        // Die Liste l bleibt unverändert.

        List<String> lStr = new LinkedList<>();
        lStr.add("vier");
        lStr.add("fuenf");
        lStr.add("vier");
        lStr.add("eins");
        lStr.add("fuenf");
        lStr.add("acht");
        lStr.add("vier");

        System.out.println(removeDuplicates(lStr));
    }

    private static <T> List<T> removeDuplicates(List<T> l) {
        // Ihr Code:
        // ...
        return null;
    }

    private static void aufgabe_c() {
        // Flexibilisieren Sie die Schnittstelle der statischen Methode append mit Hilfe des PECS-Prinzips so,
        // dass folgender Aufruf von append möglich ist.

        List<Number> nbList = new ArrayList<>();
        nbList.add(4);
        nbList.add(5);
        nbList.add(1.1);
        nbList.add(1);
        nbList.add(7);


        List<Object> objList = new LinkedList<>();
        objList.add("Test");
        objList.add(34.56);
        objList.add(1.1);
        objList.add(List.of(1,2,3));
        objList.add(4);
        objList.add(4);

        // Dieser Aufruf sollte gehen:
        //List<Object> l = append(nbList, objList);
        //System.out.println(nbList);
        //System.out.println(objList);
        //System.out.println(l);

    }

    private static void aufgabe_d() {
        // Schreiben Sie eine statische Methode findCommon(a,b), die alle gemeinsamen Elemente
        // der Collections a und b als Set zurueckgibt.

        List<Number> lNb = new LinkedList<>();
        lNb.add(4);
        lNb.add(1);
        lNb.add(500);
        lNb.add(1.2);
        lNb.add(500);
        lNb.add(1);
        lNb.add(8.5);

        Collection<Integer> colInt = new ArrayList<>();
        colInt.add(4);
        colInt.add(500);
        colInt.add(1);
        colInt.add(1);
        colInt.add(7);

        Set<Number> s = findCommon(lNb, colInt);
        System.out.println(s);
    }

    private static <T> Set<T> findCommon(Collection<? extends T> a, Collection<? extends T> b) {
        // Ihr Code:
        // ...
        return null;
    }


}
