package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class NodeTest {

	@Test
	public void CreateNodeTest() {
		Node node = new RootedBifurcatingNode("SingleNode");
		assertNotNull(node);
		assertEquals("SingleNode", node.getNodeName());
		assertEquals(true, node.isLeafNode());
		assertEquals(true, node.isRootNode());
	}
	
	@Test
	public void CreateNodeWithExistingChildren() {
		Node node1 = new RootedBifurcatingNode("RootNode");
		Node node2 = new RootedBifurcatingNode("LeftChild");
		Node node3 = new RootedBifurcatingNode("RightChild");
		node1.addExistingNodesAsChildren(node2, node3);
		assertEquals(true, node1.isRootNode());
		assertEquals(false, node1.isLeafNode());
		assertEquals(node2, node1.getLeftChild());
		assertEquals(node3, node1.getRightChild());
	}
	
	@Test
	public void CreateNodeWithChildren() {
		Node node1 = new RootedBifurcatingNode("RootNode");
		node1.setChildren("LeftChild", 1, "RightChild", 1);
		assertEquals(true, node1.isRootNode());
		assertEquals(false, node1.isLeafNode());
		assertEquals("LeftChild", node1.getLeftChild().getNodeName());
		assertEquals("RightChild", node1.getRightChild().getNodeName());		
	}
	
	@Test
	public void AddingTreesToTrees() {
		Node rootNode1 = new RootedBifurcatingNode("RootNode1");
		
		Node rootNode2 = new RootedBifurcatingNode("RootNode2");
		rootNode2.setChildren("LeftChild2", 1, "RightChild2", 1);
		
		Node rootNode3 = new RootedBifurcatingNode("RootNode3");
		rootNode3.setChildren("LeftChild3", 1, "RightChild3", 1);
		
		rootNode1.addExistingNodesAsChildren(rootNode2, rootNode3);
		
		assertEquals("LeftChild2", rootNode1.getLeftChild().getLeftChild().getNodeName());
		assertEquals("RightChild2", rootNode1.getLeftChild().getRightChild().getNodeName());
		assertEquals("LeftChild3", rootNode1.getRightChild().getLeftChild().getNodeName());
		assertEquals("RightChild3", rootNode1.getRightChild().getRightChild().getNodeName());
	}
	
	@Test
	public void GetSetOfAllEdges() {
		Node rootNode1 = new RootedBifurcatingNode("RootNode1");
		
		Node rootNode2 = new RootedBifurcatingNode("RootNode2");
		rootNode2.setChildren("LeftChild2", 1, "RightChild2", 1);
		
		Node rootNode3 = new RootedBifurcatingNode("RootNode3");
		rootNode3.setChildren("LeftChild3", 1, "RightChild3", 1);
		
		rootNode1.addExistingNodesAsChildren(rootNode2, rootNode3);
				
		Set<Edge> edges = new TreeSet<Edge>();
		
		edges.addAll(rootNode1.getEdgeSet());
		edges.addAll(rootNode2.getEdgeSet());
		edges.addAll(rootNode2.getLeftChild().getEdgeSet());
		edges.addAll(rootNode2.getRightChild().getEdgeSet());
		edges.addAll(rootNode3.getEdgeSet());
		edges.addAll(rootNode3.getLeftChild().getEdgeSet());
		edges.addAll(rootNode3.getRightChild().getEdgeSet());
		
		assertEquals(7, edges.size());
	}
	
	@Test
	public void RemoveNodeTest1() {
		Node node1 = new RootedBifurcatingNode("RootNode");
		Node node2 = new RootedBifurcatingNode("LeftChild");
		Node node3 = new RootedBifurcatingNode("RightChild");
		node1.addExistingNodesAsChildren(node2, node3);
		
		node1.removeEdge(node2);
		node1.removeEdge(node3);
		
		assertEquals(true, node1.isLeafNode());
	}
	
	@Test
	public void RemoveNodeTest2() {
		Node node1 = new RootedBifurcatingNode("RootNode");
		Node node2 = new RootedBifurcatingNode("LeftChild");
		Node node3 = new RootedBifurcatingNode("RightChild");
		Node node4 = null;
		node1.addExistingNodesAsChildren(node2, node3);
		
		node1.removeEdge(node4);
		
		assertEquals(true, node1.isRootNode());
		assertEquals(false, node1.isLeafNode());
		assertEquals(node2, node1.getLeftChild());
		assertEquals(node3, node1.getRightChild());
	}	
	
	@Test
	public void NumberOfInternalNodesTest1() {
		// Balanced Tree Test
		Node node = new RootedBifurcatingNode("A");
		node.setChildren("B", 1, "C", 1);
		node.getLeftChild().setChildren("D", 1, "E", 1);
		node.getRightChild().setChildren("F", 1, "G", 1);
		
		node.setTheNumberOfChildrenThatAreInternalNodes();
		assertEquals(2, node.numberOfChildrenThatAreInternalNodes());
	}
	
	@Test
	public void NumberOfInternalNodesTest2() {
		// Unbalanced to the Left Tree Test
		Node node = new RootedBifurcatingNode("A");
		node.setChildren("B", 1, "C", 1);
		node.getLeftChild().setChildren("D", 1, "E", 1);
		node.getLeftChild().getLeftChild().setChildren("F", 1, "G", 1);
		
		node.setTheNumberOfChildrenThatAreInternalNodes();
		assertEquals(2, node.numberOfChildrenThatAreInternalNodes());
		assertEquals(1, node.getLeftChild().numberOfChildrenThatAreInternalNodes());
	}
	
	@Test
	public void NumberOfInternalNodesTest3() {
		// Unbalanced to the Right Tree Test
		Node node = new RootedBifurcatingNode("A");
		node.setChildren("B", 1, "C", 1);
		node.getRightChild().setChildren("D", 1, "E", 1);
		node.getRightChild().getRightChild().setChildren("F", 1, "G", 1);
		
		node.setTheNumberOfChildrenThatAreInternalNodes();
		assertEquals(2, node.numberOfChildrenThatAreInternalNodes());
		assertEquals(1, node.getRightChild().numberOfChildrenThatAreInternalNodes());
	}		
}
