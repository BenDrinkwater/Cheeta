package util;

import java.util.Collection;

public interface SolutionSet<T extends SolutionComparable> extends Iterable<T>{

	public boolean add(T item);
	
	public void add(SolutionSet<T> set);
	
	public void add(Collection<T> collection);
	
	public T getFirstElement();
	
	public T getSecondElement();
	
	public int size();
	
	public SolutionSet<T> cloneSolutionSet();
}
