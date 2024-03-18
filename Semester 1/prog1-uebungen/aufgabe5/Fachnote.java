package aufgabe5;

/**
 * More specific note.
 */
public class Fachnote {

    /**
     * Subject of this Fachnote.
     */
    public String fach;

    /**
     * Grade of this Fachnote.
     */
    public Note note;

    /**
     * Create new Fachnote object.
     * @param fach that this Fachnote belongs to
     * @param note of the fach
     */
    public Fachnote(String fach, Note note) {
        if (fach == null || note == null || note.intValue() == 0) {
            throw new IllegalArgumentException();
        }
        this.fach = fach;
        this.note = note;
    }
}
