package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PairTest {

	@Test
	public void CreatePairTest() {
		Pair<String, String> pair1 = new Pair<String, String>("Word1", "Word2");
		Pair<String, Integer> pair2 = new Pair<String, Integer>("Word1", 1);
		assertNotNull(pair1);
		assertNotNull(pair2);
		assertEquals("Word1", pair1.getFirst());
		assertEquals("Word2", pair1.getSecond());
		assertEquals("Word1", pair2.getFirst());
		assertEquals((Integer) 1, pair2.getSecond());
	}
	
	@Test
	public void EqualityTest1() {
		Pair<String, String> pair1 = new Pair<String, String>("Word1", "Word2");
		Pair<String, String> pair2 = new Pair<String, String>("Word1", "Word2");
		Pair<String, String> pair3 = new Pair<String, String>("Word2", "Word1");
		assertEquals(true, pair1.equals(pair2));
		assertEquals(true, pair2.equals(pair1));
		assertEquals(false, pair1.equals(pair3));
		assertEquals(false, pair3.equals(pair1));
		assertEquals(false, pair2.equals(pair3));
		assertEquals(false, pair3.equals(pair2));
	}
	
	@Test
	public void EqualityTest2() {
		Pair<String, Integer> pair1 = new Pair<String, Integer>("Word1", 1);
		Pair<String, Integer> pair2 = new Pair<String, Integer>("Word1", 1);
		Pair<Integer, String> pair3 = new Pair<Integer, String>(1, "Word1");
		assertEquals(true, pair1.equals(pair2));
		assertEquals(true, pair2.equals(pair1));
		assertEquals(false, pair1.equals(pair3));
		assertEquals(false, pair3.equals(pair1));
		assertEquals(false, pair2.equals(pair3));
		assertEquals(false, pair3.equals(pair2));
	}
	
	@Test
	public void EqualityTest3() {
		Pair<String, Integer> pair1 = new Pair<String, Integer>("Word1", null);
		Pair<String, Integer> pair2 = new Pair<String, Integer>("Word1", null);
		Pair<Integer, String> pair3 = new Pair<Integer, String>(null, "Word1");
		assertEquals(false, pair1.equals(pair2));
		assertEquals(false, pair2.equals(pair1));
		assertEquals(false, pair1.equals(pair3));
		assertEquals(false, pair3.equals(pair1));
		assertEquals(false, pair2.equals(pair3));
		assertEquals(false, pair3.equals(pair2));
	}
	
	@Test
	public void EqualityTest4() {
		Pair<String, Integer> pair1 = new Pair<String, Integer>(null, 1);
		Pair<String, Integer> pair2 = new Pair<String, Integer>(null, 1);
		Pair<Integer, String> pair3 = new Pair<Integer, String>(1, null);
		assertEquals(false, pair1.equals(pair2));
		assertEquals(false, pair2.equals(pair1));
		assertEquals(false, pair1.equals(pair3));
		assertEquals(false, pair3.equals(pair1));
		assertEquals(false, pair2.equals(pair3));
		assertEquals(false, pair3.equals(pair2));
	}
	
	@Test
	public void EqualityTest5() {
		Pair<String, Integer> pair1 = new Pair<String, Integer>(null, null);
		Pair<String, Integer> pair2 = new Pair<String, Integer>(null, null);
		assertEquals(false, pair1.equals(pair2));
		assertEquals(false, pair2.equals(pair1));
	}
	
	@Test
	public void EqualityTest6() {
		Pair<String, Integer> pair1 = new Pair<String, Integer>(null, null);
		Pair<String, Integer> pair2 = null;
		assertEquals(false, pair1.equals(pair2));
	}

	@Test
	public void HashCodeTest() {
		Pair<String, String> pair1 = new Pair<String, String>("Word1", "Word2");
		assertEquals(2074761007, pair1.hashCode());
	}
	
	@Test
	public void ContainsTest1() {
		Pair<String, String> pair1 = new Pair<String, String>("Word1", "Word2");
		assertEquals(true, pair1.contains("Word1"));
		assertEquals(true, pair1.contains("Word2"));
	}
	
	@Test
	public void ContainsTest2() {
		Pair<String, String> pair1 = new Pair<String, String>(null, "Word2");
		assertEquals(false, pair1.contains("Word1"));
		assertEquals(true, pair1.contains("Word2"));
	}
	
	@Test
	public void ContainsTest3() {
		Pair<String, String> pair1 = new Pair<String, String>("Word1", null);
		assertEquals(true, pair1.contains("Word1"));
		assertEquals(false, pair1.contains("Word2"));
	}
	
	@Test
	public void CompareTest1() {
		ComparablePair<String, String> pair1 = new ComparablePair<String, String>("Word1", "Word2");
		ComparablePair<String, String> pair2 = new ComparablePair<String, String>("Word2", "Word1");
		ComparablePair<String, String> pair3 = new ComparablePair<String, String>("Word1", "Word3");
		ComparablePair<String, String> pair4 = new ComparablePair<String, String>("Word1", "Word2");
		assertEquals(-1, pair1.compareTo(pair2));
		assertEquals(1, pair2.compareTo(pair1));
		assertEquals(-1, pair1.compareTo(pair3));
		assertEquals(1, pair3.compareTo(pair1));
		assertEquals(0, pair1.compareTo(pair4));
		assertEquals(0, pair4.compareTo(pair1));
	}
}
