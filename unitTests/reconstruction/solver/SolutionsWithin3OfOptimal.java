package reconstruction.solver;

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

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class SolutionsWithin3OfOptimal {
		
	private CoevolutionaryHistory actinobacillusActinomycetemcomitansB;
	private final String actinobacillusActinomycetemcomitansBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ActinobacillusActinomycetemcomitans2003b.nex";
	
	private CoevolutionaryHistory actinobacillusActinomycetemcomitansD;
	private final String actinobacillusActinomycetemcomitansDFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ActinobacillusActinomycetemcomitans2003d.nex";
	
	private CoevolutionaryHistory birdsLiceF;
	private final String birdsLiceFFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsLice2004b.nex";	
	
	private CoevolutionaryHistory caryophyllaceaeMicrobotryumB;
	private final String caryophyllaceaeMicrobotryumBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CaryophyllaceaeMicrobotryum2008b.nex";		
	
	private CoevolutionaryHistory estrildaeVidua;
	private final String estrildaeViduaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeVidua2005.nex";		
	
	@Before
	public void LoadActinobacillusActinomycetemcomitansBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansBFileName);
			this.actinobacillusActinomycetemcomitansB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00022"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00019"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00021"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00020"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00016"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00013"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00012"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00017"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00018"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00006"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00015"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00009"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00011"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00008"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00014"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00005"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00002"), 18);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00003"), 19);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00001"), 20);
			nodes.put(this.actinobacillusActinomycetemcomitansB.getHostTree().getNode("InternalNode_00004"), 21);
			this.actinobacillusActinomycetemcomitansB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadActinobacillusActinomycetemcomitansDExample() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansDFileName);
			this.actinobacillusActinomycetemcomitansD = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00009"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00011"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00010"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00012"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00016"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00001"), 10);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00005"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00004"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00002"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00003"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00018"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00008"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00014"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansD.getHostTree().getNode("InternalNode_00015"), 18);
			this.actinobacillusActinomycetemcomitansD.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadBirdLiceFExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceFFileName);
			this.birdsLiceF = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00006"), 8);
			nodes.put(this.birdsLiceF.getHostTree().getNode("InternalNode_00008"), 9);
			this.birdsLiceF.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadcaryophyllaceaeMicrobotryumBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.caryophyllaceaeMicrobotryumBFileName);
			this.caryophyllaceaeMicrobotryumB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00017"), 0);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00015"), 2);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00014"), 3);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00013"), 4);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00006"), 5);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00012"), 6);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00011"), 7);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00009"), 9);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00016"), 11);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00004"), 13);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00001"), 14);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00003"), 15);
			nodes.put(this.caryophyllaceaeMicrobotryumB.getHostTree().getNode("InternalNode_00002"), 16);
			this.caryophyllaceaeMicrobotryumB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadEstrildaeViduaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.estrildaeViduaFileName);
			this.estrildaeVidua = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00032"), 0);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00031"), 1);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00023"), 2);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00022"), 3);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00021"), 4);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00015"), 5);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00014"), 6);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00010"), 7);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00030"), 9);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00009"), 10);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00029"), 11);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00016"), 12);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00004"), 13);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00020"), 14);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00003"), 15);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00007"), 16);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00013"), 17);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00012"), 18);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00024"), 19);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00002"), 20);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00005"), 21);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00018"), 22);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00006"), 23);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00001"), 24);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00011"), 25);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00019"), 26);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00017"), 27);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00028"), 28);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00027"), 29);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00026"), 30);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00025"), 31);
			this.estrildaeVidua.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void ActinobacillusActinomycetemcomitansBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.actinobacillusActinomycetemcomitansB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// 3 away from optimal
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void ActinobacillusActinomycetemcomitansDTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.actinobacillusActinomycetemcomitansD);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// now two away from optimal
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void BirdsLiceFTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceF);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void CaryophyllaceaeMicrobotryumBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.caryophyllaceaeMicrobotryumB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// now one away from optimal
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void EstrildaeViduaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeVidua);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// now one away from optimal
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 13, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
}
