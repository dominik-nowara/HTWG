// IntVar.java

package aufgabe1;

import java.util.Scanner;

/**
 * IntVar zeigt den Umgang mit Variablen vom Typ int.
 * &Uuml;bungsaufgabe 1 zur Programmiertechnik 1.
 * @author Dominik Nowara
 * @version 28.03.2023
 */
public final class IntVar {
    private IntVar() { }

    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        //Definieren von min und max
        final int min = Integer.MIN_VALUE;
        final int max = Integer.MAX_VALUE;

        //Definieren der beiden Rechenvariablen
        long x = 0;
        long y = 0;

        //Solange Eingabe von zwei Zahlen, bis korrekte Zahl eingegeben wird
        while (true) {
            System.out.printf("Zwei ganze Zahlen zwischen "
                + "%d und %d eingeben:%n", min, max);

            //Erste Zahl nach Zahl überprüfen, wenn keine Zahl, wiederhole
            if (!INPUT.hasNextInt()) {
                System.out.printf("Keine Zahl eingegeben!%n");
                INPUT.next();
                continue;
            }
            x = INPUT.nextInt();

            //Zweite Zahl nach Zahl überprüfen, wenn keine Zahl, wiederhole
            if (!INPUT.hasNextInt()) {
                System.out.printf("Keine Zahl eingegeben!%n");
                INPUT.next();
                continue;
            }
            y = INPUT.nextInt();

            break;
        }

        //Eingabe des Rechenoperators
        System.out.printf("Bitte Rechenoperator eingeben:%n");
        String operator = INPUT.next();

        //Ausgabe der zwei Zahlen als Oktal, Dezimal und Hex
        System.out.printf("%o ist oktal %d und hexadezimal %x%n", x, x, x);
        System.out.printf("%o ist oktal %d und hexadezimal %x%n", y, y, y);

        /* Berechnung des Ergebnis bei Rechenoperator,
        *  sonst Ausgabe des Vergleichwertes und verlasse das Programm
        */
        long result = 0;
        switch (operator) {
        case "+":
            result = x + y;
            break;
        case "-":
            result = x - y;
            break;
        case "*":
            result = x * y;
            break;
        case "/":
            result = x / y;
            break;
        case "%":
            result = x % y;
            break;
        case "==":
            System.out.printf("%d == %d ist %b%n", x, y, x == y);
            System.exit(1);
        case "!=":
            System.out.printf("%d != %d ist %b%n", x, y, x != y);
            System.exit(1);
        case "<":
            System.out.printf("%d < %d ist %b%n", x, y, x < y);
            System.exit(1);
        case "<=":
            System.out.printf("%d <= %d ist %b%n", x, y, x <= y);
            System.exit(1);
        case ">":
            System.out.printf("%d > %d ist %b%n", x, y, x > y);
            System.exit(1);
        case ">=":
            System.out.printf("%d >= %d ist %b%n", x, y, x >= y);
            System.exit(1);
        default:
            System.out.println("Keinen gültigen Rechenoperator eingegeben!");
            System.exit(1);
        }

        //Gib an, ob das Ergebnis als 8bit Integer darstellbar ist oder nicht
        if (result > min && result < max) {
            System.out.println("Das Ergebenis ist als Integer darstellbar");
        } else {
            System.out.println("Das Ergebenis ist NICHT als Integer "
                + "darstellbar");
        }
    }
}
