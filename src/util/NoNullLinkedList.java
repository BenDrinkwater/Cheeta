package util;

import java.util.Collection;
import java.util.LinkedList;

public class NoNullLinkedList<T> extends LinkedList<T>{

	private static final long serialVersionUID = -564569799569065057L;
	
	public NoNullLinkedList() {
		super();
	}
	
	public NoNullLinkedList(Collection<T> collection) {
		super(collection);
	}
	
	@Override
	public boolean add(T element) {
		if (!(null == element)) {
			return super.add(element);
		}
		return false;
	}
	
	@Override
	public boolean addAll(Collection<? extends T> collection) {
		if (null == collection) {
			return false;
		}
		for (T item : collection) {
			this.add(item);
		}
		return true;
	}
	
	@Override
	public void addFirst(T element) {
		if (!(null == element)) {
			super.addFirst(element);
		}	
	}
	
	@Override
	public void addLast(T element) {
		if (!(null == element)) {
			super.addLast(element);
		}	
	}
}
