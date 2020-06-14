package reconstruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.Node;
import util.Pair;

public class LooseParasiteMappingTest {

	private CoevolutionaryHistory gopherLice;
	private final String gopherLiceFileName 
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	

	@Before
	public void LoadGopherLiceExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.gopherLiceFileName);		
			this.gopherLice = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Test
	public void LooseParasiteMappingTest1() {
		Node node1 = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		Node node2 = this.gopherLice.getHostTree().getNode("InternalNode_00002");
		Node node3 = this.gopherLice.getHostTree().getNode("InternalNode_00003");
		
		Pair<Edge, Edge> edgePair = new Pair<Edge, Edge> (node2.getParentEdge(), node1.getParentEdge());
		
		MappingPoint parent = new MappingPoint(node3);		
		MappingPoint left = new MappingPoint(edgePair);		
		MappingPoint right = new MappingPoint(node2);
		
		LooseParasiteMapping leftLooseParasiteMapping = new LooseParasiteMapping(left, LooseParasiteMapping.Event.HostSwitchDirection1);
		LooseParasiteMapping rightLooseParasiteMapping = new LooseParasiteMapping(right, LooseParasiteMapping.Event.Codivergence);
		LooseParasiteMapping parentLooseParasiteMapping = new LooseParasiteMapping(parent, LooseParasiteMapping.Event.Codivergence, leftLooseParasiteMapping, rightLooseParasiteMapping);
		assertNotNull(parentLooseParasiteMapping);
	}

	@Test
	public void LooseParasiteMappingTest2() {
		Node node1 = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		Node node2 = this.gopherLice.getHostTree().getNode("InternalNode_00002");
		Node node3 = this.gopherLice.getHostTree().getNode("InternalNode_00003");
		
		Pair<Edge, Edge> edgePair = new Pair<Edge, Edge> (node2.getParentEdge(), node1.getParentEdge());
		
		MappingPoint parent = new MappingPoint(node3);		
		MappingPoint left = new MappingPoint(edgePair);		
		MappingPoint right = new MappingPoint(node2);
		
		LooseParasiteMapping leftLooseParasiteMapping = new LooseParasiteMapping(left, LooseParasiteMapping.Event.HostSwitchDirection1);
		LooseParasiteMapping rightLooseParasiteMapping = new LooseParasiteMapping(right, LooseParasiteMapping.Event.Codivergence);
		LooseParasiteMapping parentLooseParasiteMapping = new LooseParasiteMapping(parent, LooseParasiteMapping.Event.Codivergence, leftLooseParasiteMapping, rightLooseParasiteMapping);
		assertNotNull(parentLooseParasiteMapping);
		
		assertEquals("((host_InternalNode_00003,host_InternalNode_00002),(host_InternalNode_00007,host_InternalNode_00001))", parentLooseParasiteMapping.getLeftChildsMappingPoint().toString());
		assertEquals("host_InternalNode_00002", parentLooseParasiteMapping.getRightChildsMappingPoint().toString());
	}
	
	@Test
	public void LooseParasiteMappingTest3() {
		Node node1 = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		Node node2 = this.gopherLice.getHostTree().getNode("InternalNode_00002");
		Node node3 = this.gopherLice.getHostTree().getNode("InternalNode_00003");
		
		Pair<Edge, Edge> edgePair = new Pair<Edge, Edge> (node2.getParentEdge(), node1.getParentEdge());
		
		MappingPoint parent = new MappingPoint(node3);		
		MappingPoint left = new MappingPoint(edgePair);		
		MappingPoint right = new MappingPoint(node2);
		
		LooseParasiteMapping leftLooseParasiteMapping = new LooseParasiteMapping(left, LooseParasiteMapping.Event.HostSwitchDirection1);
		LooseParasiteMapping rightLooseParasiteMapping = new LooseParasiteMapping(right, LooseParasiteMapping.Event.Codivergence);
		LooseParasiteMapping parentLooseParasiteMapping = new LooseParasiteMapping(parent, LooseParasiteMapping.Event.Codivergence, leftLooseParasiteMapping, rightLooseParasiteMapping);
		assertNotNull(parentLooseParasiteMapping);
		
		assertEquals("HostSwitchDirection1", parentLooseParasiteMapping.getLeftChildsEvent().toString());
		assertEquals("Codivergence", parentLooseParasiteMapping.getRightChildsEvent().toString());
	}
	
	@Test
	public void LooseParasiteMappingTest4() {
		Node node1 = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		Node node2 = this.gopherLice.getHostTree().getNode("InternalNode_00002");
		Node node3 = this.gopherLice.getHostTree().getNode("InternalNode_00003");
		
		Pair<Edge, Edge> edgePair = new Pair<Edge, Edge> (node2.getParentEdge(), node1.getParentEdge());
		
		MappingPoint parent = new MappingPoint(node3);		
		MappingPoint left = new MappingPoint(edgePair);		
		MappingPoint right = new MappingPoint(node2);
		
		LooseParasiteMapping leftLooseParasiteMapping = new LooseParasiteMapping(left, LooseParasiteMapping.Event.HostSwitchDirection1);
		LooseParasiteMapping rightLooseParasiteMapping = new LooseParasiteMapping(right, LooseParasiteMapping.Event.Codivergence);
		LooseParasiteMapping parentLooseParasiteMapping = new LooseParasiteMapping(parent, LooseParasiteMapping.Event.Codivergence, leftLooseParasiteMapping, rightLooseParasiteMapping);
		parentLooseParasiteMapping.addCost(10);
		
		assertEquals("host_InternalNode_0000310", parentLooseParasiteMapping.toString());
	}
	
}
