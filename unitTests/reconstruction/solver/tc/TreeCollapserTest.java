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

import reconstruction.solver.tc.TreeCollapser.EdgeResolver;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class TreeCollapserTest {

	private CoevolutionaryHistory history;	
	private final String fileName = "unitTestFiles" + File.separator + "reconstruction" +  File.separator + "solver" +  File.separator + "tc"  +  File.separator +  "StrongLink.nex";

	@Before
	public void LoadHistory() {
		try {
			NexusFile file = new JaneNexusFile(this.fileName);
			this.history = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Test
	public void CreateTreeCollapserTest() {
		TreeCollapser tc = new TreeCollapser(this.history);
		assertNotNull(tc);
	}
	
	@Test
	public void CreateTreeCollapserWithNullHistoryTest() {
		TreeCollapser tc = new TreeCollapser(null);
		assertNotNull(tc);
	}
	
	@Test
	public void CollapseHostTreeTest() {
		TreeCollapser tc = new TreeCollapser(this.history);
		assertNotNull(this.history.getHostTree().getNode("3"));
		Node parent = this.history.getHostTree().getNode("3").getParent();
		assertEquals(4, this.history.getHostTree().getLeafNodes().size());
		tc.collapseHostTree(this.history.getHostTree().getNode("3"));
			assertEquals(3, this.history.getHostTree().getLeafNodes().size());
		assertEquals(true, this.history.getHostTree().getLeafNodes().contains(parent));
	}
	
	@Test
	public void CollapseParasiteTreeTest() {
		TreeCollapser tc = new TreeCollapser(this.history);
		assertNotNull(this.history.getParasiteTree().getNode("10"));
		Node parent = this.history.getParasiteTree().getNode("10").getParent();
		assertEquals(4, this.history.getParasiteTree().getLeafNodes().size());
		tc.collapseParasiteTree(this.history.getParasiteTree().getNode("10"), EdgeResolver.resolveBoth);
		assertEquals(3, this.history.getParasiteTree().getLeafNodes().size());
		assertEquals(true, this.history.getParasiteTree().getLeafNodes().contains(parent));
	}
	
	@Test
	public void CollapseParasiteTreeForHostSwitchTest() {
		TreeCollapser tc = new TreeCollapser(this.history);
		assertNotNull(this.history.getParasiteTree().getNode("10"));
		Node parent = this.history.getParasiteTree().getNode("10").getParent();
		assertEquals(4, this.history.getParasiteTree().getLeafNodes().size());
		tc.collapseParasiteTree(this.history.getParasiteTree().getNode("10"), EdgeResolver.onlyResolveFirst);
		assertEquals(3, this.history.getParasiteTree().getLeafNodes().size());
		assertEquals(true, this.history.getParasiteTree().getLeafNodes().contains(parent));		
	}
	
	@Test
	public void MultipleCollapsesForHostTreeTest1() {
		CollapseHostNode("3");
		CollapseHostNode("5");
		CollapseHostNode("InternalNode_00001");
		assertEquals(1, this.history.getHostTree().size());
	}
	
	@Test
	public void MultipleCollapsesForHostTreeTest2() {
		this.CollapseHostNode("4");
		this.CollapseHostNode("6");
		this.CollapseHostNode("InternalNode_00002");
		assertEquals(1, this.history.getHostTree().size());
	}	
	
	private void CollapseHostNode(String node) {
		TreeCollapser tc = new TreeCollapser(this.history);
		assertNotNull(this.history.getHostTree().getNode(node));
		Node parent = this.history.getHostTree().getNode(node).getParent();
		tc.collapseHostTree(this.history.getHostTree().getNode(node));
		assertEquals(true, this.history.getHostTree().getLeafNodes().contains(parent));		
	}
}
