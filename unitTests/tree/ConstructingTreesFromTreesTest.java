package tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConstructingTreesFromTreesTest {

	@Test
	public void SimpleTest() {
		Tree tree1 = new RootedBifurcatingTree("Root", "host");
		
		Tree tree2 = new RootedBifurcatingTree("A", "host");
		assertEquals(true, tree2.addChildren("A", "B", 3, "C", 4));
		assertEquals(true, tree2.addChildren("B", "D", 5, "E", 9));
		assertEquals(true, tree2.addChildren("C", "F", "G"));
		
		Tree tree3 = new RootedBifurcatingTree("Z", "host");
		assertEquals(true, tree3.addChildren("Z", "Y", 3, "X", 4));
		assertEquals(true, tree3.addChildren("Y", "W", 5, "V", 9));
		assertEquals(true, tree3.addChildren("X", "U", "T"));	
		
		assertEquals(true, tree1.addChildren("Root", tree2, tree3));
		
		assertEquals(15, tree1.size());
		assertEquals(8, tree1.numberOfLeafNodes());
		assertEquals(7, tree1.numberOfInteranlNodes());	
	}	
	
	@Test
	public void UsingTreeConstructorTest() {
		Tree tree1 = TreeConstructor.createNewTree("Root", "host");
	
		Tree tree2 = TreeConstructor.createNewTree("A", "host");
		assertEquals(true, tree2.addChildren("A", "B", 3, "C", 4));
		assertEquals(true, tree2.addChildren("B", "D", 5, "E", 9));
		assertEquals(true, tree2.addChildren("C", "F", "G"));
		
		Tree tree3 = TreeConstructor.createNewTree("Z", "host");
		assertEquals(true, tree3.addChildren("Z", "Y", 3, "X", 4));
		assertEquals(true, tree3.addChildren("Y", "W", 5, "V", 9));
		assertEquals(true, tree3.addChildren("X", "U", "T"));		
		
		assertEquals(true, tree1.addChildren("Root", tree2, tree3));
		
		assertEquals(15, tree1.size());
		assertEquals(8, tree1.numberOfLeafNodes());
		assertEquals(7, tree1.numberOfInteranlNodes());
	}
}
