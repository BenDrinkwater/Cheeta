package association;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import tree.Tree;
import tree.TreeConstructor;

public class AssociationsTest {

	private Tree hostTree;
	private Tree parasiteTree;
	
	@Before
	public void CreateTwoTreesWeCanUseToMakeAssociationsWith() {
		this.hostTree = TreeConstructor.createNewTree("A", "Host");
		this.hostTree.addChildren("A", "B", 1, "C", 1);
		this.hostTree.addChildren("B", "D", 9, "E", 21);
		this.hostTree.addChildren("C", "F", 6, "G", 18);
		this.hostTree.addChildren("D", "H", 1, "I", 1);
		this.hostTree.addChildren("I", "J", 82, "K", 2);
		this.hostTree.addChildren("K", "L", 1, "M", 5);
		this.hostTree.addChildren("L", "N", 3, "O", 8);
		this.parasiteTree = this.hostTree.cloneTree("parasite");
	}
	
	@Test
	public void ConstructionTest() {
		Associations associations = new CophylogeneticAssociations();
		assertNotNull(associations);
	}
	
	@Test
	public void AddingElementsToTest() {
		Associations associations = new CophylogeneticAssociations();
		
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M")));
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O")));
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L")));
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E")));
		
		assertEquals(4, associations.size());
	}	
	
