package reconstruction.solver.tc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tanglegram.CoevolutionaryHistory;
import tree.LeafNodePair;
import tree.Node;

public class TreeShapeDetectorTest {
	
	private CoevolutionaryHistory parasiteCircleExampleHistory;
	private CoevolutionaryHistory strongLinkExample;
	private CoevolutionaryHistory switchPathExample;
	@SuppressWarnings("unused")
	private CoevolutionaryHistory dualSwitchPathExample1;
	private CoevolutionaryHistory dualSwitchPathExample2;
	@SuppressWarnings("unused")
	private CoevolutionaryHistory dualSwitchPathExample3;
	private CoevolutionaryHistory duplicationLossOrSwitchParadigm;
	private CoevolutionaryHistory doubleHostSwitchExample;
	private CoevolutionaryHistory duplicationSwitch;
	@SuppressWarnings("unused")
	private CoevolutionaryHistory dualSwitchPathExample4;
	
	private final String parasiteCircleExampleFileName = "unitTestFiles" + File.separator + "reconstruction" +  File.separator + "solver" +  File.separator + "tc"  +  File.separator +  "ParasiteCircle.nex";
	private final String strongLinkExampleFileName = "unitTestFiles" + File.separator + "reconstruction" +  File.separator + "solver" +  File.separator + "tc"  +  File.separator +  "StrongLink.nex";
	private final String switchPathExampleFileName = "unitTestFiles" + File.separator + "reconstruction" +  File.separator + "solver" +  File.separator + "tc"  +  File.separator +  "SwitchPath.nex";
	private final String dualSwitchPathExample1FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest5.nex";
	private final String dualSwitchPathExample2FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest6.nex";
	private final String dualSwitchPathExample3FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest8.nex";
	private final String duplicationLossOrSwitchParadigmFileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest7.nex";
	private final String doubleHostSwitchFilename = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest9.nex";
	private final String duplicationSwitchFilename = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest10.nex";
	private final String dualSwitchPathExample4FileName = "unitTestFiles" +  File.separator + "reconstruction" +  File.separator +  "testHistories" +  File.separator + "ReconstructionTest12.nex";
	
