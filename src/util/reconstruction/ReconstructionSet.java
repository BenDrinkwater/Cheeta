package util.reconstruction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import reconstruction.LooseParasiteMapping;

import tree.Node;

public class ReconstructionSet implements Set<LooseParasiteMapping> {

	protected Map<Node, LooseParasiteMapping> container;
	protected Map<Integer, Node> tracker;
	
	public ReconstructionSet() {
		this.container = new HashMap<Node, LooseParasiteMapping>();
		this.tracker = new HashMap<Integer, Node>();
	}
	
	public boolean add(LooseParasiteMapping looseParasiteMapping) {
		if (this.tracker.containsKey(looseParasiteMapping.hashCode())) {
			if (looseParasiteMapping.getCost() < this.container.get(this.tracker.get(looseParasiteMapping.hashCode())).getCost()) {
				this.container.put(this.tracker.get(looseParasiteMapping.hashCode()), looseParasiteMapping);
				return true;
			}
		}
		else {
			Node node = looseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
			this.tracker.put(looseParasiteMapping.hashCode(), node);
			this.container.put(node, looseParasiteMapping);
			return true;
		}
		return false;
	}

	public boolean addAll(Collection<? extends LooseParasiteMapping> c) {
		boolean allAdded = true;
		if (null == c) {
			return false;
		}
		for (LooseParasiteMapping looseParasiteMapping : c) {
			if (!(this.add(looseParasiteMapping))) {
				allAdded = false;
			}
		}
		return allAdded;
	}

	@Override
	public void clear() {
		this.container.clear();
		this.tracker.clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.container.containsValue(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!(this.container.containsValue(o))) {
				return false;
			}
		}		
		return true;
	}

	public boolean isEmpty() {
		return this.container.isEmpty();
	}

	public Iterator<LooseParasiteMapping> iterator() {
		return this.container.values().iterator();
	}

	public boolean remove(Object o) {
		if (this.container.containsValue(o)) {
			this.container.remove(this.tracker.get(o.hashCode()));
			this.tracker.remove(o.hashCode());
			return true;
		}
		return false;
	}

	public int size() {
		return this.container.size();
	}

	public Object[] toArray() {
		return this.container.values().toArray();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		int removalCounter = 0;
		for (Object item : c) {
			removalCounter += this.remove(item) ? 1 : 0;
		}
		return c.size() == removalCounter;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Object[] array = this.toArray();
		for (Object item : array) {
			if (!c.contains(item)) {
				this.remove(item);
			}
		}
		return true;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}
	
	public String toString() {
		return this.container.toString();
	}
}