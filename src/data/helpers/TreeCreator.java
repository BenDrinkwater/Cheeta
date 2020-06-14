package data.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tree.Node;
import tree.RootedBifurcatingTree;
import tree.Tree;
import tree.TreeConstructor;

public class TreeCreator {

	private RootedBifurcatingTree finalTree;
	private Map<String,Integer> count;
	
	public TreeCreator() {
		this.finalTree = (RootedBifurcatingTree) TreeConstructor.createNewTree("root", "tree");
		this.count = new HashMap<String,Integer>();
	}
	
	public TreeCreator(Tree tree) {
		this();
		this.finalTree = (RootedBifurcatingTree) tree;
	}	
	
	public Tree buildTree(int numberOfLeafNodes) {
		int index = 1;
		while (this.finalTree.numberOfLeafNodes() < numberOfLeafNodes) {
			List<Node> leaves = this.finalTree.getLeafNodesList();
			Node parent = this.pickANodeAtRandom(leaves);
			this.finalTree.addChildren(parent.getNodeName().substring(5), this.getNextNodeName(index++), this.getNextNodeName(index++));
		}
		return this.finalTree;
	}
	
	public int getHeightOfTree() {
		int maxHeight = 0;
		for (Node leafNode : this.finalTree.getLeafNodes()) {
			maxHeight = Math.max(maxHeight, this.finalTree.distanceBetweenNodes(this.finalTree.getRootNode(), leafNode) + 1);
		}
		return maxHeight;
	}
	
	public List<Integer> getNumberOfNodesAtEachLevel() {
		List<Integer> numberOfNodesAtEachLevel = new ArrayList<Integer>();
		for (int i = 0; i < this.getHeightOfTree()+1; ++i) {
			numberOfNodesAtEachLevel.add(0);
		}
		for (Node node : this.finalTree.getLeafNodes()) {
			this.updateNodeOnLeafChain(node);
		}
		for (Map.Entry<String,Integer> entry : this.count.entrySet()) {
			numberOfNodesAtEachLevel.set(entry.getValue(), numberOfNodesAtEachLevel.get(entry.getValue()) + 1);
		}
		return numberOfNodesAtEachLevel;
	}
	
	private void updateNodeOnLeafChain(Node startNode) {
		Node current = startNode;
		int count = 0;
		while(!current.isRootNode()) {
			if (this.count.containsKey(current.getNodeName())) {
				this.count.put(current.getNodeName(), Math.max(this.count.get(current.getNodeName()), count));
			}
			else {
				this.count.put(current.getNodeName(), count);
			}
			current = current.getParent();
			count++;
		}
		if (this.count.containsKey(current.getNodeName())) {
			this.count.put(current.getNodeName(), Math.max(this.count.get(current.getNodeName()), count));
		}
		else {
			this.count.put(current.getNodeName(), count);
		}
	}	
	
	private Node pickANodeAtRandom(List<Node> leaves) {
		Random random = new Random();
		int index = random.nextInt(leaves.size());
		return leaves.get(index);
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
