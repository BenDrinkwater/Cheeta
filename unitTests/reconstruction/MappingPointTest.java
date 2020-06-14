package reconstruction;

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

import reconstruction.MappingPoint.MappedPoint;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.Node;
import util.Pair;

public class MappingPointTest {

	private CoevolutionaryHistory gopherLice;
	private final String gopherLiceFileName 
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	

	@Before
	public void LoadGopherLiceExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.gopherLiceFileName);		
			this.gopherLice = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Test
	public void MappingPointAsNodeTest1() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
	}
	
	@Test
	public void MappingPointAsNodeTest2() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
		Object object = point.getMappedPoint();
		assertEquals(node, object);
	}
	
	@Test
	public void MappingPointAsNodeTest3() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
		assertEquals(false, point.mappingPointIsEdge());
	}
	
	@Test
	public void MappingPointAsNodeTest4() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
		assertEquals(false, point.mappingPointIsEdge());
	}
	
	@Test
	public void MappingPointAsNodeTest5() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
		assertEquals(node, point.getMappedPointAsNode());
	}
	
	
	@Test
	public void MappingPointAsNodeTest6() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
		assertEquals(true, point.mappingPointIsNode());
	}
	
	@Test
	public void MappingPointAsNodeTest7() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
		assertEquals(MappedPoint.Node, point.getMappedTo());
	}
	
	@Test
	public void MappingPointAsNodeTest8() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point = new MappingPoint(node);
		assertNotNull(point);
		assertEquals(false, point.isWideSpreadMappingPoint());
	}	
	
	@Test
	public void MappingPointAsNodeTest9() {
		Node node = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		MappingPoint point1 = new MappingPoint(node);
		assertNotNull(point1);
		MappingPoint point2 = new MappingPoint(node);
		assertNotNull(point2);
		assertEquals(true, point1.equals(point2));
		assertEquals(true, point2.equals(point1));
	}	
	
	@Test
	public void MappingPointAsNodeTest10() {
		Node node1 = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		Node node2 = this.gopherLice.getHostTree().getNode("InternalNode_00002");
		MappingPoint point1 = new MappingPoint(node1);
		assertNotNull(point1);
		MappingPoint point2 = new MappingPoint(node2);
		assertNotNull(point2);
		assertEquals(false, point1.equals(point2));
		assertEquals(false, point2.equals(point1));
	}	
	
	@Test
	public void MappingPointAsNodeTest11() {
		Node node1 = this.gopherLice.getHostTree().getNode("InternalNode_00001");
		Node node2 = this.gopherLice.getHostTree().getNode("InternalNode_00002");
		MappingPoint point1 = new MappingPoint(node1);
		assertNotNull(point1);
		MappingPoint point2 = new MappingPoint(node2);
		assertNotNull(point2);
		assertEquals(false, point1.equals(null));
		assertEquals(false, point2.equals(null));
	}	
	
	@Test
	public void MappingPointAsEdgeTest1() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point = new MappingPoint(edge);
		assertNotNull(point);		
	}
	
	@Test
	public void MappingPointAsEdgeTest2() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point = new MappingPoint(edge);
		assertNotNull(point);
		Object object = point.getMappedPoint();
		assertEquals(edge, object);		
	}
	
	@Test
	public void MappingPointAsEdgeTest3() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point = new MappingPoint(edge);
		assertNotNull(point);	
		assertEquals(true, point.mappingPointIsDuplication());
	}
	
	@Test
	public void MappingPointAsEdgeTest4() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point = new MappingPoint(edge);
		assertNotNull(point);	
		assertEquals(true, point.mappingPointIsEdge());
	}
	
	@Test
	public void MappingPointAsEdgeTest5() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point = new MappingPoint(edge);
		assertNotNull(point);	
		assertEquals(this.gopherLice.getHostTree().getNode("InternalNode_00001"), point.getMappedPointAsNode());
	}
	
	@Test
	public void MappingPointAsEdgeTest6() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point = new MappingPoint(edge);
		assertNotNull(point);	
		assertEquals(false, point.mappingPointIsNode());
	}	
	
	@Test
	public void MappingPointAsEdgeTest7() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point = new MappingPoint(edge);
		assertNotNull(point);	
		assertEquals(MappedPoint.Edge, point.getMappedTo());
	}	
	
	@Test
	public void MappingPointAsEdgeTest8() {
		Edge edge = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		MappingPoint point1 = new MappingPoint(edge);
		assertNotNull(point1);	
		MappingPoint point2 = new MappingPoint(edge);
		assertNotNull(point2);	
		assertEquals(true, point1.equals(point2));
		assertEquals(true, point2.equals(point1));
	}		
	
	@Test
	public void MappingPointAsEdgeTest9() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		MappingPoint point1 = new MappingPoint(edge1);
		assertNotNull(point1);	
		MappingPoint point2 = new MappingPoint(edge2);
		assertNotNull(point2);	
		assertEquals(false, point1.equals(point2));
		assertEquals(false, point2.equals(point1));
	}
	
	@Test
	public void MappingPointAsHostSwitchTest1() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);		
	}
	
	@Test
	public void MappingPointAsHostSwitchTest2() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);
		Object object = point.getMappedPoint();
		assertEquals(hostSwitch, object);		
	}
	
	@Test
	public void MappingPointAsHostSwitchTest3() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);
		assertEquals(false, point.mappingPointIsDuplication());		
	}
	
	@Test
	public void MappingPointAsHostSwitchTest4() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);
		assertEquals(true, point.mappingPointIsEdge());		
	}
	
	@Test
	public void MappingPointAsHostSwitchTest5() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);
		assertEquals(this.gopherLice.getHostTree().getNode("InternalNode_00001"), point.getMappedPointAsNode());	
	}
	
	@Test
	public void MappingPointAsHostSwitchTest6() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);
		assertEquals(MappedPoint.HostSwitchPair, point.getMappedTo());	
	}
	
	@Test
	public void MappingPointAsHostSwitchTest7() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);
		assertEquals(hostSwitch, point.getEdgePair());	
	}
	
	@Test
	public void MappingPointAsHostSwitchTest8() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point = new MappingPoint(hostSwitch);
		assertNotNull(point);
		assertEquals(this.gopherLice.getHostTree().getNode("InternalNode_00002"), point.getSecondHostNode());	
	}

	@Test
	public void MappingPointAsHostSwitchTest9() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Pair<Edge, Edge> hostSwitch = new Pair<Edge, Edge>(edge1, edge2);
		MappingPoint point1 = new MappingPoint(hostSwitch);
		assertNotNull(point1);
		MappingPoint point2 = new MappingPoint(hostSwitch);
		assertNotNull(point2);
		assertEquals(true, point1.equals(point2));
		assertEquals(true, point2.equals(point1));
	}
	
	@Test
	public void MappingPointAsHostSwitchTest10() {
		Edge edge1 = this.gopherLice.getHostTree().getNode("InternalNode_00001").getParentEdge();
		Edge edge2 = this.gopherLice.getHostTree().getNode("InternalNode_00002").getParentEdge();
		Edge edge3 = this.gopherLice.getHostTree().getNode("InternalNode_00003").getParentEdge();
		Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(edge1, edge2);
		Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(edge1, edge3);
		MappingPoint point1 = new MappingPoint(hostSwitch1);
		assertNotNull(point1);
		MappingPoint point2 = new MappingPoint(hostSwitch2);
		assertNotNull(point2);
		assertEquals(false, point1.equals(point2));
		assertEquals(false, point2.equals(point1));
	}
	
	@Test
	public void UndefinedMappingPointTest1() {	
		MappingPoint point = new MappingPoint();
		assertNotNull(point);
	}
	
	@Test
	public void UndefinedMappingPointTest2() {	
		MappingPoint point = new MappingPoint();
		assertNotNull(point);
		Object object = point.getMappedPoint();
		assertEquals(null, object);			
	}
	
	@Test
	public void UndefinedMappingPointTest3() {	
		MappingPoint point = new MappingPoint();
		assertNotNull(point);
		Node node = point.getSecondHostNode();
		assertEquals(null, node);			
	}
	
	@Test
	public void UndefinedMappingPointTest4() {	
		MappingPoint point = new MappingPoint();
		assertNotNull(point);
		assertEquals(null, point.getMappedPointAsNode());			
	}	
	
	@Test
	public void UndefinedMappingPointTest5() {	
		MappingPoint point = new MappingPoint();
		assertNotNull(point);
		assertEquals(MappedPoint.Undefined, point.getMappedTo());				
	}	
	
	@Test
	public void UndefinedMappingPointTest6() {	
		MappingPoint point = new MappingPoint();
		assertNotNull(point);
		assertEquals("Unknown Mapping", point.toString());				
	}
}
