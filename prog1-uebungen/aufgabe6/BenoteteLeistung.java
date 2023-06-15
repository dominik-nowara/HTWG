package aufgabe6;

import aufgabe5.Note;

/**
 * Klasse für alle Benotete Noten (Leistungsnachweise).
 */
public class BenoteteLeistung extends Leistung {
    private final Note note;

    /**
     * Erstelle eine neue benotetete Leistung.
     * @param fach in welcher die benotete Leistung sein soll
     * @param note welche eingesetzt werden soll
     */
    public BenoteteLeistung(String fach, String note) {
        super(fach);
        this.note = Note.valueOf(note);
    }

    /**
     * liefert die Note des Fachs in numerischer Schreibweise.
     * <p>
     * Wenn {@link #istBenotet} <code>true</code> zur&uuml;ckgibt,
     * muss die Methode in der Unterklasse so &uuml;berschrieben werden,
     * dass sie die Note in numerischer Schreibweise liefert,
     * also Strings "1,0", "1,3" usw.
     * @return leerer String &quot;&quot;
     */
    public String getNote() {
        String back = "";
        if (note.intValue() < 16) {
            back = "sehr gut";
        } else if (note.intValue() < 26) {
            back = "gut";
        } else if (note.intValue() < 36) {
            back = "befriedigend";
        } else if (note.intValue() < 41) {
            back = "ausreichend";
        } else {
            back = "nicht ausreichend";
        }

        back += " (" + note.toString() + ")";
        return back;
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
        return note.istBestanden();
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
        return true;
    }
}
