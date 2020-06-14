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

public class HostMappingTest {

	private CoevolutionaryHistory gopherLouseHistory;
	private final String gopherLouseExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "RealExamples" + File.separator + "PocketGopherChewingLice2003A.nex";		
	
	private CoevolutionaryHistory reconstructionTest4;
	private final String reconstructionTest4FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest4.nex";

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
	
	@Before
	public void LoadReconstructionTest4() {
		try {			
			NexusFile file = new JaneNexusFile(this.reconstructionTest4FileName);
			this.reconstructionTest4 = file.read();			
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
		
		assertEquals(9, reconstruction.getHostMapping().numberOfLossEvents());
		
		assertEquals((Integer) 2, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00001")));
		assertEquals((Integer) 1, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00002")));
		assertEquals((Integer) 1, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00003")));		
		assertEquals((Integer) 1, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00006")));	
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
		
		assertEquals(6, reconstruction.getHostMapping().numberOfLossEvents());
		
		assertEquals((Integer) 1, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00002")));
		assertEquals((Integer) 1, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.gopherLouseHistory.getHostTree().getNode("InternalNode_00003")));
	}	
	
	@Test
	public void ReconstructionTest4FirstSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();	
		
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00003"), 2);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001"), 3);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00005"), 6);		
		this.reconstructionTest4.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest4);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
		
		assertEquals(0, reconstruction.getHostMapping().numberOfLossEvents());
	}		
	
	@Test
	public void ReconstructionTest4SecondSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00003"), 2);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00004"), 3);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00005"), 4);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001"), 5);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002"), 6);		
		this.reconstructionTest4.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest4);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();		
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
		
		assertEquals(4, reconstruction.getHostMapping().numberOfLossEvents());
		
		assertEquals((Integer) 2, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001")));
		assertEquals((Integer) 2, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002")));
	}		
	
	@Test
	public void ReconstructionTest4ThirdSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00005"), 2);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00004"), 3);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00003"), 4);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002"), 5);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001"), 6);			
		this.reconstructionTest4.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest4);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();	
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfLossEvents());
		
		assertEquals(9, reconstruction.getHostMapping().numberOfLossEvents());
		
		assertEquals((Integer) 2, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001")));
		assertEquals((Integer) 2, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002")));
		assertEquals((Integer) 4, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.reconstructionTest4.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 1, reconstruction.getHostMapping().numberOfLossEventsAtNode(this.reconstructionTest4.getHostTree().getNode("InternalNode_00006")));		
	}	
	
	@Test
	public void ReconstructionTest4FourthSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00003"), 1);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001"), 2);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002"), 3);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00006"), 4);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00005"), 6);		
		this.reconstructionTest4.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest4);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();	
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void ToStringTest1() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00003"), 1);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001"), 2);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002"), 3);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00006"), 4);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00005"), 6);		
		this.reconstructionTest4.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest4);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals("No Loss Events in this Reconstruction", reconstruction.getHostMapping().toString());
	}
	
	@Test
	public void ToStringTest2() {	
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00005"), 2);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00004"), 3);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00003"), 4);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00002"), 5);
		timings.put(this.reconstructionTest4.getHostTree().getNode("InternalNode_00001"), 6);			
		this.reconstructionTest4.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest4);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();	
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfLossEvents());
		assertEquals("host_InternalNode_00003=>4\nhost_InternalNode_00001=>2\nhost_InternalNode_00006=>1\nhost_InternalNode_00002=>2\n", reconstruction.getHostMapping().toString());
	}
}
