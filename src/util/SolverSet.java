package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class SolverSet<T extends SolutionComparable> implements SolutionSet<T> {

	private HashMap<Integer, T> set;
	private int minimumCostHistoryKey;
	private int maximumNumberOfCodivergenceEvents;
	
	public SolverSet() {
		this.set = new HashMap<Integer, T>();
		this.minimumCostHistoryKey = Integer.MAX_VALUE;
		this.maximumNumberOfCodivergenceEvents = Integer.MIN_VALUE;
	}
	
	@Override
	public Iterator<T> iterator() {
		return this.set.values().iterator();
	}

	@Override
	public boolean add(T item) {
		int last = this.minimumCostHistoryKey;
		this.minimumCostHistoryKey = Math.min(this.minimumCostHistoryKey, item.getCost());
		if (last > this.minimumCostHistoryKey) {
			this.maximumNumberOfCodivergenceEvents = item.getNumberOfCodivergenceEvents();
			this.set.clear();
			this.set.put(item.hashCode(), item);
			return true;
		}
		else if (item.getCost() == this.minimumCostHistoryKey) {
			if (item.getNumberOfCodivergenceEvents() > this.maximumNumberOfCodivergenceEvents) {
				this.maximumNumberOfCodivergenceEvents = item.getNumberOfCodivergenceEvents();
				this.set.clear();
				this.set.put(item.hashCode(), item);
				return true;
			}
			return false;
		}
		else {
			return false;
		}
		
	}

	@Override
	public void add(SolutionSet<T> set) {
		for (T item : set) {
			this.add(item);
		}
	}
	
	@Override
	public void add(Collection<T> collection) {
		for (T item : collection) {
			this.add(item);
		}		
	}

	@Override
	public T getFirstElement() {
		return this.iterator().next();
	}
	
	@Override
	public T getSecondElement() {
		if (this.size() < 2) {
			return this.getFirstElement();
		}		
		Iterator<T> iterator = this.iterator();
		iterator.next();
		return iterator.next();
	}

	@Override
	public int size() {
		return this.set.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SolutionSet<T> cloneSolutionSet() {
		SolutionSet<T> set = new SolverSet<T>();
		for (T solution : this) {
			set.add((T) solution.getClonedSolution());
		}
		return set;
	}
}
