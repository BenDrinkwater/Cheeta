package data;

import org.junit.Test;

import data.helpers.TreeCreator;

import tree.RootedBifurcatingTree;
import tree.TreeConstructor;

public class TreeCreatorTest {

	@Test
	public void Test() {
		RootedBifurcatingTree tree = (RootedBifurcatingTree) TreeConstructor.createNewTree("root", "tree");
		tree.addChildren("root", this.getNextNodeName(1), this.getNextNodeName(2));
		tree.addChildren("Node0002", this.getNextNodeName(3), this.getNextNodeName(4));
		tree.addChildren("Node0004", this.getNextNodeName(5), this.getNextNodeName(6));
		tree.addChildren("Node0005", this.getNextNodeName(7), this.getNextNodeName(8));
		tree.addChildren("Node0003", this.getNextNodeName(9), this.getNextNodeName(10));
		tree.addChildren("Node0006", this.getNextNodeName(11), this.getNextNodeName(12));
		tree.addChildren("Node0012", this.getNextNodeName(13), this.getNextNodeName(14));
		TreeCreator treeCreator = new TreeCreator(tree);
		treeCreator.getNumberOfNodesAtEachLevel();
	}
	
	private String getNextNodeName(int index) {
		String str = "Node";
		if (index < 10) {
			return str + "000" + index;	
		}
		else if (index < 100) {
			return str + "00" + index;			
		}
		else if (index < 1000) {
			return str + "0" + index;			
		}
		else {
			return str + index;
		}
	}	
	
}
