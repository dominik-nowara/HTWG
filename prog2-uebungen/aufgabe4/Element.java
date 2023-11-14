package aufgabe4;

/**
 * Klasse für Wörter mit ihren Häufigkeiten.
 * @author oliverbittel
 * @since 31.07.2023
 */
public class Element<T> {
	final private T data;
	private int freqency;
	
	/**
	 * Konstruktor.
	 * @param data Data
	 * @param f H&auml;ufgkeit
	 */
	public Element(T data, int f) {
		this.data = data;
		this.freqency = f;
	}

	/**
	 * Liefert Wort zur&uuml;ck.
	 * @return Wort
	 */
	public T getData() {
		return data;
	}

	/**
	 * Liefert H&auml;ufgkeit zur&uuml;ck.
	 * @return H&auml;ufgkeit
	 */
	public int getFrequency() {
		return freqency;
	}
	
	/**
	 * Addiert zur H&auml;ufgkeit f dazu.
	 * @param f H&auml;ufgkeits&auml;nderung.
	 */
	public void addFrequency(int f) {
		freqency += f;
	}

	@Override
	public String toString() {
		return data + ":" + freqency;
	}
}
