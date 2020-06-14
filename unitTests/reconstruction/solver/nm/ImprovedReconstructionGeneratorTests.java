package reconstruction.solver.nm;

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
import org.junit.Test;

import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.RootedBifurcatingTree;

public class ImprovedReconstructionGeneratorTests {
	
	private CoevolutionaryHistory baseTestCase1;
	private final String baseTestCase1FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "synthetic" + File.separator + "AllFourEvents.nex";	
	
	private CoevolutionaryHistory testCase1;
	private final String testCase1FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	
		
	private CoevolutionaryHistory testCase2;
	private final String testCase2FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "SeabirdLouse1999.nex";	
		
	private CoevolutionaryHistory testCase3;
	private final String testCase3FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004c.nex";			
		
	private CoevolutionaryHistory testCase4;
	private final String testCase4FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CaryophyllaceaeMicrobotryum2008a.nex";			
		
	private CoevolutionaryHistory testCase5;
	private final String testCase5FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PytiliaVidua1994.nex";		
	
	private CoevolutionaryHistory testCase6;
	private final String testCase6FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "LeguminosaePsyllidsReduced2005.nex";
	
	private void loadBaseCaseTestExample() {
		try {
			NexusFile file = new JaneNexusFile(this.baseTestCase1FileName);
			this.baseTestCase1 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();	
			nodes.put(this.baseTestCase1.getHostTree().getNode("InternalNode_00002"), 0);
			nodes.put(this.baseTestCase1.getHostTree().getNode("InternalNode_00001"), 1);		
			this.baseTestCase1.getHostTree().setUniqueNodeTimings(nodes);	
			((RootedBifurcatingTree) this.baseTestCase1.getHostTree()).cacheValues();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	private void loadPocketGopherLouseExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase1FileName);
			this.testCase1 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();	
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00002"), 6);			
			this.testCase1.getHostTree().setUniqueNodeTimings(nodes);	
			((RootedBifurcatingTree) this.testCase1.getHostTree()).cacheValues();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	private void loadSeabirdLouseLargeAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase2FileName);
			this.testCase2 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00006"), 7);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00004"), 9);
			this.testCase2.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	private void loadFigWaspCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase3FileName);
			this.testCase3 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00014"), 9);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00011"), 10);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00008"), 11);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00007"), 12);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00001"), 13);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00013"), 14);
			this.testCase3.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	private void loadTestCase4() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase4FileName);
			this.testCase4 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00018"), 1);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00017"), 2);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00016"), 3);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00015"), 4);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00006"), 5);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00014"), 6);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00009"), 7);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00013"), 8);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00008"), 9);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00002"), 11);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00001"), 12);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00012"), 13);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00005"), 14);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00004"), 15);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00010"), 16);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00003"), 17);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00011"), 18);
			this.testCase4.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	private void loadPytiliaViduaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase5FileName);
			this.testCase5 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase5.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase5.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.testCase5.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.testCase5.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.testCase5.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.testCase5.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.testCase5.getHostTree().getNode("InternalNode_00003"), 6);
			this.testCase5.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	private void loadLeguminosaePsyllidsReducedExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase6FileName);
			this.testCase6 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00033"), 0);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00025"), 1);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00014"), 3);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00024"), 4);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00010"), 5);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00023"), 7);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00022"), 8);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00021"), 9);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00006"), 10);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00032"), 11);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00013"), 12);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00029"), 13);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00018"), 14);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00028"), 15);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00027"), 16);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00026"), 17);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00005"), 18);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00012"), 19);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00031"), 20);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00017"), 21);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00009"), 22);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00016"), 23);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00008"), 24);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00011"), 25);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00004"), 26);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00015"), 27);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00030"), 28);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00003"), 29);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00001"), 30);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00002"), 31);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00020"), 32);
			nodes.put(this.testCase6.getHostTree().getNode("InternalNode_00019"), 33);
			this.testCase6.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Test
	public void BaseTestCase() {
		this.loadBaseCaseTestExample();
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(this.baseTestCase1, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();		
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.baseTestCase1, list);
		
		assertEquals((Integer) 2, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfLossEvents());
	}
	
	@Test
	public void TestCase1() {
		this.loadPocketGopherLouseExample();
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(this.testCase1, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();		
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.testCase1, list);
		
		assertEquals((Integer) 7, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, counter.getEventCounts().getNumberOfLossEvents());
	}
	
	@Test
	public void TestCase2() {
		this.loadSeabirdLouseLargeAExample();
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(this.testCase2, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.testCase2, list);
		
		assertEquals((Integer) 9, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, counter.getEventCounts().getNumberOfLossEvents());
	}
	
	@Test
	public void TestCase3() {
		this.loadFigWaspCExample();
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(this.testCase3, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.testCase3, list);
		
		assertEquals((Integer) 10, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, counter.getEventCounts().getNumberOfLossEvents());
	}	
	
	@Test
	public void TestCase4() {
		this.loadTestCase4();
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(this.testCase4, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.testCase4, list);
		
		assertEquals((Integer) 6, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 16, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfLossEvents());
	}	
	
	@Test
	public void TestCase5() {
		this.loadPytiliaViduaExample();
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(this.testCase5, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.testCase5, list);
		
		assertEquals((Integer) 5, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, counter.getEventCounts().getNumberOfLossEvents());
	}	

	@Test
	public void TestCase6() {
		this.loadLeguminosaePsyllidsReducedExample();
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(this.testCase6, new int[] {0, 1, 1, 2});
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.testCase6, list);
				
		assertEquals((Integer) 14, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 5, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 26, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, counter.getEventCounts().getNumberOfLossEvents());
	}	
}
