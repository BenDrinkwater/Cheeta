package reconstruction.solver.rp;

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

import reconstruction.MappingPoint;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.tc.LossCounter;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.Node;
import util.Pair;

public class RightPushTestBase {

	protected ParasiteMappingList testCase1Scenerio;
	protected CoevolutionaryHistory testCase1;
	private final String testCase1FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "rp" + File.separator + "TestCase1.nex";
	
	protected ParasiteMappingList testCase2Scenerio;
	protected CoevolutionaryHistory testCase2;
	private final String testCase2FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "rp" + File.separator + "TestCase2.nex";
	
	protected ParasiteMappingList testCase3Scenerio;
	protected CoevolutionaryHistory testCase3;
	private final String testCase3FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "rp" + File.separator + "TestCase3.nex";
	
	protected ParasiteMappingList testCase4Scenerio;
	protected CoevolutionaryHistory testCase4;
	private final String testCase4FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "rp" + File.separator + "TestCase4.nex";
	
	public void LoadTestCase1WithFirstOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase1FileName);
			this.testCase1 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00003"), 1);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00001"), 3);
			this.testCase1.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase1Scenerio = new ParasiteMappingList();
			// leaf nodes
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf1"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf1")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf2"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf2")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf3"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf3")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf4"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf4")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf5"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf5")));
			// internal nodes
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(this.testCase1.getHostTree().getNode("InternalNode_00001")));
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("InternalNode_00001").getParentEdge(), this.testCase1.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("InternalNode_00001").getParentEdge(), this.testCase1.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(hostSwitch2));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("InternalNode_00003").getParentEdge(), this.testCase1.getHostTree().getNode("hostleaf5").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(hostSwitch3));			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	public void LoadTestCase1WithSecondOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase1FileName);
			this.testCase1 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00003"), 1);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00001"), 2);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00002"), 3);
			this.testCase1.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase1Scenerio = new ParasiteMappingList();
			// leaf nodes
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf1"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf1")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf2"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf2")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf3"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf3")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf4"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf4")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf5"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf5")));
			// internal nodes
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(this.testCase1.getHostTree().getNode("InternalNode_00001")));
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("InternalNode_00001").getParentEdge(), this.testCase1.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("InternalNode_00001").getParentEdge(), this.testCase1.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(hostSwitch2));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("InternalNode_00003").getParentEdge(), this.testCase1.getHostTree().getNode("hostleaf5").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(hostSwitch3));	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	public void LoadTestCase1WithThirdOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase1FileName);
			this.testCase1 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00003"), 1);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00001"), 3);
			this.testCase1.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase1Scenerio = new ParasiteMappingList();
			// leaf nodes
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf1"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf1")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf2"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf2")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf3"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf3")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf4"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf4")));
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("parasiteleaf5"), new MappingPoint(this.testCase1.getHostTree().getNode("hostleaf5")));
			// internal nodes
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("hostleaf1").getParentEdge(), this.testCase1.getHostTree().getNode("hostleaf2").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("InternalNode_00002").getParentEdge(), this.testCase1.getHostTree().getNode("InternalNode_00001").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch2));
			Edge duplication1 = this.testCase1.getHostTree().getNode("InternalNode_00002").getParentEdge();
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(duplication1));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase1.getHostTree().getNode("hostleaf5").getParentEdge(), this.testCase1.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase1Scenerio.add(this.testCase1.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(hostSwitch3));
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	public void LoadTestCase2WithFirstOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase2FileName);
			this.testCase2 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00001"), 6);
			this.testCase2.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase2Scenerio = new ParasiteMappingList();
			
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase2.getHostTree().getNode("l1")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase2.getHostTree().getNode("l2")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase2.getHostTree().getNode("l3")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase2.getHostTree().getNode("l4")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase2.getHostTree().getNode("l5")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase2.getHostTree().getNode("l6")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase2.getHostTree().getNode("l7")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase2.getHostTree().getNode("l8")));
			
			Node codivergence1 = this.testCase2.getHostTree().getNode("InternalNode_00001");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00002").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00001").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("l5").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(hostSwitch2));
			Node codivergence2 = this.testCase2.getHostTree().getNode("InternalNode_00004");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(codivergence2));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00004").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(hostSwitch3));
			Node codivergence3 = this.testCase2.getHostTree().getNode("InternalNode_00005");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence3));
			Pair<Edge, Edge> hostSwitch4 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00005").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00004").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(hostSwitch4));
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	public void LoadTestCase2WithSecondOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase2FileName);
			this.testCase2 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00002"), 6);
			this.testCase2.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase2Scenerio = new ParasiteMappingList();
			
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase2.getHostTree().getNode("l1")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase2.getHostTree().getNode("l2")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase2.getHostTree().getNode("l3")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase2.getHostTree().getNode("l4")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase2.getHostTree().getNode("l5")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase2.getHostTree().getNode("l6")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase2.getHostTree().getNode("l7")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase2.getHostTree().getNode("l8")));
			
			Node codivergence1 = this.testCase2.getHostTree().getNode("InternalNode_00001");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00002").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00001").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("l5").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(hostSwitch2));
			Node codivergence2 = this.testCase2.getHostTree().getNode("InternalNode_00004");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(codivergence2));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00004").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(hostSwitch3));
			Node codivergence3 = this.testCase2.getHostTree().getNode("InternalNode_00005");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence3));
			Pair<Edge, Edge> hostSwitch4 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00005").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00004").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(hostSwitch4));
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	public void LoadTestCase2WithThirdOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase2FileName);
			this.testCase2 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00002"), 6);
			this.testCase2.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase2Scenerio = new ParasiteMappingList();
			
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase2.getHostTree().getNode("l1")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase2.getHostTree().getNode("l2")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase2.getHostTree().getNode("l3")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase2.getHostTree().getNode("l4")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase2.getHostTree().getNode("l5")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase2.getHostTree().getNode("l6")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase2.getHostTree().getNode("l7")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase2.getHostTree().getNode("l8")));
			
			Node codivergence1 = this.testCase2.getHostTree().getNode("InternalNode_00001");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00002").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00001").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("l5").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(hostSwitch2));
			Node codivergence2 = this.testCase2.getHostTree().getNode("InternalNode_00004");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(codivergence2));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00004").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(hostSwitch3));
			Node codivergence3 = this.testCase2.getHostTree().getNode("InternalNode_00005");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence3));
			Pair<Edge, Edge> hostSwitch4 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00005").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00004").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(hostSwitch4));
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	public void LoadTestCase2WithDifferentReconstructionWithFirstOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase2FileName);
			this.testCase2 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.testCase2.getHostTree().getNode("InternalNode_00001"), 6);
			this.testCase2.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase2Scenerio = new ParasiteMappingList();
			
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase2.getHostTree().getNode("l1")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase2.getHostTree().getNode("l2")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase2.getHostTree().getNode("l3")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase2.getHostTree().getNode("l4")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase2.getHostTree().getNode("l5")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase2.getHostTree().getNode("l6")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase2.getHostTree().getNode("l7")));
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase2.getHostTree().getNode("l8")));
			
			Node codivergence1 = this.testCase2.getHostTree().getNode("InternalNode_00001");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00001").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00002").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00006").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(hostSwitch2));
			Edge duplication1 = this.testCase2.getHostTree().getNode("InternalNode_00006").getParentEdge();
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(duplication1));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase2.getHostTree().getNode("InternalNode_00006").getParentEdge(), this.testCase2.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(hostSwitch3));
			Node codivergence2 = this.testCase2.getHostTree().getNode("InternalNode_00005");
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence2));
			Edge duplication2 = this.testCase2.getHostTree().getNode("InternalNode_00006").getParentEdge();
			this.testCase2Scenerio.add(this.testCase2.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(duplication2));
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	public void LoadTestCase3WithWithFirstOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase3FileName);
			this.testCase3 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00007"), 9);
			this.testCase3.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase3Scenerio = new ParasiteMappingList();
			
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase3.getHostTree().getNode("l1")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase3.getHostTree().getNode("l2")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase3.getHostTree().getNode("l3")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase3.getHostTree().getNode("l4")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase3.getHostTree().getNode("l5")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase3.getHostTree().getNode("l6")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase3.getHostTree().getNode("l7")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase3.getHostTree().getNode("l7")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l9"), new MappingPoint(this.testCase3.getHostTree().getNode("l8")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l10"), new MappingPoint(this.testCase3.getHostTree().getNode("l9")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l11"), new MappingPoint(this.testCase3.getHostTree().getNode("l10")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l12"), new MappingPoint(this.testCase3.getHostTree().getNode("l11")));
			
			
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l4").getParentEdge(), this.testCase3.getHostTree().getNode("l3").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l5").getParentEdge(), this.testCase3.getHostTree().getNode("l4").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch2));
			Node codivergence1 = this.testCase3.getHostTree().getNode("InternalNode_00003");
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l7").getParentEdge(), this.testCase3.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(hostSwitch3));
			Edge duplication1 = this.testCase3.getHostTree().getNode("l7").getParentEdge();
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(duplication1));	
			Node codivergence2 = this.testCase3.getHostTree().getNode("InternalNode_00007");
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence2));
			Pair<Edge, Edge> hostSwitch4 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l7").getParentEdge(), this.testCase3.getHostTree().getNode("InternalNode_00008").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(hostSwitch4));
			Pair<Edge, Edge> hostSwitch5 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l2").getParentEdge(), this.testCase3.getHostTree().getNode("l7").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00008"), new MappingPoint(hostSwitch5));
			Pair<Edge, Edge> hostSwitch6 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l2").getParentEdge(), this.testCase3.getHostTree().getNode("InternalNode_00008").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00009"), new MappingPoint(hostSwitch6));		
			Node codivergence3 = this.testCase3.getHostTree().getNode("InternalNode_00001");
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00010"), new MappingPoint(codivergence3));
			Pair<Edge, Edge> hostSwitch7 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("InternalNode_00009").getParentEdge(), this.testCase3.getHostTree().getNode("l11").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00011"), new MappingPoint(hostSwitch7));			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	public void LoadTestCase3WithWithSecondOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase3FileName);
			this.testCase3 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00001"), 8);
			nodes.put(this.testCase3.getHostTree().getNode("InternalNode_00007"), 9);
			this.testCase3.getHostTree().setUniqueNodeTimings(nodes);
			
			this.testCase3Scenerio = new ParasiteMappingList();
			
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase3.getHostTree().getNode("l1")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase3.getHostTree().getNode("l2")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase3.getHostTree().getNode("l3")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase3.getHostTree().getNode("l4")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase3.getHostTree().getNode("l5")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase3.getHostTree().getNode("l6")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase3.getHostTree().getNode("l7")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase3.getHostTree().getNode("l7")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l9"), new MappingPoint(this.testCase3.getHostTree().getNode("l8")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l10"), new MappingPoint(this.testCase3.getHostTree().getNode("l9")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l11"), new MappingPoint(this.testCase3.getHostTree().getNode("l10")));
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("l12"), new MappingPoint(this.testCase3.getHostTree().getNode("l11")));
			
			
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l4").getParentEdge(), this.testCase3.getHostTree().getNode("l3").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l5").getParentEdge(), this.testCase3.getHostTree().getNode("l4").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch2));
			Node codivergence1 = this.testCase3.getHostTree().getNode("InternalNode_00003");
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l7").getParentEdge(), this.testCase3.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(hostSwitch3));
			Edge duplication1 = this.testCase3.getHostTree().getNode("l7").getParentEdge();
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(duplication1));	
			Node codivergence2 = this.testCase3.getHostTree().getNode("InternalNode_00007");
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence2));
			Pair<Edge, Edge> hostSwitch4 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l7").getParentEdge(), this.testCase3.getHostTree().getNode("InternalNode_00007").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(hostSwitch4));
			Pair<Edge, Edge> hostSwitch5 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l2").getParentEdge(), this.testCase3.getHostTree().getNode("l7").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00008"), new MappingPoint(hostSwitch5));
			Pair<Edge, Edge> hostSwitch6 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("l2").getParentEdge(), this.testCase3.getHostTree().getNode("InternalNode_00008").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00009"), new MappingPoint(hostSwitch6));		
			Node codivergence3 = this.testCase3.getHostTree().getNode("InternalNode_00001");
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00010"), new MappingPoint(codivergence3));
			Pair<Edge, Edge> hostSwitch7 = new Pair<Edge, Edge>(this.testCase3.getHostTree().getNode("InternalNode_00009").getParentEdge(), this.testCase3.getHostTree().getNode("l11").getParentEdge());
			this.testCase3Scenerio.add(this.testCase3.getParasiteTree().getNode("InternalNode_00011"), new MappingPoint(hostSwitch7));			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}	
	
	public void LoadTestCase4WithWithFirstOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase4FileName);
			this.testCase4 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00013"), 0);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00011"), 2);			
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00008"), 4);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00007"), 8);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00003"), 9);
			this.testCase4.getHostTree().setUniqueNodeTimings(nodes);	
			
			this.testCase4Scenerio = new ParasiteMappingList();
			
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase4.getHostTree().getNode("l1")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase4.getHostTree().getNode("l2")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase4.getHostTree().getNode("l3")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase4.getHostTree().getNode("l4")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase4.getHostTree().getNode("l5")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase4.getHostTree().getNode("l6")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase4.getHostTree().getNode("l7")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase4.getHostTree().getNode("l7")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l9"), new MappingPoint(this.testCase4.getHostTree().getNode("l8")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l10"), new MappingPoint(this.testCase4.getHostTree().getNode("l9")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l11"), new MappingPoint(this.testCase4.getHostTree().getNode("l10")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l12"), new MappingPoint(this.testCase4.getHostTree().getNode("l14")));
			
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l3").getParentEdge(), this.testCase4.getHostTree().getNode("l4").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l5").getParentEdge(), this.testCase4.getHostTree().getNode("l3").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch2));
			Node codivergence1 = this.testCase4.getHostTree().getNode("InternalNode_00003");	
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l7").getParentEdge(), this.testCase4.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(hostSwitch3));	
			Edge duplication1 = this.testCase4.getHostTree().getNode("l7").getParentEdge();
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(duplication1));
			Node codivergence2 = this.testCase4.getHostTree().getNode("InternalNode_00007");	
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence2));
			Pair<Edge, Edge> hostSwitch4 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l7").getParentEdge(), this.testCase4.getHostTree().getNode("InternalNode_00007").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(hostSwitch4));
			Pair<Edge, Edge> hostSwitch5 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l2").getParentEdge(), this.testCase4.getHostTree().getNode("l7").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00008"), new MappingPoint(hostSwitch5));
			Pair<Edge, Edge> hostSwitch6 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l2").getParentEdge(), this.testCase4.getHostTree().getNode("l10").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00009"), new MappingPoint(hostSwitch6));
			Pair<Edge, Edge> hostSwitch7 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l2").getParentEdge(), this.testCase4.getHostTree().getNode("l1").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00010"), new MappingPoint(hostSwitch7));	
			Pair<Edge, Edge> hostSwitch8 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("InternalNode_00009").getParentEdge(), this.testCase4.getHostTree().getNode("InternalNode_00012").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00011"), new MappingPoint(hostSwitch8));				
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}
	}
	
	public void LoadTestCase4WithWithSecondOrderingExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase4FileName);
			this.testCase4 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00013"), 0);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00009"), 2);			
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00011"), 7);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00010"), 8);
			nodes.put(this.testCase4.getHostTree().getNode("InternalNode_00003"), 9);
			this.testCase4.getHostTree().setUniqueNodeTimings(nodes);	
			
			this.testCase4Scenerio = new ParasiteMappingList();
			
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l1"), new MappingPoint(this.testCase4.getHostTree().getNode("l1")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l2"), new MappingPoint(this.testCase4.getHostTree().getNode("l2")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l3"), new MappingPoint(this.testCase4.getHostTree().getNode("l3")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l4"), new MappingPoint(this.testCase4.getHostTree().getNode("l4")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l5"), new MappingPoint(this.testCase4.getHostTree().getNode("l5")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l6"), new MappingPoint(this.testCase4.getHostTree().getNode("l6")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l7"), new MappingPoint(this.testCase4.getHostTree().getNode("l7")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l8"), new MappingPoint(this.testCase4.getHostTree().getNode("l7")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l9"), new MappingPoint(this.testCase4.getHostTree().getNode("l8")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l10"), new MappingPoint(this.testCase4.getHostTree().getNode("l9")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l11"), new MappingPoint(this.testCase4.getHostTree().getNode("l10")));
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("l12"), new MappingPoint(this.testCase4.getHostTree().getNode("l14")));
			
			Pair<Edge, Edge> hostSwitch1 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l3").getParentEdge(), this.testCase4.getHostTree().getNode("l4").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00001"), new MappingPoint(hostSwitch1));
			Pair<Edge, Edge> hostSwitch2 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l5").getParentEdge(), this.testCase4.getHostTree().getNode("l3").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00002"), new MappingPoint(hostSwitch2));
			Node codivergence1 = this.testCase4.getHostTree().getNode("InternalNode_00003");	
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00003"), new MappingPoint(codivergence1));
			Pair<Edge, Edge> hostSwitch3 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l7").getParentEdge(), this.testCase4.getHostTree().getNode("InternalNode_00003").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00004"), new MappingPoint(hostSwitch3));	
			Edge duplication1 = this.testCase4.getHostTree().getNode("l7").getParentEdge();
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00005"), new MappingPoint(duplication1));
			Node codivergence2 = this.testCase4.getHostTree().getNode("InternalNode_00007");	
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00006"), new MappingPoint(codivergence2));
			Pair<Edge, Edge> hostSwitch4 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l7").getParentEdge(), this.testCase4.getHostTree().getNode("InternalNode_00007").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00007"), new MappingPoint(hostSwitch4));
			Pair<Edge, Edge> hostSwitch5 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l2").getParentEdge(), this.testCase4.getHostTree().getNode("l7").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00008"), new MappingPoint(hostSwitch5));
			Pair<Edge, Edge> hostSwitch6 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l2").getParentEdge(), this.testCase4.getHostTree().getNode("l10").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00009"), new MappingPoint(hostSwitch6));
			Pair<Edge, Edge> hostSwitch7 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("l2").getParentEdge(), this.testCase4.getHostTree().getNode("l1").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00010"), new MappingPoint(hostSwitch7));	
			Pair<Edge, Edge> hostSwitch8 = new Pair<Edge, Edge>(this.testCase4.getHostTree().getNode("InternalNode_00009").getParentEdge(), this.testCase4.getHostTree().getNode("InternalNode_00012").getParentEdge());
			this.testCase4Scenerio.add(this.testCase4.getParasiteTree().getNode("InternalNode_00011"), new MappingPoint(hostSwitch8));				
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}		
	}	
	
	@Test
	public void CompletePushForwardTest1() {
		LoadTestCase1WithFirstOrderingExample();
		LossCounter counter = new LossCounter(this.testCase1, this.testCase1Scenerio);
		assertEquals(3, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase1.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase1.getHostTree().getNode("InternalNode_00002")));		
	}
	
	@Test
	public void CompletePushForwardTest2() {
		LoadTestCase1WithThirdOrderingExample();
		LossCounter counter = new LossCounter(this.testCase1, this.testCase1Scenerio);
		assertEquals(4, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase1.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase1.getHostTree().getNode("InternalNode_00002")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase1.getHostTree().getNode("InternalNode_00001")));	
	}	
	
	@Test
	public void CompletePushForwardTest3() {
		LoadTestCase2WithFirstOrderingExample();
		LossCounter counter = new LossCounter(this.testCase2, this.testCase2Scenerio);
		assertEquals(3, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00002")));
	}	
	
	@Test
	public void CompletePushForwardTest4() {
		LoadTestCase2WithSecondOrderingExample();
		LossCounter counter = new LossCounter(this.testCase2, this.testCase2Scenerio);
		assertEquals(3, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00002")));
	}		

	@Test
	public void PartailPushForwardTest1() {
		LoadTestCase1WithSecondOrderingExample();
		LossCounter counter = new LossCounter(this.testCase1, this.testCase1Scenerio);
		assertEquals(3, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase1.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase1.getHostTree().getNode("InternalNode_00002")));		
	}
	
	@Test
	public void PartailPushForwardTest2() {
		LoadTestCase2WithThirdOrderingExample();
		LossCounter counter = new LossCounter(this.testCase2, this.testCase2Scenerio);
		assertEquals(3, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00002")));		
	}	
	
	@Test
	public void PartailPushForwardTest3() {
		LoadTestCase3WithWithFirstOrderingExample();
		LossCounter counter = new LossCounter(this.testCase3, this.testCase3Scenerio);
		assertEquals(5, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00002")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00006")));		
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00008")));		
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00009")));
	}	
	
	@Test
	public void PartailPushForwardTest4() {
		LoadTestCase3WithWithSecondOrderingExample();
		LossCounter counter = new LossCounter(this.testCase3, this.testCase3Scenerio);
		assertEquals(4, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00002")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00006")));		
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00008")));		
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase3.getHostTree().getNode("InternalNode_00009")));
	}	
	
	@Test
	public void PartailPushForwardTest5() {
		LoadTestCase4WithWithSecondOrderingExample();
		LossCounter counter = new LossCounter(this.testCase4, this.testCase4Scenerio);
		assertEquals(7, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00001")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00002")));	
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00006")));	
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00009")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00010")));	
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00011")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00012")));	
	}		
	
	@Test
	public void MultipleHopsForwardTest1() {
		LoadTestCase2WithDifferentReconstructionWithFirstOrderingExample();
		LossCounter counter = new LossCounter(this.testCase2, this.testCase2Scenerio);
		assertEquals(9, counter.countNumberOfLossEvents());
		assertEquals((Integer) 3, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00006")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00004")));	
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00003")));
		assertEquals((Integer) 2, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase2.getHostTree().getNode("InternalNode_00002")));			
	}
	
	@Test
	public void MultipleHopsForwardTest2() {
		LoadTestCase4WithWithFirstOrderingExample();
		LossCounter counter = new LossCounter(this.testCase4, this.testCase4Scenerio);
		assertEquals(7, counter.countNumberOfLossEvents());
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00001")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00002")));	
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00006")));	
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00009")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00010")));	
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00011")));
		assertEquals((Integer) 1, counter.getHostMapping().numberOfLossEventsAtNode(this.testCase4.getHostTree().getNode("InternalNode_00012")));		
	}	
}
