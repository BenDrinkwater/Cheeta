package util;


public class Pair<T, U> {

	private T first;
	private U second;
	
	public Pair (T first, U second) {
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() {
		return this.first;
	}
	
	public U getSecond() {
		return this.second;
	}
	
	public void setFirst(T value) {
		this.first = value;
	}
	
	public void setSecond(U value) {
		this.second = value;
	}
	
	public boolean equals(Pair<T,U> pair) {
		if (null != pair && null != this.getFirst() && null != this.getSecond()) {
			return this.getFirst().equals(pair.getFirst()) && this.getSecond().equals(pair.getSecond());
		}
		return false;		
	}
	
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	public String toString() {
		return this.first.toString() + " " +  this.second.toString();
	}
	
	public boolean contains(Object value) {
		return ((null != first && first.equals(value)) || (null != second && second.equals(value)));
	}
}
