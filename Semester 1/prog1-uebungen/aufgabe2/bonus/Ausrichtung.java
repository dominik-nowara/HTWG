// Histogramm.java
package aufgabe2.bonus;

import java.util.Scanner;

/**
 * Histogramm liest ganze Zahlen zwischen 1 und 6 ein und
 * gibt deren H&auml;ufigkeitsverteilung als Histogramm aus.
 * @author Dominik Nowara
 * @version 11.04.2023
 */
public final class Ausrichtung {
    private Ausrichtung() { }

    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        final int min = 1;
        final int max = 6;

        int[] histrogramm = {0, 0, 0, 0, 0, 0};

        //---------------------------------------------------- Zahlen einlesen
        System.out.println("Ganze Zahlen zwischen 1 und 6 eingeben "
                    + "(Ende mit Strg-D/Strg-Z):");

        while (INPUT.hasNextInt()) {
            int number = INPUT.nextInt();

            if (number >= min && number <= max) {
                ++histrogramm[number - 1];
            } else {
                System.out.println("Eingabe wird ignoiert: " + number);
            }
        }

        //---------------------- Bestimmen, wie viele ausgegeben werden müssen
        int maxLength = 0;
        for (int i = 0; i < histrogramm.length; i++) {
            if (maxLength < histrogramm[i]) {
                maxLength = histrogramm[i];
            }
        }

        //------------------------------------------------ Histogramm ausgeben
        System.out.println("Histogramm:");
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < histrogramm.length; j++) {
                if (histrogramm[j] > i) {
                    System.out.printf("%d ", j);
                } else {
                    System.out.printf("  ");
                }
            }
            System.out.printf(" (%d)%n", i + 1);
        }
    }
}

