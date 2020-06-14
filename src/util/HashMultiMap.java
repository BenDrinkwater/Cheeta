package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class HashMultiMap<T,U> extends HashMap<T, Set<U>> implements MultiMap<T, U>{
	
	public HashMultiMap() {
		super();
	}
	
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
			Set<U> set = new HashSet<U>();
			set.add(value);
			super.put(key, set);
		}
		return true;
	}
}