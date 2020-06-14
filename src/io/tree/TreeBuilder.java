 package io.tree;

import tree.Tree;
import tree.TreeConstructor;

public class TreeBuilder {

	private String treeString;
	
	public TreeBuilder(String treeString) {
		this.treeString = treeString;
	}

	public Tree createTree(String prefix) {
		String[] lines = this.treeString.split("\n");
		
		Tree tree = null;
		
		for (String line : lines) {
			if (line.split("\t").length < 3) {
				continue;
			}
			String parentNode = line.split("\t")[0];
			String leftChild = line.split("\t")[1];
			String rightChild = line.split("\t")[2];
			
			if (null == tree) {
				tree = TreeConstructor.createNewTree(parentNode, prefix);
			}
			tree.addChildren(parentNode, leftChild, rightChild);
		}
		
		return tree;
	}

}
