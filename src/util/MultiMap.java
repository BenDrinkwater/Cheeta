package util;

import java.util.Map;
import java.util.Set;

public interface MultiMap<T,U> extends Map<T, Set<U>> {

	public boolean add(T key, U value);
	
	public void clear();
}
