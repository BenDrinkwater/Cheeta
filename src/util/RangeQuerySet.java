package util;

import reconstruction.LooseParasiteMapping;
import util.rmq.RangeMinimumQuery;
import util.rmq.RangeMinimumQuerySparseTable;

public class RangeQuerySet {

	private RangeQuerySetValue[] rangeQuerySet;
	private RangeMinimumQuery querySheet;
	
	public RangeQuerySet(int size) {
		this.rangeQuerySet = new RangeQuerySetValue[size];
	}
	
	public void add(int index, LooseParasiteMapping first) {
		this.rangeQuerySet[index] = new RangeQuerySetValue(first);
	}
	
	// brute force search (will cache later)
	public LooseParasiteMapping getMin(int startIndex, int endIndex) {
		int index = this.querySheet.query(startIndex, endIndex);
		return null == this.rangeQuerySet[index] ? null : this.rangeQuerySet[index].getFirstMap();
	}
	
	public void buildQueryArray() {
		int[] array = new int[this.rangeQuerySet.length];
		for (int i = 0; i < this.rangeQuerySet.length; ++i) {
			if (null != this.rangeQuerySet[i]) {
				array[i] = this.rangeQuerySet[i].getCost();
			}
			else {
				array[i] = Integer.MAX_VALUE;
			}
 		}
		this.querySheet = new RangeMinimumQuerySparseTable(array);
	}
	
	class RangeQuerySetValue {
	
		private LooseParasiteMapping first;
		
		public RangeQuerySetValue(LooseParasiteMapping best) {
			this.first = best;
		}
		
		public int getCost() {
			return this.first.getCost();
		}
		
		public LooseParasiteMapping getFirstMap() {
			return this.first;
		}
		
		public String toString() {				
			return first.toString();
		}
	}
	
}
