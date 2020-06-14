package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TreeCloneTest {

	@Test
	public void CloneTest1() {
		Tree originalTree = this.TestTree();
		Tree clonedTree = originalTree.cloneTree();
		assertNotNull(clonedTree);
		assertEquals(15, clonedTree.size());
		assertEquals(originalTree, clonedTree);
	}
	
	@Test
	public void CloneTest2() {
		Tree originalTree = this.TestTree();
		Tree clonedTree = originalTree.cloneTree("parasite");
		assertNotNull(clonedTree);
		assertEquals(15, clonedTree.size());
		assertEquals(false, originalTree.equals(clonedTree));
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
	
	private Tree TestTreeWithDistances() {
		Tree tree = TreeConstructor.createNewTree("A", "host");
		tree.addChildren("A", "B", 1, "C", 2);
		tree.addChildren("B", "D", 1, "E", 5);
		tree.addChildren("C", "F", 4, "G", 4);
		tree.addChildren("D", "H", 4, "I", 1);
		tree.addChildren("I", "J", 3, "K", 1);
		tree.addChildren("K", "L", 1, "M", 2);
		tree.addChildren("L", "N", 1, "O", 1);
		return tree;
	}		
}
