package util.comparators;

import java.util.Comparator;

public class DecendingComparator<T extends Comparable<T>> implements Comparator<T> {

	@Override
	public int compare(T value1, T value2) {
		return value2.compareTo(value1);
	}

}
