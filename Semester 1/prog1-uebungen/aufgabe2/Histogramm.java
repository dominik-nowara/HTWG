// Histogramm.java
package aufgabe2;

import java.util.Scanner;

/**
 * Histogramm liest ganze Zahlen zwischen 1 und 6 ein und
 * gibt deren H&auml;ufigkeitsverteilung als Histogramm aus.
 * @author Dominik Nowara
 * @version 11.04.2023
 */
public final class Histogramm {
    private Histogramm() { }

    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        final int min = 1;
        final int max = 6;
        final int moduloReplace = 5;

        int[] histogramm = {0, 0, 0, 0, 0, 0};

        //---------------------------------------------------- Zahlen einlesen
        System.out.println("Ganze Zahlen zwischen 1 und 6 eingeben "
                    + "(Ende mit Strg-D/Strg-Z):");

        while (INPUT.hasNextInt()) {
            int number = INPUT.nextInt();

            if (number >= min && number <= max) {
                ++histogramm[number - 1];
            } else {
                System.out.println("Eingabe wird ignoiert: " + number);
            }
        }

        //------------------------------------------------ Histogramm ausgeben
        System.out.println("Histogramm:");
        for (int i = 0; i < histogramm.length; i++) {
            for (int j = 1; j < histogramm[i] + 1; j++) {
                if (j % moduloReplace == 0) {
                    System.out.printf("$");
                } else {
                    System.out.printf("%d", i + 1);
                }
            }
            System.out.printf(" (%d)%n", histogramm[i]);
        }
    }
}

