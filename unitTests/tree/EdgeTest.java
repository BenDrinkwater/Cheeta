package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EdgeTest {

	@Test
	public void CreateInternalEdge() {
		Node node1 = new RootedBifurcatingNode("Left");
		Node node2 = new RootedBifurcatingNode("Right");
		
		Edge edge = new InternalTreeEdge(node1, node2);
		assertNotNull(edge);
	}
	
	@Test
	public void CreateFloatingEdge() {
		Node root = new RootedBifurcatingNode("RootNode");
		
		Edge edge = new FloatingEdge(root);
		assertNotNull(edge);		
	}
	
	@Test
	public void ValidateInternalEdge() {
		Node node1 = new RootedBifurcatingNode("Left");
		Node node2 = new RootedBifurcatingNode("Right");
		
		Edge edge = new InternalTreeEdge(node1, node2);
		assertEquals("(Left,Right)", edge.toString());		
	}
	
	@Test
	public void ValidateFloatingEdge() {
		Node root = new RootedBifurcatingNode("RootNode");
		
		Edge edge = new FloatingEdge(root);
		assertEquals("(VirtualNode,RootNode)", edge.toString());
	}	
}
