package reconstruction.solver.nm;

import static org.junit.Assert.assertTrue;
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
import reconstruction.solver.nm.RestrictedReconstructionGenerator.RestrictionMode;
import tanglegram.CoevolutionaryHistory;
import tree.Node;
import util.reconstruction.RestrictedReconstructionSet;

public class RestrictedReconstructionGeneratorTests {

	private CoevolutionaryHistory testCase;
	private final String testCaseFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "LeguminosaePsyllidsReduced2005.nex";
	
	private void loadLeguminosaePsyllidsReducedExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCaseFileName);
			this.testCase = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00033"), 0);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00025"), 1);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00014"), 3);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00024"), 4);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00010"), 5);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00023"), 7);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00022"), 8);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00021"), 9);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00006"), 10);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00032"), 11);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00013"), 12);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00029"), 13);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00018"), 14);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00028"), 15);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00027"), 16);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00026"), 17);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00005"), 18);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00012"), 19);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00031"), 20);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00017"), 21);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00009"), 22);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00016"), 23);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00008"), 24);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00011"), 25);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00004"), 26);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00015"), 27);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00030"), 28);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00003"), 29);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00001"), 30);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00002"), 31);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00020"), 32);
			nodes.put(this.testCase.getHostTree().getNode("InternalNode_00019"), 33);
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
	public void HybridVSRandom() {
		this.loadLeguminosaePsyllidsReducedExample();
		int[] wins = new int[2];
		for (int i = 0; i < 100; ++i) {
			for (int j = 0; j < 100; ++j) {
				RestrictedReconstructionGenerator reconstructionGenerator1 = new RestrictedReconstructionGenerator(this.testCase.cloneHistory(), new int[] {0, 1, 1, 2}, RestrictionMode.Hybrid, RestrictedReconstructionSet.Size.SquareRoot);
				RestrictedReconstructionGenerator reconstructionGenerator2 = new RestrictedReconstructionGenerator(this.testCase.cloneHistory(), new int[] {0, 1, 1, 2}, RestrictionMode.Random, RestrictedReconstructionSet.Size.SquareRoot);
				
				ParasiteMappingList list1 = reconstructionGenerator1.getParasiteMappingList();
				ParasiteMappingList list2 = reconstructionGenerator2.getParasiteMappingList();
				
				NodeMappingEventCounter counter1 = new NodeMappingEventCounter(this.testCase, list1, new int[] {0, 1, 1, 2});
				NodeMappingEventCounter counter2 = new NodeMappingEventCounter(this.testCase, list2, new int[] {0, 1, 1, 2});
				
				if (62 == counter1.getEventCounts().getCost() && 62 == counter2.getEventCounts().getCost()) {
					wins[0]++;
					wins[1]++;
					break;
				}
				else if (62 == counter1.getEventCounts().getCost()) {
					wins[0]++;
					break;
				}
				else if (62 == counter2.getEventCounts().getCost()) {
					wins[1]++;
					break;
				}
			}
		}
		assertTrue(wins[0] < wins[1]);		
	}
}
