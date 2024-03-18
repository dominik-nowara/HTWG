// Wuerfel.java
package aufgabe2;
import java.util.Random;

/**
 * Wuerfel gibt Zufallszahlen zwischen 1 und 6 aus.
 * @author H.Drachenfels
 * @version 18.12.2018
 */
public final class Wuerfel {
    private Wuerfel() { }

    private static final int ANZAHL_AUGEN = 6;

    /**
     * main ist der Startpunkt des Programms.
     * @param args Anzahl der Zufallszahlen (Default 1)
     */
    public static void main(String[] args) {
        int versuche = 1;

        if (args.length > 0) {
            try {
                versuche = Integer.parseInt(args[0]);
            } catch (NumberFormatException x) {
                versuche = 0;
            }

            if (versuche <= 0 || args.length > 1) {
                System.err.printf("Zu viele oder falsche Parameter!%n"
                                  + "Aufruf: java Wuerfel [Anzahl]%n");
                return;
            }
        }

        Random wuerfel = new Random();
        for (int i = 0; i < versuche; ++i) {
            System.out.println(wuerfel.nextInt(ANZAHL_AUGEN) + 1);
        }
    }
}

