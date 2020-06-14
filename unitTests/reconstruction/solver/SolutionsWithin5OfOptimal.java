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

public class SolutionsWithin5OfOptimal {

	private CoevolutionaryHistory birdsLiceB;
	private final String birdsLiceBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsLouse2003a.nex";			

	private CoevolutionaryHistory estrildaeVidua;
	private final String estrildaeViduaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeVidua2004.nex";		
	
	private CoevolutionaryHistory leguminosaePsyllids;
	private final String leguminosaePsyllidsFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "LeguminosaePsyllids2005.nex";
		
	@Before
	public void LoadBirdLiceBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceBFileName);
			this.birdsLiceB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00021"), 0);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00020"), 1);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00019"), 2);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00006"), 5);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00012"), 6);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00005"), 7);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00010"), 8);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00003"), 9);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00018"), 10);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00008"), 11);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00017"), 12);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00013"), 13);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00007"), 14);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00015"), 15);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00009"), 16);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00004"), 17);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00016"), 18);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00002"), 19);
			nodes.put(this.birdsLiceB.getHostTree().getNode("InternalNode_00011"), 20);
			this.birdsLiceB.getHostTree().setUniqueNodeTimings(nodes);
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
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00030"), 5);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00015"), 6);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00029"), 8);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00010"), 9);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00009"), 10);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00020"), 11);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00004"), 13);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00013"), 14);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00028"), 15);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00007"), 16);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00011"), 17);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00027"), 18);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00012"), 19);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00026"), 20);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00016"), 21);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00002"), 22);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00019"), 23);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00006"), 24);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00005"), 25);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00025"), 26);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00018"), 27);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00024"), 28);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00017"), 29);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00001"), 30);
			nodes.put(this.estrildaeVidua.getHostTree().getNode("InternalNode_00003"), 31);
			this.estrildaeVidua.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadLeguminosaePsyllidsExample() {
		try {
			NexusFile file = new JaneNexusFile(this.leguminosaePsyllidsFileName);
			this.leguminosaePsyllids = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00035"), 0);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00034"), 1);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00023"), 2);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00031"), 3);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00029"), 4);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00033"), 5);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00013"), 6);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00027"), 7);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00024"), 8);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00010"), 9);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00012"), 10);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00009"), 11);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00011"), 12);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00008"), 13);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00025"), 14);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00007"), 15);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00019"), 16);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00030"), 17);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00005"), 18);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00028"), 19);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00017"), 20);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00014"), 21);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00022"), 22);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00020"), 23);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00026"), 24);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00032"), 25);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00006"), 26);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00004"), 27);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00003"), 28);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00002"), 29);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00021"), 30);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00001"), 31);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00018"), 32);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00015"), 33);
			nodes.put(this.leguminosaePsyllids.getHostTree().getNode("InternalNode_00016"), 34);
			this.leguminosaePsyllids.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void BirdsLiceBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// 2 away from optimal
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 13, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	
	@Test
	public void EstrildaeViduaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeVidua);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// 3 away from optimal
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 16, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void LeguminosaePsyllidsTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.leguminosaePsyllids);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// 3 away from optimal
		
		assertEquals((Integer) 16, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 29, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
}
