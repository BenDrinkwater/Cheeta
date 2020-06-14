package reconstruction.solver.nm;

import static org.junit.Assert.assertNotNull;
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

import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class TestExample {

	private CoevolutionaryHistory mostSimpleCase;
	private final String mostSimpleCaseFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "testHistories" + File.separator + "ReconstructionTest17.nex";	
	
	@Before
	public void LoadMostSimpleCaseExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.mostSimpleCaseFileName);
			
			this.mostSimpleCase = file.read();
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.mostSimpleCase.getHostTree().getNode("InternalNode_00001"), 0);
			this.mostSimpleCase.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	

	@Test
	public void CodivergencePromotionSolutionTest() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.mostSimpleCase, new int[] {0, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.mostSimpleCase, list, new int[] {0, 1, 1, 2});
		
		assertEquals(0, counter.getEventCounts().getCost());
	}	
	
	@Test
	public void DuplicationPromotionSolutionTest() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.mostSimpleCase, new int[] {10, 1, 1, 10});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.mostSimpleCase, list, new int[] {10, 1, 1, 10});
		
		assertEquals((Integer) 1, counter.getEventCounts().getNumberOfDuplicationEvents());
		assertEquals(3, counter.getEventCounts().getCost());
	}
	
	@Test
	public void SwitchPromotionSolutionTest() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.mostSimpleCase, new int[] {10, 1, 1, 2});
		assertNotNull(reconstructionGenerator);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		NodeMappingEventCounter counter = new NodeMappingEventCounter(this.mostSimpleCase, list, new int[] {10, 1, 1, 2});
		
		assertEquals(2, counter.getEventCounts().getCost());
	}
}
