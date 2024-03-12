// Bubblesort.java

import java.util.Scanner;
import java.util.Random;

/**
 * Bubblesort liest ganze Zahlen ein und sortiert sie aufsteigend.
 * @author H.Drachenfels
 * @version 8.11.2020
 */
public final class Bubblesort {
    private Bubblesort() { }

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Random random = new Random();

        //---------------------------------------------- Arraygroesse einlesen
        if (args.length != 1) {
            System.err.println("Aufruf: java Bubblesort Anzahl");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];

        //---------------------------------------------------- Zahlen einlesen
        System.out.printf("Bitte %d ganze Zahlen eingeben: ", n);
        int k = 0;
        for (int i = 0; i < a.length && stdin.hasNextInt(); ++i) {
            a[i] = stdin.nextInt();
            ++k;
        }

        for (int i = k; i < a.length; ++i) {
            a[i] = random.nextInt();
            System.out.println(a[i]);
        }

        //--------------------------------------------------- Zahlen sortieren
        for (int i = a.length; i > 1; --i) {
            // groessten Wert nach hinten schieben
            for (int j = 0; j < i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    // Werte tauschen
                    int tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                }
            }
        }

        //---------------------------------------------------- Zahlen ausgeben
        System.out.println("Sortierte Zahlenfolge: ");
        for (int m: a) {
            System.out.println(m);
        }
    }
}

