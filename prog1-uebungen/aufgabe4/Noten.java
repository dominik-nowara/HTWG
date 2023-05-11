package aufgabe4;

/**
 * Utility class that holds all methods needed for grades.
 */
public final class Noten {
    private Noten() { }

    /**
     * Best valid grade.
     */
    public static final double BESTE = 1.0;

    /**
     * Worst valid grade.
     */
    public static final double SCHLECHTESTE = 5.0;

    /**
     * Checks if given grade is valid or not.
     * @param grade to check on.
     * @return true if grade is valid, false if not.
     */
    public static boolean istZulaessig(String grade) {
        //Überprüfe, ob es eine richtige Note ist
        if (grade.length() != 3
                || !Character.isDigit(grade.charAt(0))
                || (grade.charAt(1) != ',' && grade.charAt(1) != '.')
                || !Character.isDigit(grade.charAt(2))) {
            return false;
        }

        final int beforeComma =
                Character.getNumericValue(grade.charAt(0));
        final int afterComma =
                Character.getNumericValue(grade.charAt(2));

        if (beforeComma < 1
                || beforeComma > 5) {
            return false;
        } else if (((beforeComma == 4
                || beforeComma == 5)
                && afterComma != 0)
                || (afterComma != 0
                && afterComma != 3
                && afterComma != 7)) {
            return false;
        }

        return true;
    }

    /**
     * Transforms grade into double.
     * @param grade to transform.
     * @return transformed grade.
     */
    public static double toDouble(String grade) {
        try {
            grade = grade.replace(',', '.');
            return Double.parseDouble(grade);
        } catch (Exception e) {
            throw new IllegalArgumentException("Diese Note kann nicht zu "
                    + "einem Double umgewandelt werden: " + grade);
        }
    }

    /**
     * Gives grade as String back. If given grade is
     * not in between 1.0 and 5.0 throw an error.
     * @param grade to transform.
     * @return grade as string.
     */
    public static String toString(double grade) {
        if (grade < BESTE || grade > SCHLECHTESTE) {
            throw new IllegalArgumentException(String.valueOf(grade));
        }

        return String.format("%.01f", grade);
    }

    /**
     * Checks if grade passed.
     * @param grade to check.
     * @return false if grade is taller than 5, else true.
     */
    public static boolean istBestanden(double grade) {
        return (grade <= 4);
    }

    /**
     * Checks which of the two provided grades is better.
     * @param grade1 first grade to check.
     * @param grade2 second grade to check.
     * @return better grade.
     */
    public static double bessere(double grade1, double grade2) {
        return (grade1 < grade2) ? grade1 : grade2;
    }

    /**
     * Checks which of the two provided grades is worse.
     * @param grade1 first grade to check.
     * @param grade2 second grade to check.
     * @return worse grade.
     */
    public static double schlechtere(double grade1, double grade2) {
        return (grade1 > grade2) ? grade1 : grade2;
    }
}
