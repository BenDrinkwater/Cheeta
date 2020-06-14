package util.rmq;

public abstract class RangeMinimumQuery {
	
	protected int[] array;
	
	public RangeMinimumQuery(int[] array) {
		this.array = array;
	}
	
	public abstract int query(int i, int j);
}
