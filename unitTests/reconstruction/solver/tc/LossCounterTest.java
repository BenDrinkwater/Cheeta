package reconstruction.solver.tc;

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
import reconstruction.ParasiteMappingList;
import reconstruction.solver.TreeCollapse;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class LossCounterTest {

	private CoevolutionaryHistory gammaHerpesvirusLCVNodeOrder1;
	private CoevolutionaryHistory gammaHerpesvirusLCVNodeOrder2;
	private CoevolutionaryHistory gammaHerpesvirusLCVNodeOrder3;
	private final String gammaHerpesvirusLCVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "GammaHerpesvirusLCV2005.nex";	
	
	
	@Before
	public void LoadGammaHerpesvirusNodeOrder1Example() {	
		try {
			NexusFile file = new JaneNexusFile(this.gammaHerpesvirusLCVFileName);
			this.gammaHerpesvirusLCVNodeOrder1 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00017"), 0);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00016"), 1);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00011"), 2);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00015"), 3);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00013"), 5);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00012"), 6); 
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00010"), 7);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00008"), 9);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00005"), 10);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00007"), 11);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00006"), 12);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00003"), 13);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00004"), 14);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00002"), 15); 
			nodes.put(this.gammaHerpesvirusLCVNodeOrder1.getHostTree().getNode("InternalNode_00001"), 16);
			this.gammaHerpesvirusLCVNodeOrder1.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadGammaHerpesvirusNodeOrder2Example() {	
		try {
			NexusFile file = new JaneNexusFile(this.gammaHerpesvirusLCVFileName);
			this.gammaHerpesvirusLCVNodeOrder2 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00017"), 0);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00016"), 1);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00011"), 2);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00015"), 3);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00013"), 5);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00012"), 6); 
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00010"), 7);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00003"), 9);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00004"), 10);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00008"), 11);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00007"), 12);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00006"), 13);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00009"), 14);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00002"), 15); 
			nodes.put(this.gammaHerpesvirusLCVNodeOrder2.getHostTree().getNode("InternalNode_00001"), 16);
			this.gammaHerpesvirusLCVNodeOrder2.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadGammaHerpesvirusNodeOrder3Example() {	
		try {
			NexusFile file = new JaneNexusFile(this.gammaHerpesvirusLCVFileName);
			this.gammaHerpesvirusLCVNodeOrder3 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00017"), 0);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00016"), 1);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00011"), 2);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00015"), 3);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00013"), 5);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00012"), 6); 
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00005"), 7);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00003"), 8);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00004"), 9);			
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00010"), 10);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00009"), 11);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00007"), 13);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00006"), 14);
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00002"), 15); 
			nodes.put(this.gammaHerpesvirusLCVNodeOrder3.getHostTree().getNode("InternalNode_00001"), 16);
			this.gammaHerpesvirusLCVNodeOrder3.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void GammaHerpesvirusLCVNodeOrder1Test() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.gammaHerpesvirusLCVNodeOrder1);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		LossCounter losscounter = new LossCounter(this.gammaHerpesvirusLCVNodeOrder1, parasiteMapping);
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.gammaHerpesvirusLCVNodeOrder1, parasiteMapping);
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(gammaHerpesvirusLCVNodeOrder1.getHostTree(), losscounter.hostMapping, parasiteMapping, eventCounter.getEventCounts());	
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void GammaHerpesvirusLCVNodeOrder2Test() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.gammaHerpesvirusLCVNodeOrder2);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		LossCounter losscounter = new LossCounter(this.gammaHerpesvirusLCVNodeOrder2, parasiteMapping);
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.gammaHerpesvirusLCVNodeOrder2, parasiteMapping);
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(gammaHerpesvirusLCVNodeOrder2.getHostTree(), losscounter.hostMapping, parasiteMapping, eventCounter.getEventCounts());	
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void GammaHerpesvirusLCVNodeOrder3Test() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.gammaHerpesvirusLCVNodeOrder3);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		LossCounter losscounter = new LossCounter(this.gammaHerpesvirusLCVNodeOrder3, parasiteMapping);
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.gammaHerpesvirusLCVNodeOrder3, parasiteMapping);
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(gammaHerpesvirusLCVNodeOrder3.getHostTree(), losscounter.hostMapping, parasiteMapping, eventCounter.getEventCounts());	
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void GammaHerpesvirusLCVNodeOrder1InTreeCollapseTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.gammaHerpesvirusLCVNodeOrder1);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void GammaHerpesvirusLCVNodeOrder2InTreeCollapseTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.gammaHerpesvirusLCVNodeOrder2);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void GammaHerpesvirusLCVNodeOrder3InTreeCollapseTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.gammaHerpesvirusLCVNodeOrder3);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
	}		
}
