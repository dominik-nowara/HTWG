package einstieg;

import java.util.Scanner;

/**
 * Einsteig ist ein kleines Java Programm.
 * Es verwendet die Klassen Scanner und System user der Java-Bibliothek.
 * @author Dominik Nowara
 * @version 21.03.2023
 */
public final class Einstieg {
    private Einstieg() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * Die Klassenmethode main ist der Startpunkt des Programms.
     * main verwendet die Methoden print und printf zum Ausgeben von Text
     * sowie die Methoden next und nextInt zum Einlesen von Text
     * und einer ganzen Zahl.
     * @param args wird nicht verwendet
     */
    public static void main(String[] args) {
        System.out.print("Vorname: ");
        String vorname = EINGABE.next();

        System.out.print("Anzahl bisher geschriebener Java-Programme: ");
        int anzahl = EINGABE.nextInt();

        System.out.printf("%ss %d. Java-Programm funktioniert!%n",
                vorname, anzahl + 1);
    }
}