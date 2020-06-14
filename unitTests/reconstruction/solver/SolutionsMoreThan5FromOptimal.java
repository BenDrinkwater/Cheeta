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

public class SolutionsMoreThan5FromOptimal {
	
	private CoevolutionaryHistory hantiviruses;
	private final String hantivirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantaviruses2013.nex";	
	
	private CoevolutionaryHistory leguminosaePsyllidsReduced;
	private final String leguminosaePsyllidsReducedFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "LeguminosaePsyllidsReduced2005.nex";
	
	private CoevolutionaryHistory partitiviridae;
	private final String partitiviridaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Partitiviridae2011.nex";	
	
	@Before
	public void LargeHantiViruses() {
		try {

			NexusFile file = new JaneNexusFile(this.hantivirusesFileName);
			
			this.hantiviruses = file.read();			
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00052"), 0);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00017"), 2);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00050"), 3);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00016"), 4);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00045"), 5);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00036"), 6);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00015"), 7);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00035"), 8);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00044"), 9);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00043"), 10);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00025"), 11);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00034"), 12);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00006"), 13);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00042"), 14);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00031"), 15);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00020"), 16);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00030"), 17);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00014"), 18);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00041"), 19);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00009"), 20);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00049"), 21);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00024"), 22);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00023"), 23);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00013"), 24);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00028"), 25);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00005"), 26);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00022"), 27);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00012"), 28);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00040"), 29);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00011"), 30);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00021"), 31);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00033"), 32);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00019"), 33);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00027"), 34);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00008"), 35);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00007"), 36);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00039"), 37);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00026"), 38);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00018"), 39);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00047"), 40);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00032"), 41);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00010"), 42);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00037"), 43);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00048"), 44);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00038"), 45);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00004"), 46);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00029"), 47);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00046"), 48);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00003"), 49);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00051"), 50);
			nodes.put(this.hantiviruses.getHostTree().getNode("InternalNode_00001"), 51);
			this.hantiviruses.getHostTree().setUniqueNodeTimings(nodes);

		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Before
	public void LoadLeguminosaePsyllidsReducedExample() {
		try {
			NexusFile file = new JaneNexusFile(this.leguminosaePsyllidsReducedFileName);
			this.leguminosaePsyllidsReduced = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00033"), 0);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00025"), 1);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00032"), 2);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00014"), 3);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00010"), 4);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00029"), 5);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00009"), 7);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00027"), 8);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00026"), 9);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00024"), 10);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00023"), 11);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00022"), 12);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00021"), 13);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00028"), 14);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00020"), 15);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00031"), 16);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00018"), 17);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00006"), 18);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00008"), 19);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00013"), 20);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00012"), 21);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00011"), 22);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00019"), 23);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00005"), 24);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00017"), 25);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00004"), 26);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00016"), 27);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00003"), 28);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00015"), 29);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00030"), 30);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00001"), 31);
			nodes.put(this.leguminosaePsyllidsReduced.getHostTree().getNode("InternalNode_00002"), 32);
			this.leguminosaePsyllidsReduced.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPartitiviridaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.partitiviridaeFileName);
			this.partitiviridae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00035"), 0);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00013"), 1);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00034"), 2);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00026"), 3);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00025"), 4);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00027"), 5);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00001"), 8);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00030"), 9);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00012"), 10);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00020"), 11);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00016"), 12);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00017"), 13);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00023"), 14);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00029"), 15);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00032"), 16);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00028"), 17);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00003"), 18);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00002"), 19);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00019"), 20);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00011"), 21);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00018"), 22);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00033"), 23);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00021"), 24);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00031"), 25);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00015"), 26);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00009"), 27);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00010"), 28);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00024"), 29);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00008"), 30);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00007"), 31);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00005"), 32);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00006"), 33);
			nodes.put(this.partitiviridae.getHostTree().getNode("InternalNode_00022"), 34);
			this.partitiviridae.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Test
	public void LargeHantivirusesTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantiviruses);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// 3 away from optimal
		
		assertEquals((Integer) 25, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 28, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void LeguminosaePsyllidsReducedTest() {	
		TreeCollapse treeCollapse = new TreeCollapse(this.leguminosaePsyllidsReduced);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		assertEquals((Integer) 13, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 28, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfLossEvents());
	}	

	@Test
	public void PartitiviridaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.partitiviridae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 13, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 31, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfLossEvents());
	}	
}
