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

public class SolutionsWithin4OfOptimal {

	private CoevolutionaryHistory actinobacillusActinomycetemcomitansA;
	private final String actinobacillusActinomycetemcomitansAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ActinobacillusActinomycetemcomitans2003a.nex";
	
	private CoevolutionaryHistory actinobacillusActinomycetemcomitansF;
	private final String actinobacillusActinomycetemcomitansFFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ActinobacillusActinomycetemcomitans2003f.nex";
	
	private CoevolutionaryHistory jc;
	private final String jcFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "JC2006.nex";
	
	private CoevolutionaryHistory tobamoviruses;
	private final String tobamovirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Tobamoviruses2010.nex";		
	
	private CoevolutionaryHistory totiviridae;
	private final String totiviridaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Totiviridae2011.nex";		
	
	@Before
	public void LoadActinobacillusActinomycetemcomitansAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansAFileName);
			this.actinobacillusActinomycetemcomitansA = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00022"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00016"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00020"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00012"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00019"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00017"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00021"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00018"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00002"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00013"), 10);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00011"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00015"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00010"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00014"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00006"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00001"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00005"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00007"), 18);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00003"), 19);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00004"), 20);
			nodes.put(this.actinobacillusActinomycetemcomitansA.getHostTree().getNode("InternalNode_00008"), 21);
			this.actinobacillusActinomycetemcomitansA.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadActinobacillusActinomycetemcomitansFExample() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansFFileName);
			this.actinobacillusActinomycetemcomitansF = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00011"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00014"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00015"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00017"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00006"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00016"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00012"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00018"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00009"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00010"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00004"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00013"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00003"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00005"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansF.getHostTree().getNode("InternalNode_00002"), 18);
			this.actinobacillusActinomycetemcomitansF.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadJCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.jcFileName);
			
			this.jc = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.jc.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.jc.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.jc.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.jc.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.jc.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.jc.getHostTree().getNode("InternalNode_00004"), 5);
			this.jc.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadTobamovirusesExample() {
		try {
			NexusFile file = new JaneNexusFile(this.tobamovirusesFileName);
			
			this.tobamoviruses = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00007"), 5);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00006"), 6);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00005"), 7);
			nodes.put(this.tobamoviruses.getHostTree().getNode("InternalNode_00004"), 8);
			this.tobamoviruses.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadTotiviridaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.totiviridaeFileName);
			
			this.totiviridae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00024"), 0);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00023"), 1);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00022"), 2);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00020"), 3);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00016"), 4);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00019"), 5);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00018"), 6);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00017"), 7);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00021"), 8);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00015"), 9);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00014"), 10);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00013"), 11);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00012"), 12);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00009"), 13);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00003"), 14);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00008"), 15);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00002"), 16);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00011"), 17);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00006"), 18);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00010"), 19);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00005"), 20);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00004"), 21);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00001"), 22);
			nodes.put(this.totiviridae.getHostTree().getNode("InternalNode_00007"), 23);
			this.totiviridae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Test
	public void ActinobacillusActinomycetemcomitansATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.actinobacillusActinomycetemcomitansA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void ActinobacillusActinomycetemcomitansFTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.actinobacillusActinomycetemcomitansF);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal now
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void JCTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.jc);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void TobamovirusesTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.tobamoviruses);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void TotiviridaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.totiviridae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// 3 away from optimal
		
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}	
}