	@Test
	public void AddingNullAssocaitionsTest() {
		Associations associations = new CophylogeneticAssociations();
		
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M")));
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O")));
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L")));
		associations.add(new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E")));
		
		assertEquals(4, associations.size());
		
		associations.add(null);
		
		assertEquals(4, associations.size());
	}
	
	@Test
	public void ContainsElementsTest() {
		Associations associations = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("A"), this.parasiteTree.getNode("A"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association5 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations.add(association2);
		associations.add(association3);
		associations.add(association4);
		associations.add(association5);
		
		assertEquals(false, associations.contains(association1));
		assertEquals(true, associations.contains(association2));
		assertEquals(true, associations.contains(association3));
		assertEquals(true, associations.contains(association4));
		assertEquals(true, associations.contains(association5));
	}
	
	@Test
	public void DeleteTest() {
		
		Associations associations = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations.add(association1);
		associations.add(association2);
		associations.add(association3);
		associations.add(association4);	
		
		assertEquals(4, associations.size());
		assertEquals(true, associations.contains(association2));
		
		associations.delete(association2);
		
		assertEquals(3, associations.size());
		assertEquals(false, associations.contains(association2));
		
		associations.delete(association2);
		assertEquals(3, associations.size());
	}

	@Test
	public void AttemptToDeleteNullItemTest() {	
		Associations associations = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations.add(association1);
		associations.add(association2);
		associations.add(association3);
		associations.add(association4);	
		
		assertEquals(4, associations.size());
		
		associations.delete(null);
		
		assertEquals(4, associations.size());
	}
	
	@Test
	public void IteratorTest() {
		
		Associations associations = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations.add(association1);
		associations.add(association2);
		associations.add(association3);
		associations.add(association4);	
		
		Iterator<Association> iterator = associations.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(association4, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(association3, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(association1, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(association2, iterator.next());
		assertEquals(false, iterator.hasNext());		
	}
	
	@Test
	public void SpecialForLoopTest() {
		
		Associations associations = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations.add(association1);
		associations.add(association2);
		associations.add(association3);
		associations.add(association4);			
		
		int counter = 0;
		
		for (Association association : associations) {
			
			switch(counter) {		
				case 0 : assertEquals(association4, association);
					break;
				case 1 :assertEquals(association3, association);
					break;
				case 2 :assertEquals(association1, association);
					break;
				case 3: assertEquals(association2, association);
					break;
			}	
			counter++;
		}
	}
	
	@Test
	public void EqualityTest() {
		Associations associations1 = new CophylogeneticAssociations();
		Associations associations2 = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations1.add(association1);
		associations1.add(association2);
		associations1.add(association3);
		associations1.add(association4);
		
		associations2.add(association1);
		associations2.add(association2);
		associations2.add(association3);
		associations2.add(association4);	
		
		assertEquals(associations1, associations2);
	}
	
	@Test
	public void EqualityTestWithDifferentOrder() {
		Associations associations1 = new CophylogeneticAssociations();
		Associations associations2 = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations1.add(association3);
		associations1.add(association4);
		
		associations2.add(association1);
		associations2.add(association2);
		
		assertEquals(false, associations1.equals(associations2));
		assertEquals(false, associations2.equals(associations1));		
	}
	
	@Test
	public void EqualityTestWithDifferentNumberOfItemsOrder() {
		Associations associations1 = new CophylogeneticAssociations();
		Associations associations2 = new CophylogeneticAssociations(); 
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations1.add(association1);
		associations1.add(association2);
		associations1.add(association3);
		associations1.add(association4);
		
		associations2.add(association3);
		associations2.add(association4);
		
		assertEquals(false, associations1.equals(associations2));
		assertEquals(false, associations2.equals(associations1));
	}	
	
	@Test
	public void EqualityTestWithNull() {	
		
		Associations associations1 = new CophylogeneticAssociations();
		Associations associations2 = null;
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations1.add(association1);
		associations1.add(association2);
		associations1.add(association3);
		associations1.add(association4);
		
		assertEquals(false, associations1.equals(associations2));
	}
	
	@Test
	public void EqualityTestWithDifferentClassObject() {	
		
		Associations associations1 = new CophylogeneticAssociations();
		String associations2 = "Hello World";
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations1.add(association1);
		associations1.add(association2);
		associations1.add(association3);
		associations1.add(association4);
		
		assertEquals(false, associations1.equals(associations2));
	}
		
	
	@Test
	public void CloneTest() {
		Associations associations = new CophylogeneticAssociations();
		
		Association association1 = new CophylogeneticAssociation(this.hostTree.getNode("M"), this.parasiteTree.getNode("M"));
		Association association2 = new CophylogeneticAssociation(this.hostTree.getNode("O"), this.parasiteTree.getNode("O"));
		Association association3 = new CophylogeneticAssociation(this.hostTree.getNode("L"), this.parasiteTree.getNode("L"));
		Association association4 = new CophylogeneticAssociation(this.hostTree.getNode("E"), this.parasiteTree.getNode("E"));
		
		associations.add(association1);
		associations.add(association2);
		associations.add(association3);
		associations.add(association4);	
		
		Associations clonedAssociations =  associations.createCopy(this.hostTree.cloneTree(), this.parasiteTree.cloneTree());
		
		assertNotNull(clonedAssociations);
		
		assertEquals(associations, clonedAssociations);
	}
	
	@Test
	public void AssociationsConstructedFromCollectionTest1() {
		Tree syntheticTree = AssociationTest.GetTreeForEqualityTest();
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
		
		Associations assocs = new CophylogeneticAssociations(associations);
		assertEquals(6, assocs.size());
	}
	
	@Test
	public void AssociationsConstructedFromCollectionTest2() {
		Tree syntheticTree = AssociationTest.GetTreeForEqualityTest();
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
		
		Associations assocs = new CophylogeneticAssociations(associations);
		
		Iterator<Association> iterator = assocs.iterator();
		
		assertEquals("Parasite_F:Host_F", iterator.next().toString());
		assertEquals("Parasite_H:Host_H", iterator.next().toString());
		assertEquals("Parasite_J:Host_J", iterator.next().toString());
		assertEquals("Parasite_M:Host_M", iterator.next().toString());
		assertEquals("Parasite_N:Host_N", iterator.next().toString());
		assertEquals("Parasite_O:Host_O", iterator.next().toString());	
	}
	
	@Test
	public void AssociationsToStringTest() {
		Tree syntheticTree = AssociationTest.GetTreeForEqualityTest();
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
		Associations assocs = new CophylogeneticAssociations(associations);
		assertEquals("Host_F:Parasite_F\nHost_H:Parasite_H\nHost_J:Parasite_J\nHost_M:Parasite_M\nHost_N:Parasite_N\nHost_O:Parasite_O\n", assocs.toString());
	}
}
