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
import reconstruction.solver.tc.TreeCollapseEventCounter;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class TenHardRealDataTestCases {

	private CoevolutionaryHistory testCase;
	private final String testCaseFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantaviruses2013.nex";
	
	private void loadTestCase() {
		try {
			NexusFile file = new JaneNexusFile(this.testCaseFileName);
			this.testCase = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00052"), 0);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00051"), 1);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00050"), 2);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00017"), 3);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00045"), 4);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00036"), 5);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00025"), 6);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00016"), 7);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00015"), 8);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00014"), 9);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00006"), 10);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00020"), 11);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00044"), 12);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00031"), 13);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00037"), 14);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00049"), 15);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00043"), 16);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00030"), 17);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00042"), 18);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00028"), 19);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00035"), 20);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00005"), 21);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00009"), 22);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00041"), 23);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00024"), 24);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00029"), 25);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00019"), 26);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00008"), 27);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00034"), 28);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00027"), 29);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00023"), 30);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00013"), 31);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00012"), 32);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00033"), 33);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00004"), 34);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00007"), 35);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00040"), 36);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00011"), 37);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00026"), 38);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00032"), 39);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00047"), 40);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00046"), 41);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00039"), 42);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00038"), 43);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00048"), 44);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00022"), 45);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00021"), 46);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00018"), 47);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00010"), 48);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00003"), 49);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00002"), 50);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00001"), 51);
			this.testCase.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Test
	public void TestCase1() {
		this.loadTestCase();
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.testCase, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		TreeCollapseEventCounter counter = new TreeCollapseEventCounter(this.testCase, list);
		assertEquals((Integer) 25, counter.getEventCounts().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals((Integer) 28, counter.getEventCounts().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 7, counter.getEventCounts().getNumberOfLossEvents());
	}
}
