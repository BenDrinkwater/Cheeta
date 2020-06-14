package util.reconstruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import org.junit.Before;
import org.junit.Test;

import reconstruction.LooseParasiteMapping;
import reconstruction.MappingPoint;
import tanglegram.CoevolutionaryHistory;
import util.reconstruction.ReconstructionSet;

public class ReconstructionSetTest {

	private LooseParasiteMapping looseParasiteMapping1;	
	private LooseParasiteMapping looseParasiteMapping2;	
	private LooseParasiteMapping looseParasiteMapping3;	
	private LooseParasiteMapping looseParasiteMapping4;	
	private LooseParasiteMapping looseParasiteMapping5;	
	private LooseParasiteMapping looseParasiteMapping6;	
	private LooseParasiteMapping looseParasiteMapping7;	
	private LooseParasiteMapping looseParasiteMapping8;	
	private LooseParasiteMapping looseParasiteMapping9;	

	private CoevolutionaryHistory ficusWasp;
	private final String ficusWaspFileName 
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004a.nex";	
	
	private void LoadFicusWaspExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.ficusWaspFileName);
			
			this.ficusWasp = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadAllLooseParasiteMappings() {
		this.LoadFicusWaspExample();
		// need a tree to allow for mapping points
		MappingPoint mappingPoint1 = new MappingPoint(this.ficusWasp.getHostTree().getNode("InternalNode_00001"));
		MappingPoint mappingPoint2 = new MappingPoint(this.ficusWasp.getHostTree().getNode("InternalNode_00002"));
		MappingPoint mappingPoint3 = new MappingPoint(this.ficusWasp.getHostTree().getNode("InternalNode_00003"));
		MappingPoint mappingPoint4 = new MappingPoint(this.ficusWasp.getHostTree().getNode("InternalNode_00004"));
		MappingPoint mappingPoint5 = new MappingPoint(this.ficusWasp.getHostTree().getNode("InternalNode_00005"));
		this.looseParasiteMapping1 = new LooseParasiteMapping(mappingPoint1, LooseParasiteMapping.Event.Codivergence);
		this.looseParasiteMapping1.addCost(0);
		this.looseParasiteMapping2 = new LooseParasiteMapping(mappingPoint1, LooseParasiteMapping.Event.Duplication);
		this.looseParasiteMapping2.addCost(1);
		this.looseParasiteMapping3 = new LooseParasiteMapping(mappingPoint1, LooseParasiteMapping.Event.LeafNodeMapping);
		this.looseParasiteMapping3.addCost(0);
		this.looseParasiteMapping4 = new LooseParasiteMapping(mappingPoint1, LooseParasiteMapping.Event.HostSwitchDirection1);
		this.looseParasiteMapping4.addCost(2);
		this.looseParasiteMapping5 = new LooseParasiteMapping(mappingPoint1, LooseParasiteMapping.Event.HostSwitchDirection2);
		this.looseParasiteMapping5.addCost(2);
		this.looseParasiteMapping6 = new LooseParasiteMapping(mappingPoint2, LooseParasiteMapping.Event.Duplication);
		this.looseParasiteMapping6.addCost(1);
		this.looseParasiteMapping7 = new LooseParasiteMapping(mappingPoint3, LooseParasiteMapping.Event.LeafNodeMapping);
		this.looseParasiteMapping7.addCost(0);
		this.looseParasiteMapping8 = new LooseParasiteMapping(mappingPoint4, LooseParasiteMapping.Event.HostSwitchDirection1);
		this.looseParasiteMapping8.addCost(2);
		this.looseParasiteMapping9 = new LooseParasiteMapping(mappingPoint5, LooseParasiteMapping.Event.HostSwitchDirection2);
		this.looseParasiteMapping9.addCost(2);
	}
	
	@Test
	public void CreateReconstructionSet() {
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertNotNull(set);
	}
	
	@Test
	public void AddTest1() {
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertNotNull(set);
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());
	}
	
	@Test
	public void AddTest2() {
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertNotNull(set);
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());
		set.add(this.looseParasiteMapping2);
		assertEquals(1, set.size());	
		set.add(this.looseParasiteMapping6);
		assertEquals(2, set.size());			
	}
	
	@Test
	public void AddTest3() {
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertNotNull(set);
		set.add(this.looseParasiteMapping2);
		assertEquals(1, set.size());
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());		
		set.add(this.looseParasiteMapping6);
		assertEquals(2, set.size());
	}
	
	@Test
	public void AddAllTest1() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping3);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());	
		set.addAll(list);
		assertEquals(1, set.size());		
	}
	
	@Test
	public void AddAllTest2() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping2);
		list.add(this.looseParasiteMapping4);
		list.add(this.looseParasiteMapping5);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());	
		set.addAll(list);
		assertEquals(1, set.size());			
	}
	
	@Test
	public void AddAllTest3() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping2);
		list.add(this.looseParasiteMapping3);
		list.add(this.looseParasiteMapping4);
		list.add(this.looseParasiteMapping5);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());	
		set.addAll(list);
		assertEquals(1, set.size());	
	}
	
	@Test
	public void AddAllTest4() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping1);
		list.add(this.looseParasiteMapping3);
		list.add(this.looseParasiteMapping4);
		list.add(this.looseParasiteMapping5);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.add(this.looseParasiteMapping2);
		assertEquals(1, set.size());	
		set.addAll(list);
		assertEquals(1, set.size());	
	}
	
	@Test
	public void AddAllTest5() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping4);
		list.add(this.looseParasiteMapping2);
		list.add(this.looseParasiteMapping1);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.add(this.looseParasiteMapping5);
		assertEquals(1, set.size());	
		set.addAll(list);
		assertEquals(1, set.size());	
	}

	@Test
	public void ClearTest() {
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.add(this.looseParasiteMapping5);
		assertEquals(1, set.size());	
		set.clear();
		assertEquals(0, set.size());	
		set.add(this.looseParasiteMapping5);
		assertEquals(1, set.size());		
	}

	@Test
	public void IsEmptyTest() {
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertEquals(true, set.isEmpty());
		set.add(this.looseParasiteMapping5);
		assertEquals(1, set.size());	
		assertEquals(false, set.isEmpty());
		set.clear();
		assertEquals(0, set.size());	
		assertEquals(true, set.isEmpty());
		set.add(this.looseParasiteMapping5);
		assertEquals(1, set.size());	
		assertEquals(false, set.isEmpty());
	}
	
	@Test
	public void containsTest() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping1);
		list.add(this.looseParasiteMapping6);
		list.add(this.looseParasiteMapping7);
		list.add(this.looseParasiteMapping8);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.addAll(list);	
		assertEquals(true, set.contains(this.looseParasiteMapping1));
		assertEquals(false, set.contains(this.looseParasiteMapping2));
		assertEquals(true, set.contains(this.looseParasiteMapping8));
		assertEquals(false, set.contains(this.looseParasiteMapping9));
	}
	
	@Test
	public void containsAllTest() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping1);
		list.add(this.looseParasiteMapping6);
		list.add(this.looseParasiteMapping7);
		list.add(this.looseParasiteMapping8);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.addAll(list);	
		assertEquals(true, set.containsAll(list));	
		list.add(this.looseParasiteMapping9);
		assertEquals(false, set.containsAll(list));	
	}
	
	@Test
	public void IteratorTest() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping1);
		list.add(this.looseParasiteMapping6);
		list.add(this.looseParasiteMapping7);
		list.add(this.looseParasiteMapping8);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.addAll(list);
		Iterator<LooseParasiteMapping> iterator = set.iterator(); 
		assertEquals(true, this.looseParasiteMapping7.equals(iterator.next()));
	}
	
	@Test
	public void RemoveTest() {
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		set.add(this.looseParasiteMapping5);
		assertEquals(1, set.size());	
		set.remove(this.looseParasiteMapping5);
		assertEquals(0, set.size());	
	}
	
	@Test
	public void RemoveAllTest1() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping6);
		list.add(this.looseParasiteMapping7);
		list.add(this.looseParasiteMapping8);
		list.add(this.looseParasiteMapping9);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertEquals(true, set.isEmpty());
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());
		set.addAll(list);
		assertEquals(5, set.size());
		assertEquals(true, set.removeAll(list));
		assertEquals(1, set.size());		
	}
	
	@Test
	public void RemoveAllTest2() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping6);
		list.add(this.looseParasiteMapping7);
		list.add(this.looseParasiteMapping8);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertEquals(true, set.isEmpty());
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());
		set.addAll(list);
		list.add(this.looseParasiteMapping9);
		assertEquals(4, set.size());
		assertEquals(false, set.removeAll(list));
		assertEquals(1, set.size());		
	}
	
	@Test
	public void RetainAllTest() {
		List<LooseParasiteMapping> list = new LinkedList<LooseParasiteMapping>();
		list.add(this.looseParasiteMapping6);
		list.add(this.looseParasiteMapping7);
		list.add(this.looseParasiteMapping8);
		list.add(this.looseParasiteMapping9);
		Set<LooseParasiteMapping> set = new ReconstructionSet();
		assertEquals(true, set.isEmpty());
		set.add(this.looseParasiteMapping1);
		assertEquals(1, set.size());
		set.addAll(list);
		assertEquals(5, set.size());
		set.retainAll(list);
		assertEquals(4, set.size());		
	}
}
