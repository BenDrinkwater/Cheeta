package util;

public class ComparablePair<T extends Comparable<T>, U extends Comparable<U>> extends Pair<T, U> implements Comparable<ComparablePair<T, U>> {

	public ComparablePair(T first, U second) {
		super(first, second);
	}

	@Override
	public int compareTo(ComparablePair<T, U> compareTo) {
		if (super.getFirst().equals(compareTo.getFirst())) {
			return super.getSecond().compareTo(compareTo.getSecond());
		}
		return super.getFirst().compareTo(compareTo.getFirst());
	}
	
	public String toString() {
		return super.toString();
	}
}