	@Before
	public void LoadParasiteCircle() {
		try {
			NexusFile file = new JaneNexusFile(this.parasiteCircleExampleFileName);
			
			
			this.parasiteCircleExampleHistory = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Before
	public void LoadStrongLink() {
		try {
			NexusFile file = new JaneNexusFile(this.strongLinkExampleFileName);
			
			
			this.strongLinkExample = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadSwitchPath() {
		try {
			NexusFile file = new JaneNexusFile(this.switchPathExampleFileName);
			
			
			this.switchPathExample = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadDualSwitchPathExample1() {
		try {
			NexusFile file = new JaneNexusFile(this.dualSwitchPathExample1FileName);
			
			
			this.dualSwitchPathExample1 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadDualSwitchPathExample2() {
		try {
			NexusFile file = new JaneNexusFile(this.dualSwitchPathExample2FileName);
			
			
			this.dualSwitchPathExample2 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadDualSwitchPathExample3() {
		try {
			NexusFile file = new JaneNexusFile(this.dualSwitchPathExample3FileName);
			
			
			this.dualSwitchPathExample3 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadDualSwitchPathExample4() {
		try {
			NexusFile file = new JaneNexusFile(this.dualSwitchPathExample4FileName);
			
			
			this.dualSwitchPathExample4 = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadDuplicationLossOrSwitchParadigm() {
		try {
			NexusFile file = new JaneNexusFile(this.duplicationLossOrSwitchParadigmFileName);
			
			
			this.duplicationLossOrSwitchParadigm = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadDoubleHostSwitchFilename() {
		try {
			NexusFile file = new JaneNexusFile(this.doubleHostSwitchFilename);
			
			
			this.doubleHostSwitchExample = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadDuplicationSwitchFilename() {
		try {
			NexusFile file = new JaneNexusFile(this.duplicationSwitchFilename);
			
			
			this.duplicationSwitch = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void ParasiteCircleExampleTest() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.parasiteCircleExampleHistory); 
		assertEquals(false, treeShapeDetector.hasParasiteCircle((Node) null));
		assertEquals(true, treeShapeDetector.hasParasiteCircle(this.parasiteCircleExampleHistory.getHostTree().getNode("1")));
		assertEquals(false, treeShapeDetector.hasParasiteCircle(this.parasiteCircleExampleHistory.getHostTree().getNode("2")));
	}
	
	@Test
	public void StronglyLinkedExampleTest() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.strongLinkExample);
		assertEquals(false, treeShapeDetector.hasStrongLink(null, null));
		assertEquals(true, treeShapeDetector.hasStrongLink(this.strongLinkExample.getHostTree().getNode("3"), this.strongLinkExample.getHostTree().getNode("4")));
		assertEquals(true, treeShapeDetector.hasStrongLink(this.strongLinkExample.getHostTree().getNode("4"), this.strongLinkExample.getHostTree().getNode("3")));	
		assertEquals(true, treeShapeDetector.hasStrongLink(this.strongLinkExample.getHostTree().getNode("5"), this.strongLinkExample.getHostTree().getNode("6")));
		assertEquals(true, treeShapeDetector.hasStrongLink(this.strongLinkExample.getHostTree().getNode("6"), this.strongLinkExample.getHostTree().getNode("5")));	
	}
	
	@Test
	public void SwitchingPathExampleTest() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.switchPathExample);
		assertEquals(false, treeShapeDetector.hasSwitchPath(null, null));
		assertEquals(false, treeShapeDetector.hasSwitchPath(this.switchPathExample.getHostTree().getNode("3"), this.switchPathExample.getHostTree().getNode("4")));
		assertEquals(true, treeShapeDetector.hasSwitchPath(this.switchPathExample.getHostTree().getNode("4"), this.switchPathExample.getHostTree().getNode("3")));			
	}
	
	@Test
	public void ParasiteCircleShapeFeaturesTest() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.parasiteCircleExampleHistory); 
		LeafNodePair leafNodePair = new LeafNodePair(this.parasiteCircleExampleHistory.getHostTree().getNode("1"), this.parasiteCircleExampleHistory.getHostTree().getNode("2"));
		assertEquals(true, treeShapeDetector.hasParasiteCircle(leafNodePair));
		assertNotNull(treeShapeDetector.getShapeSpecificFeatures());
		assertEquals(3, treeShapeDetector.getShapeSpecificFeatures().size());
		assertEquals("parasite_7", treeShapeDetector.getShapeSpecificFeatures().get(0).toString());
	}
	
	@Test
	public void StrongLinkShapeFeaturesTest1() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.strongLinkExample); 
		LeafNodePair leafNodePair = new LeafNodePair(this.strongLinkExample.getHostTree().getNode("3"), this.strongLinkExample.getHostTree().getNode("4"));
		assertEquals(true, treeShapeDetector.hasStrongLink(leafNodePair));
		assertNotNull(treeShapeDetector.getShapeSpecificFeatures());
		assertEquals(3, treeShapeDetector.getShapeSpecificFeatures().size());
		assertEquals("parasite_11", treeShapeDetector.getShapeSpecificFeatures().get(0).toString());		
	}
	
	@Test
	public void StrongLinkShapeFeaturesTest2() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.strongLinkExample); 
		LeafNodePair leafNodePair = new LeafNodePair(this.strongLinkExample.getHostTree().getNode("4"), this.strongLinkExample.getHostTree().getNode("3"));
		assertEquals(true, treeShapeDetector.hasStrongLink(leafNodePair));
		assertNotNull(treeShapeDetector.getShapeSpecificFeatures());
		assertEquals(3, treeShapeDetector.getShapeSpecificFeatures().size());
		assertEquals("parasite_10", treeShapeDetector.getShapeSpecificFeatures().get(0).toString());		
	}
	
	@Test
	public void StrongLinkShapeFeaturesTest3() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.strongLinkExample); 
		LeafNodePair leafNodePair = new LeafNodePair(this.strongLinkExample.getHostTree().getNode("5"), this.strongLinkExample.getHostTree().getNode("6"));
		assertEquals(true, treeShapeDetector.hasStrongLink(leafNodePair));
		assertNotNull(treeShapeDetector.getShapeSpecificFeatures());
		assertEquals(3, treeShapeDetector.getShapeSpecificFeatures().size());
		assertEquals("parasite_13", treeShapeDetector.getShapeSpecificFeatures().get(0).toString());		
	}
	
	@Test
	public void StrongLinkShapeFeaturesTest4() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.strongLinkExample); 
		LeafNodePair leafNodePair = new LeafNodePair(this.strongLinkExample.getHostTree().getNode("6"), this.strongLinkExample.getHostTree().getNode("5"));
		assertEquals(true, treeShapeDetector.hasStrongLink(leafNodePair));
		assertNotNull(treeShapeDetector.getShapeSpecificFeatures());
		assertEquals(3, treeShapeDetector.getShapeSpecificFeatures().size());
		assertEquals("parasite_12", treeShapeDetector.getShapeSpecificFeatures().get(0).toString());		
	}	
	
	@Test
	public void SwitchingPathShapeFeaturesTest1() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.switchPathExample);
		LeafNodePair leafNodePair = new LeafNodePair(this.switchPathExample.getHostTree().getNode("3"), this.switchPathExample.getHostTree().getNode("4"));
		assertEquals(true, treeShapeDetector.hasSwitchPath(leafNodePair));
		assertNotNull(treeShapeDetector.getShapeSpecificFeatures());
		assertEquals(3, treeShapeDetector.getShapeSpecificFeatures().size());
		assertEquals("parasite_8", treeShapeDetector.getShapeSpecificFeatures().get(0).toString());	
		assertEquals("parasite_InternalNode_00001", treeShapeDetector.getShapeSpecificFeatures().get(1).toString());
	}
	
	@Test
	public void SwitchingPathShapeFeaturesTest2() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.switchPathExample);
		LeafNodePair leafNodePair = new LeafNodePair(this.switchPathExample.getHostTree().getNode("4"), this.switchPathExample.getHostTree().getNode("3"));
		assertEquals(true, treeShapeDetector.hasSwitchPath(leafNodePair));
		assertNotNull(treeShapeDetector.getShapeSpecificFeatures());
		assertEquals(3, treeShapeDetector.getShapeSpecificFeatures().size());
		assertEquals("parasite_8", treeShapeDetector.getShapeSpecificFeatures().get(0).toString());	
		assertEquals("parasite_InternalNode_00001", treeShapeDetector.getShapeSpecificFeatures().get(1).toString());
	}
	
/*	@Test
	public void DualSwitchPathTest1() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.dualSwitchPathExample1);
		assertEquals(false, treeShapeDetector.hasTightlyPackedSwitchPathPair(this.dualSwitchPathExample1.getHostTree().getNode("5"), this.dualSwitchPathExample1.getHostTree().getNode("6")));
		assertEquals(true, treeShapeDetector.hasTightlyPackedSwitchPathPair(this.dualSwitchPathExample1.getHostTree().getNode("6"), this.dualSwitchPathExample1.getHostTree().getNode("5")));
	}
	
	@Test
	public void DualSwitchPathTest2() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.dualSwitchPathExample2);
		assertEquals(true, treeShapeDetector.hasTightlyPackedSwitchPathPair(this.dualSwitchPathExample2.getHostTree().getNode("5"), this.dualSwitchPathExample2.getHostTree().getNode("6")));
		assertEquals(false, treeShapeDetector.hasTightlyPackedSwitchPathPair(this.dualSwitchPathExample2.getHostTree().getNode("6"), this.dualSwitchPathExample2.getHostTree().getNode("5")));
	}	
	
	@Test
	public void DualSwitchPathTest3() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.dualSwitchPathExample3);
		assertEquals(true, treeShapeDetector.hasTightlyPackedSwitchPathPair(this.dualSwitchPathExample3.getHostTree().getNode("l1"), this.dualSwitchPathExample3.getHostTree().getNode("l2")));
		assertEquals(false, treeShapeDetector.hasTightlyPackedSwitchPathPair(this.dualSwitchPathExample3.getHostTree().getNode("l2"), this.dualSwitchPathExample3.getHostTree().getNode("l1")));
	}
	
	@Test
	public void DualSwitchPathTest4() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.dualSwitchPathExample4);
		assertEquals(false, treeShapeDetector.hasTightlyPackedSwitchPathPair(new LeafNodePair(this.dualSwitchPathExample4.getHostTree().getNode("leaf 2"), this.dualSwitchPathExample4.getHostTree().getNode("leaf 1"))));
		assertEquals(false, treeShapeDetector.hasTightlyPackedSwitchPathPair(new LeafNodePair(this.dualSwitchPathExample4.getHostTree().getNode("leaf 1"), this.dualSwitchPathExample4.getHostTree().getNode("leaf 2"))));
	}	*/
	
/*	@Test
	public void HasTightlyPackedSwitchPathPairTest1() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.dualSwitchPathExample1);
		assertEquals(true, treeShapeDetector.hasTightlyPackedSwitchPathPair(new LeafNodePair(this.dualSwitchPathExample1.getHostTree().getNode("5"), this.dualSwitchPathExample1.getHostTree().getNode("6"))));
		assertEquals(true, treeShapeDetector.hasTightlyPackedSwitchPathPair(new LeafNodePair(this.dualSwitchPathExample1.getHostTree().getNode("6"), this.dualSwitchPathExample1.getHostTree().getNode("5"))));
	}
	
	@Test
	public void HasTightlyPackedSwitchPathPairTest2() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.dualSwitchPathExample2);
		assertEquals(true, treeShapeDetector.hasTightlyPackedSwitchPathPair(new LeafNodePair(this.dualSwitchPathExample2.getHostTree().getNode("5"), this.dualSwitchPathExample2.getHostTree().getNode("6"))));
		assertEquals(true, treeShapeDetector.hasTightlyPackedSwitchPathPair(new LeafNodePair(this.dualSwitchPathExample2.getHostTree().getNode("6"), this.dualSwitchPathExample2.getHostTree().getNode("5"))));
	}*/	
	
