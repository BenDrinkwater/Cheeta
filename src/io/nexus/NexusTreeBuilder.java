package io.nexus;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import tree.Tree;
import tree.TreeConstructor;

public class NexusTreeBuilder {
	
	private String tree;
	private int index;
	
	private NexusTreeBuilder() {
		this.index = 0;
	}
	
	protected NexusTreeBuilder(NexusString tree) {
		this();
		this.tree = tree.toString();
	}
	
	public Tree createTree(String prefix) throws NexusFormatException {
		return this.initiatePartialTree(prefix, this.tree, new LinkedHashMap<String, Tree>());
	}
	
	protected Tree initiatePartialTree(final String prefix, String treeString, Map<String, Tree> listOfInternalNodes) throws NexusFormatException {
		this.validateTree(treeString);
		if (null != treeString && treeString.contains("(")) {
			int indexOfClosingBrace = treeString.indexOf(')');
			int indexOfOpeningBrace = 0;
			for (int i = indexOfClosingBrace - 2; i >= 0; --i) {
				if (treeString.charAt(i) == '(') {
					indexOfOpeningBrace = i;
					break;
				}
			}
			String nodePair = treeString.substring(indexOfOpeningBrace+1, indexOfClosingBrace);
			
			String parentNode = this.getNextNodeName();
			Tree tree = TreeConstructor.createNewTree(parentNode, prefix);
			
			String[] nodePairSplit = nodePair.split(",");
			
			if (nodePairSplit.length != 2) {
				throw new NexusFormatException("Malformed " + prefix + "tree string");
			}
			
			String leftNode = nodePairSplit[0].trim();
			String rightNode = nodePairSplit[1].trim();
			
			if (listOfInternalNodes.containsKey(leftNode)) {
				if (listOfInternalNodes.containsKey(rightNode)) {
					tree.addChildren(parentNode, listOfInternalNodes.get(leftNode),listOfInternalNodes.get(rightNode));
				}
				else {
					Tree tempTree = TreeConstructor.createNewTree(rightNode.trim(), prefix);
					tree.addChildren(parentNode,  listOfInternalNodes.get(leftNode), tempTree);
				}
			}
			else if (listOfInternalNodes.containsKey(rightNode)) {
				Tree tempTree = TreeConstructor.createNewTree(leftNode, prefix);
				tree.addChildren(parentNode, tempTree, listOfInternalNodes.get(rightNode));
			}
			else {
				tree.addChildren(parentNode, leftNode, 1, rightNode, 1);
			}
			
			listOfInternalNodes.put(parentNode, tree);
			
			treeString = (specialReplaceFirst(treeString, nodePair, parentNode));
			return initiatePartialTree(prefix, treeString, listOfInternalNodes);
		}
		else {
			if (listOfInternalNodes.size() == 0) {
				throw new NexusFormatException("No Internal Nodes in Tree");
			}
			return new LinkedList<Tree>(listOfInternalNodes.values()).getLast();
		}
	}	
	
	protected void validateTree(final String tree) throws NexusFormatException {
		if (null == tree) {
			throw new NexusFormatException("Tree is missing from nexus file");
		}
		if (this.count(tree, '(') != this.count(tree, ')')) {
			throw new NexusFormatException("Braces mismatch in tree " + tree);
		}
		this.checkOrderCorrect(tree);
	}
	
	protected String specialReplaceFirst(final String initalString, final String searchString, final String replacementString) throws NexusFormatException {
		int index = initalString.indexOf(searchString);
		if (0 == index) {
			throw new NexusFormatException("Braces Mismatch in Tree");
		}
		String firstPart = initalString.substring(0, index - 1);
		String secondPart = initalString.substring(index + searchString.length() + 1);
		return firstPart + replacementString + secondPart;
	}	
	
	protected int count(final String tree, final char searchChar) {
		int numberFound = 0;
		for (char character : tree.toCharArray()) {
			if (character == searchChar) {
				numberFound++;
			}
		}
		return numberFound;
	}
	
	protected void checkOrderCorrect(final String tree)  throws NexusFormatException {
		int numberOfInwardBraces = 0;
		int numberOfOutwardBraces = 0;
		int numberOfCommas = 0;
		for (char character : tree.toCharArray()) {
			if (character == '(') {
				numberOfInwardBraces++;
			}
			else if (character == ')') {
				numberOfOutwardBraces++;
			}
			else if (character == ',') {
				numberOfCommas++;
			}			
			
			if (numberOfOutwardBraces > numberOfInwardBraces) {
				throw new NexusFormatException("Braces mismatch in tree " + tree); 
			}
			if (numberOfOutwardBraces > numberOfCommas) {
				throw new NexusFormatException("Comma mismatch in tree " + tree);
			}
			if (numberOfCommas > numberOfInwardBraces) {
				throw new NexusFormatException("Comma mismatch in tree " + tree);
			}			
		}		
	}
	
	protected String getNextNodeName() {
		String finalPart = null;
		if (++this.index < 10) {
			finalPart = "0000" + this.index;
		}
		else if (this.index < 100) {
			finalPart = "000" + this.index;
		}
		else if (this.index < 1000) {
			finalPart = "00" + this.index;
		}
		else if (this.index < 10000){
			finalPart = "0" + this.index;
		}
		else {
			finalPart = Integer.toString(this.index);
		}
		return "InternalNode_" + finalPart; 
	}
}
