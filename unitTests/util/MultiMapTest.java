package util;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class MultiMapTest {

	@Test
	public void AddUniqueEntriesTest() {
		MultiMap<String, Integer> uniqueEntries = new LinkedHashMultiMap<String, Integer>();
		uniqueEntries.add("One", 1);
		uniqueEntries.add("Two", 2);
		uniqueEntries.add("Three", 3);
		assertEquals(3, uniqueEntries.size());
	}
	
	@Test
	public void AddTuplesTest() {
		MultiMap<String, Integer> tuples = new LinkedHashMultiMap<String, Integer>();
		tuples.add("Ones", 1);
		tuples.add("Ones", 2);
		tuples.add("Ones", 3);
		tuples.add("Ones", 7);
		tuples.add("Tens", 11);
		tuples.add("Tens", 13);
		tuples.add("Tens", 17);
		tuples.add("Tens", 23);
		tuples.add("Hundreds", 101);
		assertEquals(3, tuples.size());
	}	
	
	@Test
	public void GetTest1() {
		MultiMap<String, Integer> tuples = new LinkedHashMultiMap<String, Integer>();
		tuples.add("Ones", 2);
		tuples.add("Ones", 3);
		tuples.add("Ones", 5);
		tuples.add("Ones", 7);
		tuples.add("Tens", 11);
		tuples.add("Tens", 13);
		tuples.add("Tens", 17);
		tuples.add("Hundreds", 101);
		
		Set<Integer> items = tuples.get("Ones");
		assertEquals(4, items.size());
		items = tuples.get("Tens");
		assertEquals(3, items.size());
		items = tuples.get("Hundreds");
		assertEquals(1, items.size());
		
		items = tuples.get("Ones");
		assertEquals((Integer) 2, (Integer) items.iterator().next());
	}	
	
	@Test
	public void GetTest2() {
		MultiMap<String, Integer> tuples = new LinkedHashMultiMap<String, Integer>();
		tuples.add("Ones", 1);
		tuples.add("Ones", 1);
		tuples.add("Ones", 2);
		tuples.add("Ones", 2);
		tuples.add("Tens", 10);
		tuples.add("Tens", 10);
		tuples.add("Tens", 15);
		tuples.add("Tens", 20);
		tuples.add("Hundreds", 101);
		assertEquals(3, tuples.size());	
		
		Set<Integer> set = tuples.get("Ones");
		assertNotNull(set);
		assertEquals(2, set.size());
	}
}