	@Test
	public void DuplicationLossOrSwitchPatternDetectionTest() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.duplicationLossOrSwitchParadigm);
		assertEquals(true, treeShapeDetector.hasHostSwitchAndStrongLink(new LeafNodePair(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("11"), this.duplicationLossOrSwitchParadigm.getHostTree().getNode("12"))));
		assertEquals(true, treeShapeDetector.hasHostSwitchAndStrongLink(new LeafNodePair(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("12"), this.duplicationLossOrSwitchParadigm.getHostTree().getNode("11"))));
		assertEquals(true, treeShapeDetector.hasHostSwitchAndStrongLink(new LeafNodePair(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("3"), this.duplicationLossOrSwitchParadigm.getHostTree().getNode("4"))));
		assertEquals(true, treeShapeDetector.hasHostSwitchAndStrongLink(new LeafNodePair(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("4"), this.duplicationLossOrSwitchParadigm.getHostTree().getNode("3"))));
		assertEquals(false, treeShapeDetector.hasHostSwitchAndStrongLink(new LeafNodePair(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("24"), this.duplicationLossOrSwitchParadigm.getHostTree().getNode("25"))));
		assertEquals(false, treeShapeDetector.hasHostSwitchAndStrongLink(new LeafNodePair(this.duplicationLossOrSwitchParadigm.getHostTree().getNode("25"), this.duplicationLossOrSwitchParadigm.getHostTree().getNode("24"))));
	}
	
	@Test
	public void DoubleHostSwitchPathDetectionTest() {
		TreeShapeDetector treeShapeDetectorPositive = new TreeShapeDetector(this.doubleHostSwitchExample);
		TreeShapeDetector treeShapeDetectorNegative = new TreeShapeDetector(this.dualSwitchPathExample2);
		assertEquals(true, treeShapeDetectorPositive.hasDoubleSwitchPath(new LeafNodePair(this.doubleHostSwitchExample.getHostTree().getNode("5"), this.doubleHostSwitchExample.getHostTree().getNode("6"))));
		assertEquals(true, treeShapeDetectorPositive.hasDoubleSwitchPath(new LeafNodePair(this.doubleHostSwitchExample.getHostTree().getNode("6"), this.doubleHostSwitchExample.getHostTree().getNode("5"))));
		assertEquals(false, treeShapeDetectorNegative.hasDoubleSwitchPath(new LeafNodePair(this.dualSwitchPathExample2.getHostTree().getNode("5"), this.dualSwitchPathExample2.getHostTree().getNode("6"))));
		assertEquals(false, treeShapeDetectorNegative.hasDoubleSwitchPath(new LeafNodePair(this.dualSwitchPathExample2.getHostTree().getNode("6"), this.dualSwitchPathExample2.getHostTree().getNode("5"))));
	}
	
	@Test
	public void DuplicationSwitchDetectionTest() {
		TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.duplicationSwitch);
		assertEquals(true, treeShapeDetector.hasDuplicationSwitch(new LeafNodePair(this.duplicationSwitch.getHostTree().getNode("leaf 2"), this.duplicationSwitch.getHostTree().getNode("leaf 3"))));
	}	
}
