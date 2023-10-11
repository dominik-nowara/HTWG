package aufgabe1;

/**
 *
 * @author oliverbittel
 */
public abstract class AbstractFrequencyTable implements FrequencyTable {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
    public void add(String w) {
		add(w, 1);
    }

	@Override
	public void addAll(FrequencyTable fq) {
		for (int i = 0; i < fq.size(); i++) {
			Word gotWord = fq.get(i);
			this.add(gotWord.getWord(), gotWord.getFrequency());
		}
	}

	@Override
	public void collectNMostFrequent(int n, FrequencyTable fq) {
		fq.clear();
		n = Math.min(n, size());

		for (int i = 0; i < n; i++) {
			Word gotWord = get(i);
			fq.add(gotWord.getWord(), gotWord.getFrequency());
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");

		for (int i = 0; i < size(); i++) {
			Word gotWord = get(i);
			s.append(gotWord.getWord())
					.append(":")
					.append(gotWord.getFrequency());

			if (i < size() - 1) {
				s.append(", ");
			}
		}
		s.append("}");

		return s.toString();
	}
}
