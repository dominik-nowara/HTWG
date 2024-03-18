package aufgabe6;

/**
 * Klasse für alle unbenotete Noten (Scheine).
 */
public class UnbenoteteLeistung extends Leistung {
    private final boolean bestanden;

    /**
     * Erstelle eine unbenotete Leistung.
     * @param fach für welches die Leistung sein soll.
     * @param bestanden ob die Leistung bestanden ist.
     */
    public UnbenoteteLeistung(String fach, boolean bestanden) {
        super(fach);
        this.bestanden = bestanden;
    }

    /**
     * liefert die Note des Fachs in Worten.
     * <p>
     * Wenn {@link #istBenotet} <code>true</code> zur&uuml;ckgibt,
     * muss die Methode in der Unterklasse &uuml;berschrieben werden.
     * Der geliefert String h&auml;ngt dann davon ab,
     * welches Notensystem die Unterklasse verwendet.
     * @return "bestanden", wenn {@link #istBestanden} true liefert,
     *         sonst "nicht bestanden".
     */
    @Override
    public String getNoteInWorten() {
        if (istBestanden()) {
            return "bestanden";
        }

        return "nicht bestanden";
    }

    /**
     * fragt ab, ob das Fach bestanden ist.
     * Wenn {@link #istBenotet} <code>true</code> zur&uuml;ckgibt,
     * h&auml;ngt es vom Notensystem der Unterklasse ab,
     * welche Noten als bestanden und welche als nicht bestanden gelten.
     * @return <code>true</code>, wenn bestanden, sonst <code>false</code>
     */
    @Override
    public boolean istBestanden() {
        return bestanden;
    }

    /**
     * fragt ab, ob das Fach benotet oder unbenotet ist.
     * <p>
     * Darf nur <code>true</code> liefern, wenn die Methoden
     * {@link #getNote} und {@link #getNoteInWorten}
     * so &uuml;berschreiben sind, dass sie eine Note liefern.</p>
     * @return <code>true</code>, wenn benotet, sonst <code>false</code>
     */
    @Override
    public boolean istBenotet() {
        return false;
    }
}
