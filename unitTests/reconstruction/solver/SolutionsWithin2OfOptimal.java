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

public class SolutionsWithin2OfOptimal {

	private CoevolutionaryHistory actinobacillusActinomycetemcomitansC;
	private final String actinobacillusActinomycetemcomitansCFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ActinobacillusActinomycetemcomitans2003c.nex";
	
	private CoevolutionaryHistory caryophyllaceaeMicrobotryumA;
	private final String caryophyllaceaeMicrobotryumAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CaryophyllaceaeMicrobotryum2008a.nex";	
		
	private CoevolutionaryHistory figWaspE;
	private final String figWaspEFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004e.nex";			

	private CoevolutionaryHistory ficusPegoscapus;
	private final String ficusPegoscapusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "FicusPegoscapus2007.nex";
	
	private CoevolutionaryHistory steinernemaXenorhabdus;
	private final String steinernemaXenorhabdusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "SteinernemaXenorhabdus2010.nex";		
	
	@Before
	public void LoadActinobacillusActinomycetemcomitansCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansCFileName);
			this.actinobacillusActinomycetemcomitansC = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00012"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00018"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00006"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00010"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00009"), 10);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00005"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00002"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00011"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00004"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00003"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00016"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00015"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansC.getHostTree().getNode("InternalNode_00014"), 18);
			this.actinobacillusActinomycetemcomitansC.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadCaryophyllaceaeMicrobotryumAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.caryophyllaceaeMicrobotryumAFileName);
			this.caryophyllaceaeMicrobotryumA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00018"), 1);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00012"), 2);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00016"), 3);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00011"), 4);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00015"), 5);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00008"), 6);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00013"), 8);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00007"), 9);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00017"), 10);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00002"), 11);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00004"), 12);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00001"), 13);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00006"), 14);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00010"), 15);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00009"), 16);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00005"), 17);
			nodes.put(this.caryophyllaceaeMicrobotryumA.getHostTree().getNode("InternalNode_00003"), 18);
			this.caryophyllaceaeMicrobotryumA.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadFigWaspEExample() {
		try {
			NexusFile file = new JaneNexusFile(this.figWaspEFileName);
			this.figWaspE = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00012"), 0);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00011"), 1);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00009"), 3);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00008"), 5);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00006"), 6);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00004"), 9);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00003"), 10);
			nodes.put(this.figWaspE.getHostTree().getNode("InternalNode_00002"), 11);
			this.figWaspE.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadFicusPegoscapusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.ficusPegoscapusFileName);
			this.ficusPegoscapus = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00010"), 6);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00009"), 7);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00007"), 9);
			nodes.put(this.ficusPegoscapus.getHostTree().getNode("InternalNode_00005"), 10);
			this.ficusPegoscapus.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadSteinernemaXenorhabdusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.steinernemaXenorhabdusFileName);
			this.steinernemaXenorhabdus = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00029"), 0);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00003"), 1);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00028"), 2);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00026"), 4);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00010"), 5);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00025"), 6);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00024"), 8);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00020"), 9);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00017"), 10);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00012"), 11);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00016"), 12);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00015"), 13);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00007"), 14);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00013"), 15);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00023"), 16);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00006"), 17);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00014"), 18);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00004"), 19);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00005"), 20);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00019"), 21);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00022"), 22);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00021"), 23);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00009"), 24);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00008"), 25);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00011"), 26);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00027"), 27);
			nodes.put(this.steinernemaXenorhabdus.getHostTree().getNode("InternalNode_00018"), 28);
			this.steinernemaXenorhabdus.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void ActinobacillusActinomycetemcomitansCTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.actinobacillusActinomycetemcomitansC);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal now
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void CaryophyllaceaeMicrobotryumATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.caryophyllaceaeMicrobotryumA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal now
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 16, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void FigWaspTestD() {
		TreeCollapse treeCollapse = new TreeCollapse(this.figWaspE);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void FicusPegoscapusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.ficusPegoscapus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void SteinernemaXenorhabdusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.steinernemaXenorhabdus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 19, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
}
