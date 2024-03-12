package aufgabe4;

/**
 *
 * @author oliverbittel
 */
public abstract class AbstractFrequencyTable<T> implements FrequencyTable<T> {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
    public void add(T w) {
		add(w, 1);
    }

	@Override
	public void addAll(FrequencyTable<? extends T> fq) {
		for (var x : fq) {
			this.add((T) x.getData(), x.getFrequency());
		}
	}

	@Override
	public void collectNMostFrequent(int n, FrequencyTable<? super T> fq) {
		fq.clear();
		n = Math.max(n, 0);
		int count = 0;
		for (var x : this) {
			fq.add(x.getData(), x.getFrequency());
			count++;
			if (count == n)
				return;
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");

		for (var x : this) {
			s.append(x.getData())
					.append(":")
					.append(x.getFrequency())
					.append(", ");
		}

		s.append("} size = ")
		.append(size());

		return s.toString();
	}
}
