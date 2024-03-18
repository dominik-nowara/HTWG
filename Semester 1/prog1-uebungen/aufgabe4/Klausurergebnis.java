// Klausurergebnis.java
package aufgabe4;
//import aufgabe4.Noten;
import java.util.Locale;
import java.util.Scanner;

/**
 * Klausurergebnis erstellt eine Notenstatistik.
 * <p>
 * Das Programm liest Noten als Strings ein und bestimmt die beste und
 * die schlechteste Note, den Durchschnitt der Bestandenen sowie
 * die Durchfallquote in Prozent.
 * Das Programm ber&uuml;cksichtigt dabei nur die laut Noten.istZulaessig
 * erlaubten Noten. Andere Noten werden unter Ausgabe einer Warnung ignoriert.
 * Welche Noten besser und schlechter sind, welche als bestanden oder
 * durchgefallen gelten und wie die String-Darstellung der Noten aussieht,
 * wird mit Methoden der Klasse Noten bestimmt.
 * </p>
 * Das Programm gibt als Klausurergebnis folgende Werte aus:
 * <ul>
 * <li>die Anzahl der ber&uuml;cksichtigten Noten</li>
 * <li>die Anzahl der Bestandenen</li>
 * <li>die beste Note</li>
 * <li>die schlechteste Note</li>
 * <li>den Durchschnitt der Bestandenen</li>
 * <li>die Durchfallquote</li>
 * </ul>
 *
 * @author Dominik Nowara
 * @version 09.05.2023
 */
public final class Klausurergebnis {
    private Klausurergebnis() { }

    private static final Scanner EINGABE = new Scanner(System.in);

    /**
     * main ist der Startpunkt des Programms.
     * @param args wird nicht verwendet.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMAN);

        //Definieren der Werte, welche wir zur Ausgabe benötigen
        double worstGrade = Noten.BESTE;
        double bestGrade = Noten.SCHLECHTESTE;
        int validGrades = 0;
        int passedGrades = 0;
        double averageGrade = 0.0;

        //--------------------------------------------------- Noten einlesen
        System.out.println("Noten im Format Ganze,Zehntel "
                           + "oder Ganze.Zehntel eingeben (Ende mit Strg-D):");

        while (EINGABE.hasNext()) {
            String grade = EINGABE.next();

            //---------------------------------------------- Eingabe pruefen

            if (!Noten.istZulaessig(grade)) {
                System.out.println("Unzulaessige Note " + grade
                        + " wird ignoriert!");
                continue;
            }

            //------------------------------------------------ Note erfassen

            try {
                var gradeDouble = Noten.toDouble(grade);

                bestGrade = Noten.bessere(gradeDouble, bestGrade);
                worstGrade = Noten.schlechtere(gradeDouble, worstGrade);

                validGrades++;

                if (Noten.istBestanden(gradeDouble)) {
                    passedGrades++;
                    averageGrade += gradeDouble;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
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
            try {
                System.out.println("Beste Note: "
                        + Noten.toString(bestGrade));
                System.out.println("Schlechteste Note: "
                        + Noten.toString(worstGrade));
                System.out.println("Durchschnitt Bestandene: "
                        + Noten.toString(averageGrade));
                System.out.println("Durchfallquote: "
                        + String.format("%.1f", failureRate) + "%");
            } catch (Exception ex) {
                System.out.println("Die Note " + ex.getMessage()
                        + " ist nicht anzeigbar!");
            }
        }
    } // main
}

