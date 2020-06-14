package util;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class LinkedHashMultiMap<T,U> extends LinkedHashMap<T, Set<U>> implements MultiMap<T, U>{
	
	public LinkedHashMultiMap() {
		super();
	}
	
	@Override
	public boolean add(T key, U value) {
		if (this.containsKey(key)) {
			Set<U> set = this.get(key);
			if (set.contains(value)) {
				return false;
			}
			set.add(value);
			super.put(key, set);
		}
		else {
			Set<U> set = new LinkedHashSet<U>();
			set.add(value);
			super.put(key, set);
		}
		return true;
	}
}
