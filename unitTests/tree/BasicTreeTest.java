package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import io.nexus.NexusStringFormat;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class BasicTreeTest {

	@Test
	public void CreateSimpleTest() {
		Tree tree = new RootedBifurcatingTree("Root", "host");
		assertNotNull(tree);
		assertNotNull(tree.getRootNode());
		assertEquals("host_Root", tree.getRootNode().getNodeName());
	}
	
	@Test
	public void NodeLookupTest() {
		Tree tree = new RootedBifurcatingTree("Root", "host");
		Node node = tree.getNode("Root");
		
		assertEquals("host_Root", tree.getNode("Root").getNodeName());
		assertEquals("host_Root", tree.getNodeWithPrefix("host_Root").getNodeName());
		assertEquals("Root", tree.getNodeName(node));
	}
	
	@Test
	public void AddingNewNodesTest1() {
		Tree tree = new RootedBifurcatingTree("Root", "host");
		assertEquals(true, tree.addChildren("Root", "left_FirstLevel", "right_FirstLevel"));
		assertEquals(true, tree.addChildren("left_FirstLevel", "leftLeft_SecondLevel", "leftRight_SecondLevel"));
		assertEquals(true, tree.addChildren("right_FirstLevel", "rightLeft_SecondLevel", "rightRight_SecondLevel"));
		
		assertEquals(7, tree.size());
		assertEquals(4, tree.numberOfLeafNodes());
		assertEquals(3, tree.numberOfInteranlNodes());
		
		// TODO max height test
	}

	@Test
	public void AddingNewNodesTest2() {
		Tree tree = new RootedBifurcatingTree("Root", "host");
		assertEquals(true, tree.addChildren("Root", "left_FirstLevel", 3, "right_FirstLevel", 4));
		assertEquals(true, tree.addChildren("left_FirstLevel", "leftLeft_SecondLevel", 5, "leftRight_SecondLevel", 9));
		assertEquals(true, tree.addChildren("right_FirstLevel", "rightLeft_SecondLevel", "rightRight_SecondLevel"));
	
		assertEquals(7, tree.size());
		assertEquals(4, tree.numberOfLeafNodes());
		assertEquals(3, tree.numberOfInteranlNodes());	
		
		// TODO max height test
	}
	
	@Test
	public void LeafNodeTest1() {
		Tree tree = this.TestTree();
		assertEquals(8, tree.getLeafNodes().size());
		Iterator<Node> iterator = tree.getLeafNodes().iterator();
		assertEquals(tree.getNode("E"), iterator.next());
		assertEquals(tree.getNode("F"), iterator.next());
		assertEquals(tree.getNode("G"), iterator.next());
		assertEquals(tree.getNode("H"), iterator.next());
		assertEquals(tree.getNode("J"), iterator.next());
		assertEquals(tree.getNode("M"), iterator.next());
		assertEquals(tree.getNode("N"), iterator.next());
		assertEquals(tree.getNode("O"), iterator.next());
	}
	
	@Test
	public void LeafNodeTest2() {
		Tree tree = this.TestTree();
		assertEquals(8, tree.getLeafNodesList().size());
		Iterator<Node> iterator = tree.getLeafNodesList().iterator();
		assertEquals(tree.getNode("E"), iterator.next());
		assertEquals(tree.getNode("F"), iterator.next());
		assertEquals(tree.getNode("G"), iterator.next());
		assertEquals(tree.getNode("H"), iterator.next());
		assertEquals(tree.getNode("J"), iterator.next());
		assertEquals(tree.getNode("M"), iterator.next());
		assertEquals(tree.getNode("N"), iterator.next());
		assertEquals(tree.getNode("O"), iterator.next());
	}
	
	@Test
	public void GetAllNodesTest1() {
		Tree tree = this.TestTree();
		assertEquals(15, tree.getAllNodes().size());
		Iterator<Node> iterator = tree.getAllNodes().iterator();
		assertEquals(tree.getNode("A"), iterator.next());
		assertEquals(tree.getNode("B"), iterator.next());
		assertEquals(tree.getNode("C"), iterator.next());
		assertEquals(tree.getNode("D"), iterator.next());		
		assertEquals(tree.getNode("E"), iterator.next());
		assertEquals(tree.getNode("F"), iterator.next());
		assertEquals(tree.getNode("G"), iterator.next());
		assertEquals(tree.getNode("H"), iterator.next());
		assertEquals(tree.getNode("I"), iterator.next());
		assertEquals(tree.getNode("J"), iterator.next());
		assertEquals(tree.getNode("K"), iterator.next());
		assertEquals(tree.getNode("L"), iterator.next());
		assertEquals(tree.getNode("M"), iterator.next());
		assertEquals(tree.getNode("N"), iterator.next());
		assertEquals(tree.getNode("O"), iterator.next());	
	}
	
	@Test
	public void GetAllNodesTest2() {
		Tree tree = this.TestTree();
		assertEquals(15, tree.getAllNodesAsList().size());
		Iterator<Node> iterator = tree.getAllNodesAsList().iterator();
		assertEquals(tree.getNode("A"), iterator.next());
		assertEquals(tree.getNode("B"), iterator.next());
		assertEquals(tree.getNode("C"), iterator.next());
		assertEquals(tree.getNode("D"), iterator.next());		
		assertEquals(tree.getNode("E"), iterator.next());
		assertEquals(tree.getNode("F"), iterator.next());
		assertEquals(tree.getNode("G"), iterator.next());
		assertEquals(tree.getNode("H"), iterator.next());
		assertEquals(tree.getNode("I"), iterator.next());
		assertEquals(tree.getNode("J"), iterator.next());
		assertEquals(tree.getNode("K"), iterator.next());
		assertEquals(tree.getNode("L"), iterator.next());
		assertEquals(tree.getNode("M"), iterator.next());
		assertEquals(tree.getNode("N"), iterator.next());
		assertEquals(tree.getNode("O"), iterator.next());	
	}
	
	@Test
	public void CheckNumberOfHopsTest1() {
		Tree tree = this.TestTree();
		assertEquals((Integer) 1, tree.distanceBetweenNodes(tree.getNode("A"), tree.getNode("D")));
	}
	
	@Test
	public void CheckNumberOfHopsTest2() {
		Tree tree = this.TestTree();
		assertEquals((Integer) 0, tree.distanceBetweenNodes(tree.getNode("A"), tree.getNode("B")));		
	}
	
	@Test
	public void CheckNumberOfHopsTest3() {
		Tree tree = this.TestTree();
		assertEquals((Integer) 0, tree.distanceBetweenNodes(tree.getNode("B"), tree.getNode("B")));			
	}
	
	@Test
	public void CheckNumberOfHopsTest4() {
		Tree tree = this.TestTree();
		assertEquals((Integer) 0, tree.distanceBetweenNodes(tree.getNode("O"), tree.getNode("O")));			
	}
	
	@Test
	public void CheckNumberOfHopsTest5() {
		Tree tree = this.TestTree();
		assertEquals((Integer) 0, tree.distanceBetweenNodes(tree.getNode("A"), tree.getNode("A")));			
	}
	
	@Test
	public void CheckNumberOfHopsTest6() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.distanceBetweenNodes(tree.getNode("D"), tree.getNode("A")));			
	}	
	
	@Test
	public void CheckNumberOfHopsTest7() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.distanceBetweenNodes(tree.getNode("D"), null));			
	}	
	
	@Test
	public void CheckNumberOfHopsTest8() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.distanceBetweenNodes(tree.getNode("C"), tree.getNode("B")));			
	}	
	
	@Test
	public void CheckNumberOfHopsTest9() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.distanceBetweenNodes(null, null));			
	}	
	
	@Test
	public void CheckNumberOfHopsAsListTest1() {
		Tree tree = this.TestTree();
		List<Node> nodes = new LinkedList<Node>();
		nodes.add(tree.getNode("B"));
		assertEquals(nodes, tree.nodesBetweenNodes(tree.getNode("A"), tree.getNode("D")));
	}
	
	@Test
	public void CheckNumberOfHopsAsListTest2() {
		Tree tree = this.TestTree();
		List<Node> nodes = new LinkedList<Node>();
		assertEquals(nodes, tree.nodesBetweenNodes(tree.getNode("A"), tree.getNode("B")));		
	}
	
	@Test
	public void CheckNumberOfHopsAsListTest3() {
		Tree tree = this.TestTree();
		List<Node> nodes = new LinkedList<Node>();
		assertEquals(nodes, tree.nodesBetweenNodes(tree.getNode("B"), tree.getNode("B")));			
	}
	
	@Test
	public void CheckNumberOfHopsAsListTest4() {
		Tree tree = this.TestTree();
		List<Node> nodes = new LinkedList<Node>();
		assertEquals(nodes, tree.nodesBetweenNodes(tree.getNode("O"), tree.getNode("O")));			
	}
	
	@Test
	public void CheckNumberOfHopsAsListTest5() {
		Tree tree = this.TestTree();
		List<Node> nodes = new LinkedList<Node>();
		assertEquals(nodes, tree.nodesBetweenNodes(tree.getNode("A"), tree.getNode("A")));			
	}
	
	@Test
	public void CheckNumberOfHopsAsListTest6() {
		Tree tree = this.TestTree();
		List<Node> nodes = new LinkedList<Node>();
		nodes.add(tree.getNode("B"));
		nodes.add(tree.getNode("D"));
		nodes.add(tree.getNode("I"));
		nodes.add(tree.getNode("K"));
		assertEquals(nodes, tree.nodesBetweenNodes(tree.getNode("A"), tree.getNode("L")));
	}	
	
	@Test
	public void CheckNumberOfHopsAsListTest7() {
		Tree tree = this.TestTree();
		List<Node> nodes = new LinkedList<Node>();
		nodes.add(tree.getNode("B"));
		nodes.add(tree.getNode("D"));
		nodes.add(tree.getNode("I"));
		nodes.add(tree.getNode("K"));
		nodes.add(tree.getNode("L"));
		assertEquals(nodes, tree.nodesBetweenNodes(tree.getNode("A"), tree.getNode("N")));
	}		
	
	@Test
	public void CheckNumberOfHopsAsListTest8() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.nodesBetweenNodes(tree.getNode("D"), tree.getNode("A")));			
	}		
	
	@Test
	public void CheckNumberOfHopsAsListTest9() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.nodesBetweenNodes(tree.getNode("D"), null));			
	}	
	
	@Test
	public void CheckNumberOfHopsAsListTest10() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.nodesBetweenNodes(tree.getNode("C"), tree.getNode("B")));			
	}	
	
	@Test
	public void CheckNumberOfHopsAsListTest11() {
		Tree tree = this.TestTree();
		assertEquals(null, tree.nodesBetweenNodes(null, null));			
	}	
	
	@Test
	public void RemoveLeafNodesTest1() {
		Tree tree = this.TestTree();
		assertEquals(15, tree.size());
		tree.deleteLeafNodePair(tree.getNode("N"), tree.getNode("O"));
		assertEquals(13, tree.size());
	}

	@Test
	public void RemoveLeafNodesTest2() {
		Tree tree = this.TestTree();
		Node parent = tree.getNode("N").getParent();
		assertEquals(false, parent.isLeafNode());
		tree.deleteLeafNodePair(tree.getNode("N"), tree.getNode("O"));
		assertEquals(true, parent.isLeafNode());
	}
	
	@Test
	public void RemoveLeafNodesTest3() {
		Tree tree = this.TestTree();
		LeafNodePair leafNodePair = new LeafNodePair(tree.getNode("N"), tree.getNode("O"));
		Node parent = leafNodePair.getParent();
		assertEquals(false, parent.isLeafNode());
		assertEquals(true, tree.deleteLeafNodePair(leafNodePair));
		assertEquals(true, parent.isLeafNode());
	}	
	
	@Test
	public void RemoveLeafNodesTest4() {
		Tree tree = this.TestTree();
		LeafNodePair leafNodePair = new LeafNodePair(tree.getNode("M"), tree.getNode("L"));
		Node parent = leafNodePair.getParent();
		assertNull(parent);
		assertEquals(false, tree.deleteLeafNodePair(leafNodePair));
		assertEquals(15, tree.size());
	}
	
	@Test
	public void RemoveLeafNodesTest5() {
		Tree tree = this.TestTree();
		LeafNodePair leafNodePair = new LeafNodePair(tree.getNode("M"), null);
		Node parent = leafNodePair.getParent();
		assertNull(parent);
		assertEquals(false, tree.deleteLeafNodePair(leafNodePair));
		assertEquals(15, tree.size());
	}	
	
	@Test
	public void RemoveLeafNodesTest6() {
		Tree tree = this.TestTree();
		LeafNodePair leafNodePair = null;
		assertEquals(false, tree.deleteLeafNodePair(leafNodePair));
		assertEquals(15, tree.size());
	}
	
	@Test
	public void GetJaneNexusStringTest() {
		Tree tree = this.TestTree();
		assertEquals("(((H,(J,((N,O),M))),E),(F,G))", tree.getNexusString(NexusStringFormat.Jane));
	}
	
	@Test
	public void GetCorePaNexusStringTest() {
		Tree tree = this.TestTree();
		assertEquals("(((H,(J,((N,O)L,M)K)I)D,E)B,(F,G)C)A", tree.getNexusString(NexusStringFormat.CorePa));
	}	
	
	@Test
	public void GetDefaultPaNexusStringTest() {
		Tree tree = this.TestTree();
		assertEquals(tree.getNexusString(NexusStringFormat.Jane), tree.getNexusString(NexusStringFormat.Default));
	}		
	
	private Tree TestTree() {
		Tree tree = TreeConstructor.createNewTree("A", "host");
		tree.addChildren("A", "B", "C");
		tree.addChildren("B", "D", "E");
		tree.addChildren("C", "F", "G");
		tree.addChildren("D", "H", "I");
		tree.addChildren("I", "J", "K");
		tree.addChildren("K", "L", "M");
		tree.addChildren("L", "N", "O");
		return tree;
	}
}
