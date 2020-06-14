package util.comparators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class DecendingComparatorTest {
	
	private CoevolutionaryHistory beesNosema;
	private final String beesNosemaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "RealExamples" + File.separator + "BeesNosema2009.nex";	
		
	private CoevolutionaryHistory caliciviruses;
	private final String calicivirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "RealExamples" + File.separator + "Caliciviruses2006.nex";
	
	@Before
	public void LoadBeesNosemaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.beesNosemaFileName);
			this.beesNosema = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.beesNosema.getHostTree().getNode("InternalNode_00003"), 0);
			nodes.put(this.beesNosema.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.beesNosema.getHostTree().getNode("InternalNode_00001"), 2);
			this.beesNosema.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadCalicivirusesExample() {
		try {
			NexusFile file = new JaneNexusFile(this.calicivirusesFileName);
			this.caliciviruses = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00003"), 5);
			this.caliciviruses.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Test
	public void DepthComparatorTest1() {
		Set<Node> nodes = this.beesNosema.getHostTree().getAllNodes();
		Set<Node> ordered = new TreeSet<Node>();
		for (Node node : nodes) {
			if (!node.isLeafNode()) {
				ordered.add(node);
			}
		}
		int i = 2;
		for (Node node : ordered) {
			assertEquals(i, node.distanceToRoot());
			i--;
		}
		ordered = new TreeSet<Node>(new DecendingComparator<Node>());
		for (Node node : nodes) {
			if (!node.isLeafNode()) {
				ordered.add(node);
			}
		}
		int j = 0;
		for (Node node : ordered) {
			assertEquals(j, node.distanceToRoot());
			j++;
		}		
	}
	
	@Test
	public void DepthComparatorTest2() {
		TreeMap<Integer, Node> nodeOrder = new TreeMap<Integer, Node>(new DecendingComparator<Integer>());	
		Set<Node> nodes = this.caliciviruses.getHostTree().getAllNodes();
		for (Node node : nodes) {
			if (!node.isLeafNode()) {
				nodeOrder.put(node.distanceToRoot(), node);
			}
		}
		Iterator<Node> nodeIterator = nodeOrder.values().iterator();
		assertEquals("host_InternalNode_00003", nodeIterator.next().toString());
		assertEquals("host_InternalNode_00001", nodeIterator.next().toString());
		assertEquals("host_InternalNode_00002", nodeIterator.next().toString());
		assertEquals("host_InternalNode_00004", nodeIterator.next().toString());
		assertEquals("host_InternalNode_00005", nodeIterator.next().toString());
		assertEquals("host_InternalNode_00006", nodeIterator.next().toString());
	}
}
