package aufgabe5;

/**
 *  Class that holds note and all utility methods.
 */
public final class Note {
    private int note;

    /**
     * Best grade.
     */
    public static final Note BESTE = new Note(1);

    /**
     * Worst grade.
     */
    public static final Note SCHLECHTESTE = new Note(5);

    /**
     * Private constructor.
     * @param note to set
     */
    private Note(int note) {
        this.note = note;
    }

    /**
     * Create new Note object by int value.
     * @param note what note Note should have
     * @return newly created object
     */
    public static Note valueOf(int note) {
        switch (note) {
        case 10:
        case 13:
        case 17:
        case 20:
        case 23:
        case 27:
        case 30:
        case 33:
        case 37:
        case 40:
        case 50:
            return new Note(note);
        default:
            throw new  IllegalArgumentException("unzulaessige Note " + note);
        }
    }

    /**
     * Create new Note object by String value.
     * @param note what note Note should have
     * @return newly created object
     */
    public static Note valueOf(String note) {
        switch (note) {
        case "1,0":
        case "1,3":
        case "1,7":
        case "2,0":
        case "2,3":
        case "2,7":
        case "3,0":
        case "3,3":
        case "3,7":
        case "4,0":
        case "5,0":
            return new Note(Integer.parseInt(note.replace(",", "")));
        default:
            throw new  IllegalArgumentException("unzulaessige Note " + note);
        }
    }

    /**
     * Returns note as int.
     * @return note as int
     */
    public int intValue() {
        return this.note;
    }

    /**
     * Check if note passed.
     * @return true if passed, else false
     */
    public boolean istBestanden() {
        return this.note <= 40;
    }

    /**
     * Make note value to string.
     * @return string variant of note
     */
    @Override
    public String toString() {
        return this.note / 10 + "," + this.note % 10;
    }

    /**
     * Check if two Note objects are equal.
     * @param o second object to check
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Note) {
            Note that = (Note) o;
            return this.note == that.note;
        }
        return false;
    }

    /**
     * Create hashcode with note value.
     * @return note
     */
    @Override
    public int hashCode() {
        return this.note;
    }


}
