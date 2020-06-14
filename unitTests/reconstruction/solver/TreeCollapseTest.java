package reconstruction.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class TreeCollapseTest {

	private CoevolutionaryHistory gopherLouseHistory;
	private final String gopherLouseExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	
	
	private CoevolutionaryHistory reconstructionTest1;
	private final String reconstructionTest1FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest1.nex";
	private CoevolutionaryHistory reconstructionTest2;
	private final String reconstructionTest2FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest2.nex";
	private CoevolutionaryHistory reconstructionTest3;
	private final String reconstructionTest3FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest3.nex";
	private CoevolutionaryHistory reconstructionTest4;
	private final String reconstructionTest4FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest4.nex";
	private CoevolutionaryHistory reconstructionTest5;
	private final String reconstructionTest5FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest5.nex";
	private CoevolutionaryHistory reconstructionTest9;
	private final String reconstructionTest9FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest9.nex";
	
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
	public void LoadReconstructionTest1() {
		try {
			NexusFile file = new JaneNexusFile(this.reconstructionTest1FileName);
			
			
			this.reconstructionTest1 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadReconstructionTest2() {
		try {
			NexusFile file = new JaneNexusFile(this.reconstructionTest2FileName);
			
			
			this.reconstructionTest2 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadReconstructionTest3() {
		try {
			NexusFile file = new JaneNexusFile(this.reconstructionTest3FileName);
			
			
			this.reconstructionTest3 = file.read();
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
	
	@Before
	public void LoadReconstructionTest5() {
		try {
			NexusFile file = new JaneNexusFile(this.reconstructionTest5FileName);
			
			
			this.reconstructionTest5 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadReconstructionTest9() {
		try {
			NexusFile file = new JaneNexusFile(this.reconstructionTest9FileName);
			
			
			this.reconstructionTest9 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Test
	public void RunTreeCollapse() {
		TreeCollapse treeCollapse = new TreeCollapse(this.gopherLouseHistory);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		assertNotNull(reconstruction);
	}
	
	@Test
	public void ReconstructionTest1FirstSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest1.getHostTree().getNode("InternalNode_00003"), 0);
		timings.put(this.reconstructionTest1.getHostTree().getNode("InternalNode_00002"), 1);
		timings.put(this.reconstructionTest1.getHostTree().getNode("InternalNode_00001"), 2);
		this.reconstructionTest1.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest1);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
		
	@Test
	public void ReconstructionTest1SecondSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest1.getHostTree().getNode("InternalNode_00003"), 0);
		timings.put(this.reconstructionTest1.getHostTree().getNode("InternalNode_00001"), 1);
		timings.put(this.reconstructionTest1.getHostTree().getNode("InternalNode_00002"), 2);
		this.reconstructionTest1.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest1);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	
	@Test
	public void ReconstructionTest2FirstSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest2.getHostTree().getNode("InternalNode_00003"), 0);
		timings.put(this.reconstructionTest2.getHostTree().getNode("InternalNode_00002"), 1);
		timings.put(this.reconstructionTest2.getHostTree().getNode("InternalNode_00001"), 2);
		this.reconstructionTest2.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest2);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
		
	@Test
	public void ReconstructionTest2SecondSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest2.getHostTree().getNode("InternalNode_00003"), 0);
		timings.put(this.reconstructionTest2.getHostTree().getNode("InternalNode_00001"), 1);
		timings.put(this.reconstructionTest2.getHostTree().getNode("InternalNode_00002"), 2);
		this.reconstructionTest2.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest2);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void ReconstructionTest3FirstSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();	
		
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00003"), 2);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00001"), 3);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00005"), 6);		
		this.reconstructionTest3.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest3);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void ReconstructionTest3SecondSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00003"), 2);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00002"), 3);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00001"), 4);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00004"), 5);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00005"), 6);		
		this.reconstructionTest3.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest3);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();	
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void ReconstructionTest3ThirdSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00003"), 2);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00002"), 3);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00001"), 4);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00005"), 5);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00004"), 6);			
		this.reconstructionTest3.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest3);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void ReconstructionTest3FourthSetOfNodeOrderings() {
		Map<Node, Integer> timings = new LinkedHashMap<Node, Integer>();
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00007"), 0);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00006"), 1);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00003"), 2);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00001"), 3);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00002"), 4);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00005"), 5);
		timings.put(this.reconstructionTest3.getHostTree().getNode("InternalNode_00004"), 6);		
		this.reconstructionTest3.getHostTree().setUniqueNodeTimings(timings);
		
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest3);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
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
	public void ReconstructionTest5FourthSetOfNodeOrderings() {
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest5);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void ReconstructionTest9FourthSetOfNodeOrderings() {
		TreeCollapse treeCollapse = new TreeCollapse(this.reconstructionTest9);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
}
