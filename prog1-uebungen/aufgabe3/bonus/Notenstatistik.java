// Notenstatistik.java
package aufgabe3.bonus;

import java.util.Locale;
import java.util.Scanner;

/**
 * erstellt eine Notenstatistik.
 * <p>
 * Das Programm erwartet Pr&uuml;fungsnoten im Format
 * <code>Ganze,Zehntel</code> oder <code>Ganze.Zehntel</code>,
 * wobei <code>Ganze</code> und <code>Zehntel</code> nur aus
 * je einer Dezimalziffer bestehen d&uuml;rfen.
 * Andere Eingaben werden wegen Formatfehler ignoriert.
 * </p>
 * <p>
 * Das Programm gibt die folgende Statistik aus:
 * </p>
 * <ul>
 * <li>die Anzahl der ber&uuml;cksichtigten Noten</li>
 * <li>die Anzahl der Bestandenen</li>
 * <li>die beste Note</li>
 * <li>die schlechteste Note</li>
 * <li>den Durchschnitt der Bestandenen</li>
 * <li>die Durchfallquote in Prozent</li>
 * </ul>
 * <p>
 * Es werden in der Statistik nur die nach HTWG-Pr&uuml;fungsordnung
 * zul&auml;ssigen Noten (1,0 1,3 1,7 2,0 2,3 2,7 3,0 3,3 3,7 4,0 5,0)
 * ber&uuml;cksichtigt.
 * Andere Eingaben werden wegen falscher Vorkommastelle oder
 * falscher Nachkommastelle ignoriert.
 * Alle Noten bis 4,0 gelten als bestanden, nur die 5,0 als durchgefallen.
 * </p>
 *
 * @author Dominik Nowara
 * @version 25.04.2023
 */
public final class Notenstatistik {
    private Notenstatistik() { }

    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMANY);

        //Definieren der Werte, welche wir zur Ausgabe benötigen
        double worstGrade = 1.0;
        double bestGrade = 5.0;
        int validGrades = 0;
        int passedGrades = 0;
        double averageGrade = 0.0;

        //--------------------------------------------------- Noten einlesen
        System.out.println("Noten im Format Ganze,Zehntel "
                           + "oder Ganze.Zehntel eingeben (Ende mit Strg-D):");


        while (INPUT.hasNext()) {
            String grade = INPUT.next();

            //---------------------------------------------- Eingabe pruefen

            int beforeComma = 0;
            int afterComma = 0;

            try {
                if (grade.length() > 3) {
                    throw new java.util.InputMismatchException(grade
                        + " wird wegen Formatfehler");
                }

                if (grade.length() < 3) {
                    switch(grade) {
                    case "1":
                        grade = "1.0";
                        break;
                    case "1-":
                        grade = "1.3";
                        break;
                    case "2+":
                        grade = "1.7";
                        break;
                    case "2":
                        grade = "2.0";
                        break;
                    case "2-":
                        grade = "2.3";
                        break;
                    case "3+":
                        grade = "2.7";
                        break;
                    case "3":
                        grade = "3.0";
                        break;
                    case "3-":
                        grade = "3.3";
                        break;
                    case "4":
                        grade = "4.0";
                        break;
                    case "5":
                        grade = "5.0";
                        break;
                    default:
                        break;
                    }
                }
                else if (!Character.isDigit(grade.charAt(0))
                    || (grade.charAt(1) != ',' && grade.charAt(1) != '.')
                    || !Character.isDigit(grade.charAt(2))) {
                    throw new java.util.InputMismatchException(grade
                        + " wird wegen Formatfehler");
                }

                beforeComma =
                    Character.getNumericValue(grade.charAt(0));
                afterComma =
                    Character.getNumericValue(grade.charAt(2));

                if (beforeComma < 1
                    || beforeComma > 5) {
                    throw new java.util.InputMismatchException(grade
                        + " wird wegen Vorkommastelle " + beforeComma);
                } else if (((beforeComma == 4
                        || beforeComma == 5)
                        && afterComma != 0)
                        || (afterComma != 0
                        && afterComma != 3
                        && afterComma != 7)) {
                    throw new java.util.InputMismatchException(grade
                        + " wird wegen Nachkommastelle " + beforeComma);
                }
            } catch (Exception e) {
                System.out.println("Note " + e + " ignoriert!");
                continue;
            }

            //Alle wichtigen Daten erfassen

            final double transformed = beforeComma
                    + (double) afterComma / 10.0;

            if (transformed > worstGrade) {
                worstGrade = transformed;
            }
            if (transformed < bestGrade) {
                bestGrade = transformed;
            }

            validGrades++;

            if (beforeComma < 5) {
                passedGrades++;
                averageGrade += transformed;
            }
        } // while

        //------------------------------------------ Notenstatistik ausgeben

        final double failureRate =
                ((double) (validGrades - passedGrades)
                        / (double) validGrades) * 100.0;

        averageGrade =
                averageGrade / (double) passedGrades;

        System.out.println("\nAnzahl beruecksichtigter Noten: "
                + validGrades);
        System.out.println("Anzahl Bestandene: " + passedGrades);

        if (validGrades > 0) {
            System.out.println("Beste Note: " + bestGrade);
            System.out.println("Schlechteste Note: " + worstGrade);
            System.out.println("Durchschnitt Bestandene: "
                    + String.format("%.1f", averageGrade));
            System.out.println("Durchfallquote: "
                    + String.format("%.1f", failureRate) + "%");
        }
    } // main
}

