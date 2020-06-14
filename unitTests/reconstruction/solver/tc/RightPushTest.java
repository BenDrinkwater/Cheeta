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
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class RightPushTest {

	private CoevolutionaryHistory actinobacillusActinomycetemcomitansDNodeOrder1;
	private CoevolutionaryHistory actinobacillusActinomycetemcomitansDNodeOrder2;
	private final String actinobacillusActinomycetemcomitansDFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ActinobacillusActinomycetemcomitans2003d.nex";
	
	private CoevolutionaryHistory avianMLV;
	private final String avianMLVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "AvianMLV2003.nex";
	
	private CoevolutionaryHistory caliciviruses;
	private final String calicivirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Caliciviruses2006.nex";	
	
	private CoevolutionaryHistory gammaHerpesvirusLCVNodeOrder1;
	private CoevolutionaryHistory gammaHerpesvirusLCVNodeOrder2;
	private CoevolutionaryHistory gammaHerpesvirusLCVNodeOrder3;
	private final String gammaHerpesvirusLCVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "GammaHerpesvirusLCV2005.nex";	
	
	private CoevolutionaryHistory narnavirdae;
	private final String narnavirdaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Narnavirdae2011.nex";
		
	private CoevolutionaryHistory seabirdLouseB;
	private final String seabirdLouseBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Seabird2001b.nex";		
	
	private CoevolutionaryHistory uP1bUPII;
	private final String uP1bUPIIFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "UP1bUPII2006.nex";

	@Before
	public void LoadActinobacillusActinomycetemcomitansDNodeOrder1Example() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansDFileName);
			
			
			
			this.actinobacillusActinomycetemcomitansDNodeOrder1 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00018"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00012"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00011"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00009"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00016"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00008"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00015"), 10);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00006"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00002"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00004"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00003"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00010"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00001"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().getNode("InternalNode_00014"), 18);
			this.actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadActinobacillusActinomycetemcomitansDNodeOrder2Example() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansDFileName);
			
			
			
			this.actinobacillusActinomycetemcomitansDNodeOrder2 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00018"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00012"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00011"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00009"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00016"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00015"), 10);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00006"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00002"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00004"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00003"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00010"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00001"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().getNode("InternalNode_00014"), 18);
			this.actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadAvianMLVExample() {
		try {
			NexusFile file = new JaneNexusFile(this.avianMLVFileName);
			
			
			
			this.avianMLV = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00013"), 0);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00012"), 2);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00009"), 3);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00011"), 4);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00001"), 9);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00006"), 11);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00005"), 12);
			this.avianMLV.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadCalicivirusesExample() {
		try {
			NexusFile file = new JaneNexusFile(this.calicivirusesFileName);
			
			
			
			this.caliciviruses = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00003"), 5);
			this.caliciviruses.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
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
	
	@Before
	public void NarnavirdaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.narnavirdaeFileName);
			
			
			
			this.narnavirdae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00001"), 4);
			this.narnavirdae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadSeabirdLouseBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.seabirdLouseBFileName);
			
			
			
			this.seabirdLouseB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00003"), 1);			
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00001"), 3);
			this.seabirdLouseB.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadUP1bUPIIExample() {
		try {
			NexusFile file = new JaneNexusFile(this.uP1bUPIIFileName);
			
			
			
			this.uP1bUPII = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00001"), 7);
			this.uP1bUPII.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Test
	public void RightPushBringStartingPointForwardTest1() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.caliciviruses);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.caliciviruses, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.caliciviruses, rightPush.getNewParasiteMapping());
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.caliciviruses, rightPush.getNewParasiteMapping());
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(caliciviruses.getHostTree(), losscounter.hostMapping, rightPush.getNewParasiteMapping(), eventCounter.getEventCounts());	

		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void RightPushBringStartingPointForwardTest2() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.seabirdLouseB);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.seabirdLouseB, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.seabirdLouseB, rightPush.getNewParasiteMapping());
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.seabirdLouseB, rightPush.getNewParasiteMapping());
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(seabirdLouseB.getHostTree(), losscounter.hostMapping, rightPush.getNewParasiteMapping(), eventCounter.getEventCounts());	

		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());	
	}		
	
	@Test
	public void RightPushBringLandingPointForwardTest1() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.avianMLV);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.avianMLV, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.avianMLV, rightPush.getNewParasiteMapping());
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.avianMLV, rightPush.getNewParasiteMapping());
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(avianMLV.getHostTree(), losscounter.hostMapping, rightPush.getNewParasiteMapping(), eventCounter.getEventCounts());	

		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void RightPushBringLandingPointForwardTest2() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.uP1bUPII);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.uP1bUPII, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.uP1bUPII, rightPush.getNewParasiteMapping());
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.uP1bUPII, rightPush.getNewParasiteMapping());
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(uP1bUPII.getHostTree(), losscounter.hostMapping, rightPush.getNewParasiteMapping(), eventCounter.getEventCounts());	

		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void RightPushShouldNotBringStartingPointForwardTest1() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.gammaHerpesvirusLCVNodeOrder3);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.gammaHerpesvirusLCVNodeOrder3, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.gammaHerpesvirusLCVNodeOrder3, rightPush.getNewParasiteMapping());
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.gammaHerpesvirusLCVNodeOrder3, rightPush.getNewParasiteMapping());
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(gammaHerpesvirusLCVNodeOrder3.getHostTree(), losscounter.hostMapping, rightPush.getNewParasiteMapping(), eventCounter.getEventCounts());	

		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());	
	}	
	
	@Test
	public void RightPushShouldNotBringStartingPointForwardTest2() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.gammaHerpesvirusLCVNodeOrder2);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.gammaHerpesvirusLCVNodeOrder2, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.gammaHerpesvirusLCVNodeOrder2, rightPush.getNewParasiteMapping());
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.gammaHerpesvirusLCVNodeOrder2, rightPush.getNewParasiteMapping());
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(gammaHerpesvirusLCVNodeOrder2.getHostTree(), losscounter.hostMapping, rightPush.getNewParasiteMapping(), eventCounter.getEventCounts());	

		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void RightPushShouldNotBringStartingPointForwardTest3() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.actinobacillusActinomycetemcomitansDNodeOrder1);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.actinobacillusActinomycetemcomitansDNodeOrder1, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.actinobacillusActinomycetemcomitansDNodeOrder1, parasiteMapping);
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.actinobacillusActinomycetemcomitansDNodeOrder1, parasiteMapping);
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(actinobacillusActinomycetemcomitansDNodeOrder1.getHostTree(), losscounter.hostMapping, parasiteMapping, eventCounter.getEventCounts());	
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfLossEvents());	
	}		
	
	@Test
	public void RightPushShouldNotBringStartingPointForwardTest4() {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(this.actinobacillusActinomycetemcomitansDNodeOrder2);
		ParasiteMappingList parasiteMapping = reconstructionGenerator.generatePartialReconstruction();
		
		RightPush rightPush = new RightPush(this.actinobacillusActinomycetemcomitansDNodeOrder2, parasiteMapping);
		rightPush.pushHostSwitchesToTheRight();
		
		LossCounter losscounter = new LossCounter(this.actinobacillusActinomycetemcomitansDNodeOrder1, parasiteMapping);
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(this.actinobacillusActinomycetemcomitansDNodeOrder2, parasiteMapping);
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(actinobacillusActinomycetemcomitansDNodeOrder2.getHostTree(), losscounter.hostMapping, parasiteMapping, eventCounter.getEventCounts());	
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfLossEvents());	
	}		
}
