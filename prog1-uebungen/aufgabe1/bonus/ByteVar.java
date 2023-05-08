// ByteVar.java

package aufgabe1.bonus;

import java.util.Scanner;

/**
 * IntVar zeigt den Umgang mit Variablen vom Typ int.
 * &Uuml;bungsaufgabe 1 zur Programmiertechnik 1.
 * @author Dominik Nowara
 * @version 04.04.2023
 */
public final class ByteVar {
    private ByteVar() { }

    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        //Definieren der min und max
        final byte min = -128;
        final byte max = 127;

        //Eingabe von zwei Zahlen
        System.out.printf("Zwei ganze Zahlen zwischen %d und %d eingeben:%n",
            min, max);
        byte x = INPUT.nextByte();
        byte y = INPUT.nextByte();

        //Ausgabe der zwei Zahlen als Oktal, Dezimal und Hex
        System.out.printf("%o ist oktal %d und hexadezimal %x%n", x, x, x);
        System.out.printf("%o ist oktal %d und hexadezimal %x%n", y, y, y);

        //Ausgabe der verschiedenen Rechenoperationen
        System.out.printf("%d + %d ist %d%n", x, y, x + y);
        System.out.printf("%d - %d ist %d%n", x, y, x - y);
        System.out.printf("%d * %d ist %d%n", x, y, x * y);
        System.out.printf("%d / %d ist %d%n", x, y, x / y);
        System.out.printf("%d %% %d ist %d%n", x, y, x % y);

        //Ausgabe der verschiedenen Vergleichsoperationen
        System.out.printf("%d == %d ist %b%n", x, y, x == y);
        System.out.printf("%d != %d ist %b%n", x, y, x != y);
        System.out.printf("%d < %d ist %b%n", x, y, x < y);
        System.out.printf("%d <= %d ist %b%n", x, y, x <= y);
        System.out.printf("%d > %d ist %b%n", x, y, x > y);
        System.out.printf("%d >= %d ist %b%n", x, y, x >= y);
    }
}

