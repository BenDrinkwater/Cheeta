package reconstruction.solver.tc;

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

import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class ReconstructionGeneratorTest {

	private CoevolutionaryHistory parasiteCircleExampleHistory;
	private CoevolutionaryHistory strongLinkHistory;
	private CoevolutionaryHistory switchPathHistory;
	private CoevolutionaryHistory gopherLouseHistory;
	private CoevolutionaryHistory duplicationLossOrSwitchParadigm;
	private CoevolutionaryHistory duplicationSwitchA;
	private CoevolutionaryHistory duplicationSwitchB;
	private CoevolutionaryHistory multipleCollapsePoint;
	
	private final String parasiteCircleExampleFileName = "unitTestFiles" + File.separator + "reconstruction" +  File.separator + "solver" +  File.separator + "tc"  +  File.separator +  "ParasiteCircle.nex";
	private final String strongLinkExampleFileName = "unitTestFiles" + File.separator + "reconstruction" +  File.separator + "solver" +  File.separator + "tc"  +  File.separator +  "StrongLink.nex";
	private final String switchPathExampleFileName = "unitTestFiles" + File.separator + "reconstruction" +  File.separator + "solver" +  File.separator + "tc"  +  File.separator +  "SwitchPath.nex";
	private final String gopherLouseExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	
	private final String duplicationLossOrSwitchParadigmFileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest7.nex";
	private final String duplicationSwitchAFilename = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest10.nex";
	private final String duplicationSwitchBFilename = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest11.nex";
	private final String multipleCollapsePointFileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest16.nex";
	
	@Before
	public void LoadParasiteCircle() {
		try {
			NexusFile file = new JaneNexusFile(this.parasiteCircleExampleFileName);
			
			
			this.parasiteCircleExampleHistory = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Before
	public void LoadStrongLink() {
		try {
			NexusFile file = new JaneNexusFile(this.strongLinkExampleFileName);
			
			
			this.strongLinkHistory = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadSwitchPath() {
		try {
			NexusFile file = new JaneNexusFile(this.switchPathExampleFileName);
			
			
			this.switchPathHistory = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
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
	public void LoadDuplicationLossOrSwitchParadigm() {
		try {
			NexusFile file = new JaneNexusFile(this.duplicationLossOrSwitchParadigmFileName);
			
			
			this.duplicationLossOrSwitchParadigm = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("InternalNode_00006"), 0); 
			nodes.put(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("InternalNode_00005"), 5);
			this.duplicationLossOrSwitchParadigm.getHostTree().setUniqueNodeTimings(nodes);				
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadDuplicationSwitchAFilename() {
		try {
			NexusFile file = new JaneNexusFile(this.duplicationSwitchAFilename);
			
			
			this.duplicationSwitchA = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadDuplicationSwitchBFilename() {
		try {
			NexusFile file = new JaneNexusFile(this.duplicationSwitchBFilename);
			
			
			this.duplicationSwitchB = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMultipleCollapsePointFilename() {
		try {
			NexusFile file = new JaneNexusFile(this.multipleCollapsePointFileName);
			
			
			this.multipleCollapsePoint = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Test
	public void ReconstructionTest1() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.parasiteCircleExampleHistory);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Host Switch"));
	}

	@Test
	public void ReconstructionTest2() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.strongLinkHistory);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
		
		assertEquals((Integer) 3, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Host Switch"));
	}
	
	@Test
	public void ReconstructionTest3() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.switchPathHistory);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Host Switch"));
	}	
	
	@Test
	public void ReconstructionTest4() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.gopherLouseHistory);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
		
		assertEquals((Integer) 7, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 3, parasiteMappingList.getEvents().get("Host Switch"));
	}	
	
	@Test
	public void ReconstructionTest5() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.duplicationLossOrSwitchParadigm);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
				
		assertNotNull(parasiteMappingList);
		
		assertEquals((Integer) 5, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 3, parasiteMappingList.getEvents().get("Host Switch"));		
	}	
	
	@Test
	public void ReconstructionTest10() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.duplicationSwitchA);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
				
		assertNotNull(parasiteMappingList);
		
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Host Switch"));		
	}	
	
	@Test
	public void ReconstructionTest11() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.duplicationSwitchB);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
				
		assertNotNull(parasiteMappingList);
		
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 1, parasiteMappingList.getEvents().get("Host Switch"));		
	}	
	
	@Test
	public void ReconstructionTest16() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.multipleCollapsePoint);
		ParasiteMappingList parasiteMappingList = reconstructionGenerator.generatePartialReconstruction();
				
		assertNotNull(parasiteMappingList);
		
		assertEquals((Integer) 4, parasiteMappingList.getEvents().get("Codivergence"));
		assertEquals((Integer) 3, parasiteMappingList.getEvents().get("Duplication"));
		assertEquals((Integer) 0, parasiteMappingList.getEvents().get("Host Switch"));		
	}	
}
