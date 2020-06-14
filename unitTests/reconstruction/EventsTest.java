package reconstruction;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class EventsTest {

	private Events events;
	
	@Test
	public void GetCodivergenceTest1() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 1, this.events.getNumberOfCodivergenceEvents());
	}
	
	@Test
	public void GetCodivergenceTest2() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("SomethingElse", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 0, this.events.getNumberOfCodivergenceEvents());	
	}	
	
	@Test
	public void GetDuplicationTest1() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 2, this.events.getNumberOfDuplicationEvents());	
	}
	
	@Test
	public void GetDuplicationTest2() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("SomethingElse", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 0, this.events.getNumberOfDuplicationEvents());			
	}		

	@Test
	public void GetHostSwitchTest1() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 3, this.events.getNumberOfHostSwitchEvents());
	}
	
	@Test
	public void GetHostSwitchTest2() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("SomethingElse", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 0, this.events.getNumberOfHostSwitchEvents());		
	}	

	@Test
	public void GetLossTest1() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 4, this.events.getNumberOfLossEvents());
	}
	
	@Test
	public void GetLossTest2() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("SomethingElse", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 0, this.events.getNumberOfLossEvents());	
	}		
	
	@Test
	public void CompareTest1() {
		Map<String, Integer> eventMap1 = new LinkedHashMap<String, Integer>();
		eventMap1.put("Codivergence", 1);
		eventMap1.put("Duplication", 2);
		eventMap1.put("Host Switch", 3);
		eventMap1.put("SomethingElse", 4);
		Events events1 = new Events(eventMap1, new int[] {0, 1, 1, 2});
		
		Map<String, Integer> eventMap2 = new LinkedHashMap<String, Integer>();
		eventMap2.put("Codivergence", 1);
		eventMap2.put("Duplication", 2);
		eventMap2.put("Host Switch", 3);
		eventMap2.put("SomethingElse", 4);
		Events events2 = new Events(eventMap2, new int[] {0, 1, 1, 2});
		
		assertEquals(0, events1.compare(events2));
		assertEquals(0, events2.compare(events1));
	}

	@Test
	public void CompareTest2() {
		Map<String, Integer> eventMap1 = new LinkedHashMap<String, Integer>();
		eventMap1.put("Codivergence", 1);
		eventMap1.put("Duplication", 2);
		eventMap1.put("Host Switch", 3);
		eventMap1.put("Loss", 4);
		Events events1 = new Events(eventMap1, new int[] {0, 1, 1, 2});
		
		Map<String, Integer> eventMap2 = new LinkedHashMap<String, Integer>();
		eventMap2.put("Codivergence", 1);
		eventMap2.put("Duplication", 2);
		eventMap2.put("Host Switch", 3);
		eventMap2.put("Loss", 3);
		Events events2 = new Events(eventMap2, new int[] {0, 1, 1, 2});
		
		assertEquals(-1, events1.compare(events2));
		assertEquals(1, events2.compare(events1));		
	}

	@Test
	public void CompareTest3() {
		Map<String, Integer> eventMap1 = new LinkedHashMap<String, Integer>();
		eventMap1.put("Codivergence", 1);
		eventMap1.put("Duplication", 2);
		eventMap1.put("Host Switch", 3);
		eventMap1.put("SomethingElse", 4);
		Events events1 = new Events(eventMap1, new int[] {0, 1, 1, 2});
		
		Map<String, Integer> eventMap2 = new LinkedHashMap<String, Integer>();
		eventMap2.put("Codivergence", 1);
		eventMap2.put("Duplication", 2);
		eventMap2.put("Host Switch", 4);
		eventMap2.put("SomethingElse", 4);
		Events events2 = new Events(eventMap2, new int[] {0, 1, 1, 2});
		
		assertEquals(1, events1.compare(null));
		assertEquals(1, events2.compare(null));		
	}
	
	@Test
	public void InvalidNumberOfEvents() {
		Map<String, Integer> eventMap = null;
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals((Integer) 0, this.events.getNumberOfCodivergenceEvents());	
		assertEquals((Integer) 0, this.events.getNumberOfDuplicationEvents());	
		assertEquals((Integer) 0, this.events.getNumberOfHostSwitchEvents());	
		assertEquals((Integer) 0, this.events.getNumberOfLossEvents());	
	}
	
	@Test
	public void ToStringTest() {
		Map<String, Integer> eventMap = new LinkedHashMap<String, Integer>();
		eventMap.put("Codivergence", 1);
		eventMap.put("Duplication", 2);
		eventMap.put("Host Switch", 3);
		eventMap.put("Loss", 4);
		this.events = new Events(eventMap, new int[] {0, 1, 1, 2});
		assertEquals("{Codivergence=1, Duplication=2, Host Switch=3, Loss=4} 12", this.events.toString());
	}
}
