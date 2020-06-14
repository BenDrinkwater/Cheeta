package association;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;

import tree.RootedBifurcatingNode;
import tree.Tree;
import tree.TreeConstructor;

public class AssociationTest {

	@Test
	public void ConsturctionTest() {
		Association association = new CophylogeneticAssociation(new RootedBifurcatingNode("Node1"), new RootedBifurcatingNode("Node2"));
		assertNotNull(association);
	}
	
	@Test
	public void GetLeftNodeTest() {
		Association association = new CophylogeneticAssociation(new RootedBifurcatingNode("Node1"), new RootedBifurcatingNode("Node2"));
		assertNotNull(association.getFirstNode());
		assertEquals(new RootedBifurcatingNode("Node1"), association.getFirstNode());
	}
	
	@Test
	public void GetRightNodeTest() {
		Association association = new CophylogeneticAssociation(new RootedBifurcatingNode("Node1"), new RootedBifurcatingNode("Node2"));
		assertNotNull(association.getSecondNode());
		assertEquals(new RootedBifurcatingNode("Node2"), association.getSecondNode());		
	}
	
	@Test
	public void EqualityTestWithLeafNodes() {
		Tree syntheticTree = GetTreeForEqualityTest();
		assertNotNull(syntheticTree);
		Tree clonedTree = syntheticTree.cloneTree("Parasite");
		assertNotNull(clonedTree);
		
		Association association1 = new CophylogeneticAssociation(syntheticTree.getNode("O"), clonedTree.getNode("O"));
		Association association2 = new CophylogeneticAssociation(syntheticTree.getNode("O"), clonedTree.getNode("O"));
		
		assertEquals(association2, association1);
		assertEquals(association1, association2);
		
		Association association3 = new CophylogeneticAssociation(clonedTree.getNode("O"), syntheticTree.getNode("O"));	
		
		assertEquals(false, association1.equals(association3));
		assertEquals(false, association3.equals(association1));
	}
	
	@Test
	public void EqualityTestWitRootNodes() {
		Tree syntheticTree = GetTreeForEqualityTest();
		assertNotNull(syntheticTree);
		Tree clonedTree = syntheticTree.cloneTree("Parasite");
		assertNotNull(clonedTree);
		
		Association association1 = new CophylogeneticAssociation(syntheticTree.getNode("A"), clonedTree.getNode("A"));
		Association association2 = new CophylogeneticAssociation(syntheticTree.getNode("A"), clonedTree.getNode("A"));
		
		assertEquals(association2, association1);
		assertEquals(association1, association2);
		
		Association association3 = new CophylogeneticAssociation(clonedTree.getNode("A"), syntheticTree.getNode("A"));	
		
		assertEquals(false, association1.equals(association3));
		assertEquals(false, association3.equals(association1));		
	}
	
	@Test
	public void EqualityTestWitInternalNodes() {
		
		Tree syntheticTree = GetTreeForEqualityTest();
		assertNotNull(syntheticTree);
		Tree clonedTree = syntheticTree.cloneTree("Parasite");
		assertNotNull(clonedTree);
		
		Association association1 = new CophylogeneticAssociation(syntheticTree.getNode("D"), clonedTree.getNode("D"));
		Association association2 = new CophylogeneticAssociation(syntheticTree.getNode("D"), clonedTree.getNode("D"));
		
		assertEquals(association2, association1);
		assertEquals(association1, association2);
		
		Association association3 = new CophylogeneticAssociation(clonedTree.getNode("D"), syntheticTree.getNode("D"));	
		
		assertEquals(false, association1.equals(association3));
		assertEquals(false, association3.equals(association1));	
	}
	
	@Test
	public void EqualityTestWitNull() {
		
		Association association1 = new CophylogeneticAssociation(new RootedBifurcatingNode("Node1"), new RootedBifurcatingNode("Node2"));
		Association association2 = null;
		
		assertEquals(false, association1.equals(association2));
		
	}
	
	@Test
	public void EqualityTestWitNullNodes() {
		
		Association association1 = new CophylogeneticAssociation(null, null);
		Association association2 = new CophylogeneticAssociation(null, new RootedBifurcatingNode("Node2"));
		Association association3 = new CophylogeneticAssociation(new RootedBifurcatingNode("Node1"), null);
		
		assertEquals(false, association1.equals(association2));
		assertEquals(false, association2.equals(association1));
		assertEquals(false, association2.equals(association3));
		assertEquals(false, association3.equals(association2));		
		assertEquals(true, association1.equals(association1));		
	}
	
