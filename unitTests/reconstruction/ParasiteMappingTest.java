package reconstruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import reconstruction.solver.TreeCollapse;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class ParasiteMappingTest {

	private CoevolutionaryHistory gopherLouseHistory;
	private final String gopherLouseExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "RealExamples" + File.separator + "PocketGopherChewingLice2003A.nex";		
	
	@Before
	public void LoadGopherLouse() {
		try {
			NexusFile file = new JaneNexusFile(this.gopherLouseExampleFileName);
			this.gopherLouseHistory = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void GopherLouseFirstSetOfNodeOrderingsTest() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();	
		
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00003"), 2);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00001"), 3);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00005"), 6);		
		this.gopherLouseHistory.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.gopherLouseHistory);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfLossEvents());
	
	}	
	
	@Test
	public void GopherLouseSecondSetOfNodeOrderingsTest() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();	
		
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00001"), 1);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00006"), 2);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00003"), 3);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00005"), 6);		
		this.gopherLouseHistory.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.gopherLouseHistory);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
		
	}	
	
	@Test
	public void IsParasiteMappedToHostEdgeTest() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();	
		
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00001"), 1);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00006"), 2);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00003"), 3);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00005"), 6);		
		this.gopherLouseHistory.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.gopherLouseHistory);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00001")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00002")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00003")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00004")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00005")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00006")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00007")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00008")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00009")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdge(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00010")));	
	}
	
	@Test
	public void IsParasiteMappedToHostEdgeAndHostSwitchEventTest() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();	
		
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00001"), 1);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00006"), 2);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00003"), 3);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00005"), 6);		
		this.gopherLouseHistory.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.gopherLouseHistory);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00001")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00002")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00003")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00004")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00005")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00006")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00007")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00008")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00009")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostEdgeAndHostSwitchEvent(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00010")));			
	}
	
	@Test
	public void IsParasiteMappedToHostNodeTest() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();	
		
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00001"), 1);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00006"), 2);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00003"), 3);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00005"), 6);		
		this.gopherLouseHistory.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.gopherLouseHistory);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00001")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00002")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00003")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00004")));
		assertEquals(false, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00005")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00006")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00007")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00008")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00009")));
		assertEquals(true, reconstruction.getParasiteMapping().isParasiteMappedToHostNode(this.gopherLouseHistory.getParasiteTree().getNode("InternalNode_00010")));	
	}
}
