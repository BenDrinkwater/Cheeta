package util.rmq;

import static org.junit.Assert.*;

import org.junit.Test;

public class RangeMinimumQueryTests {

	@Test
	public void test1() {
		int[] array = {8,7,3,20,2,17,5,21,11,12};
		RangeMinimumQuery rangeMinimumQuery = new RangeMinimumQuerySparseTable(array);
		assertEquals(2, rangeMinimumQuery.query(1, 3));
		assertEquals(2, rangeMinimumQuery.query(0, 3));
		assertEquals(4, rangeMinimumQuery.query(1, 4));
		assertEquals(4, rangeMinimumQuery.query(0, 4));
		assertEquals(6, rangeMinimumQuery.query(5, 8));
	}
	
}
