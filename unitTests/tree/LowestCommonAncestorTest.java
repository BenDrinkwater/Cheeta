package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import tanglegram.CoevolutionaryHistory;

public class LowestCommonAncestorTest {

	private CoevolutionaryHistory testCase1;
	private final String testCase1FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "RealExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	

	private void loadPocketGopherLouseExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase1FileName);
			this.testCase1 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();	
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00002"), 6);			
			this.testCase1.getHostTree().setUniqueNodeTimings(nodes);	
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
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00007");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00007"), this.testCase1.getHostTree().getNode("InternalNode_00007")));
	}	
	
	@Test
	public void TestCase2() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00007");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00007"), this.testCase1.getHostTree().getNode("InternalNode_00006")));
	}	
	
	@Test
	public void TestCase3() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00007");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00007"), this.testCase1.getHostTree().getNode("InternalNode_00005")));
	}	
	
	@Test
	public void TestCase4() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00007");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00007"), this.testCase1.getHostTree().getNode("bursarius")));
	}
	
	@Test
	public void TestCase5() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00006");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00006"), this.testCase1.getHostTree().getNode("bursarius")));
	}
	
	@Test
	public void TestCase6() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00001");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("talpoides"), this.testCase1.getHostTree().getNode("bottae")));
	}	
	
	@Test
	public void TestCase7() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00006");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("heterodus"), this.testCase1.getHostTree().getNode("bursarius")));
	}	
	
	@Test
	public void TestCase8() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00002");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("heterodus"), this.testCase1.getHostTree().getNode("cherriei")));
	}	
	
	@Test
	public void TestCase9() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00003");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("heterodus"), this.testCase1.getHostTree().getNode("underwoodi")));
	}	
	
	@Test
	public void TestCase10() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00004");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("heterodus"), this.testCase1.getHostTree().getNode("cavator")));
	}	
	
	@Test
	public void TestCase11() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00005");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("heterodus"), this.testCase1.getHostTree().getNode("hispidus")));
	}
	
	@Test
	public void TestCase12() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("hispidus");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("hispidus"), this.testCase1.getHostTree().getNode("hispidus")));
	}	
	
	@Test
	public void TestCase13() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00005");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00005"), this.testCase1.getHostTree().getNode("InternalNode_00005")));
	}
	
	@Test
	public void TestCase14() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00003");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00003"), this.testCase1.getHostTree().getNode("InternalNode_00003")));
	}
	
	@Test
	public void TestCase15() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00002");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("InternalNode_00002"), this.testCase1.getHostTree().getNode("InternalNode_00002")));
	}	
	
	@Test
	public void TestCase16() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00007");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("bottae"), this.testCase1.getHostTree().getNode("bursarius")));
	}	
	
	@Test
	public void TestCase17() {
		this.loadPocketGopherLouseExample();
		Tree hostTree = this.testCase1.getHostTree();
		
		Node expected = this.testCase1.getHostTree().getNode("InternalNode_00001");
		assertEquals(expected, hostTree.getLowestCommonAncestor(this.testCase1.getHostTree().getNode("bottae"), this.testCase1.getHostTree().getNode("InternalNode_00001")));
	}		
}
