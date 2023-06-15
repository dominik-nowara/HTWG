package aufgabe6;

/**
 * Klasse für alle Faecher.
 */
public final class Faecher {
    private Faecher() { }

    private static final String[] FAECHER = new String[] {
        "Mathematik 1",
        "Programmiertechnik 1",
        "Digitaltechnik",
        "Softwaremodellierung" };

    /**
     * Überprüfe, ob ein angegebenes Fach zulässig ist.
     * @param check Fach welches überprüft werden soll
     * @return ob das Fach zulässig ist
     */
    public static boolean istZulaessig(String check) {
        for (int i = 0; i < FAECHER.length; i++) {
            if (FAECHER[i].equals(check)) {
                return true;
            }
        }

        return false;
    }
}