	@Test
	public void EqualityTestWitNonAssociationInstance() {
		Association association1 = new CophylogeneticAssociation(new RootedBifurcatingNode("Node1"), new RootedBifurcatingNode("Node2"));
		String association2 = "Hello World";
		
		assertEquals(false, association1.equals(association2));
	}
	
	public static Tree GetTreeForEqualityTest() {
		Tree tree = TreeConstructor.createNewTree("A", "Host");
		tree.addChildren("A", "B", 1, "C", 1);
		tree.addChildren("B", "D", 9, "E", 21);
		tree.addChildren("C", "F", 6, "G", 1);
		tree.addChildren("D", "H", 0, "I", 1);
		tree.addChildren("I", "J", 82, "K", 1);
		tree.addChildren("K", "L", 1, "M", 1);
		tree.addChildren("L", "N", 1, "O", 1);
		return tree;
	}	
	
	@Test 
	public void CompareLessThanTest() {
		Tree syntheticTree = GetTreeForEqualityTest();
		assertNotNull(syntheticTree);
		Tree clonedTree = syntheticTree.cloneTree("Parasite");
		assertNotNull(clonedTree);
		
		Association association1 = new CophylogeneticAssociation(syntheticTree.getNode("M"), clonedTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(syntheticTree.getNode("O"), clonedTree.getNode("O"));	
		
		assertEquals(-1, association1.compareTo(association2));
	}
	
	@Test 
	public void CompareEqualToTest() {
		Tree syntheticTree = GetTreeForEqualityTest();
		assertNotNull(syntheticTree);
		Tree clonedTree = syntheticTree.cloneTree("Parasite");
		assertNotNull(clonedTree);
		
		Association association1 = new CophylogeneticAssociation(syntheticTree.getNode("O"), clonedTree.getNode("O"));
		Association association2 = new CophylogeneticAssociation(syntheticTree.getNode("O"), clonedTree.getNode("O"));	
		
		assertEquals(0, association1.compareTo(association2));		
	}
	
	@Test 
	public void CompareGreaterThanTest() {
		Tree syntheticTree = GetTreeForEqualityTest();
		assertNotNull(syntheticTree);
		Tree clonedTree = syntheticTree.cloneTree("Parasite");
		assertNotNull(clonedTree);
		
		Association association1 = new CophylogeneticAssociation(syntheticTree.getNode("M"), clonedTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(syntheticTree.getNode("F"), clonedTree.getNode("F"));	
		
		assertEquals(1, association1.compareTo(association2));		
	}	
	
	@Test 
	public void SortTest() {
		Tree syntheticTree = GetTreeForEqualityTest();
		assertNotNull(syntheticTree);
		Tree clonedTree = syntheticTree.cloneTree("Parasite");
		assertNotNull(clonedTree);		
		
		LinkedList<Association> associations = new LinkedList<Association>();
		associations.add(new CophylogeneticAssociation(syntheticTree.getNode("M"), clonedTree.getNode("M")));
		associations.add(new CophylogeneticAssociation(syntheticTree.getNode("N"), clonedTree.getNode("N")));
		associations.add(new CophylogeneticAssociation(syntheticTree.getNode("O"), clonedTree.getNode("O")));
		associations.add(new CophylogeneticAssociation(syntheticTree.getNode("H"), clonedTree.getNode("H")));
		associations.add(new CophylogeneticAssociation(syntheticTree.getNode("F"), clonedTree.getNode("F")));
		associations.add(new CophylogeneticAssociation(syntheticTree.getNode("J"), clonedTree.getNode("J")));
		
		Collections.sort(associations);
		
		assertEquals("Parasite_F:Host_F", associations.getFirst().toString());
		associations.removeFirst();
		assertEquals("Parasite_H:Host_H", associations.getFirst().toString());
		associations.removeFirst();
		assertEquals("Parasite_J:Host_J", associations.getFirst().toString());
		associations.removeFirst();
		assertEquals("Parasite_M:Host_M", associations.getFirst().toString());
		associations.removeFirst();
		assertEquals("Parasite_N:Host_N", associations.getFirst().toString());
		associations.removeFirst();
		assertEquals("Parasite_O:Host_O", associations.getFirst().toString());
		associations.removeFirst();
		
	}		
	
	@Test
	public void ToStringTest() {
		Association association = new CophylogeneticAssociation(new RootedBifurcatingNode("Node1"), new RootedBifurcatingNode("Node2"));
		assertNotNull(association.toString());
		assertEquals("Node2:Node1", association.toString());
	}	
}
