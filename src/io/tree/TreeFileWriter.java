package io.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import association.Association;
import association.Associations;

import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class TreeFileWriter {

	protected File file;
	protected String outputString;
	protected CoevolutionaryHistory history;
	protected Map<String, Integer> referenceList;
	protected String parseString;
	
	public TreeFileWriter(CoevolutionaryHistory history, File file) {
		this.file = file;
		this.history = history;
		this.referenceList = new LinkedHashMap<String, Integer>();
	}
	
	protected void generateOutputString() {
		this.assignReferenceList();
		this.outputString = this.getHostTreeString() +  this.getParasiteTreeString() + this.getAssociationsString() + this.getRankings();
	}

	protected void assignReferenceList() {
		Node hostRoot = this.history.getHostTree().getRootNode();
		this.assignReferenceList(hostRoot);
		Node parasiteRoot = this.history.getParasiteTree().getRootNode();
		this.assignReferenceList(parasiteRoot);		
	}
	
	private void assignReferenceList(Node node) {
		this.referenceList.put(node.getNodeName(), this.referenceList.size() + 1);
		if (!node.isLeafNode()) {
			this.assignReferenceList(node.getLeftChild());
			this.assignReferenceList(node.getRightChild());
		}
	}	
	
	protected String getHostTreeString() {
		Node hostRoot = this.history.getHostTree().getRootNode();
		this.parseString = "HOSTTREE\n";
		this.getTreeString(hostRoot);
		return this.parseString + "\n\n" + this.getHostNames();
	}
	
	protected String getHostNames() {
		String str = "HOSTNAMES\n";
		for (Map.Entry<String, Integer> entrySet : this.referenceList.entrySet()) {
			if (this.history.getHostTree().getNodeWithPrefix(entrySet.getKey()) != null) {
				str += entrySet.getValue() + "\t" + entrySet.getKey() + "\n";
			}
		}
		return str;
	}
	
	private String getParasiteTreeString() {
		Node parasiteRoot = this.history.getParasiteTree().getRootNode();
		this.parseString = "\n\nPARASITETREE\n";
		this.getTreeString(parasiteRoot);
		return this.parseString + "\n\n" + this.getParasiteNames();
	}
	
	protected String getParasiteNames() {
		String str = "PARASITENAMES\n";
		for (Map.Entry<String, Integer> entrySet : this.referenceList.entrySet()) {
			if (this.history.getParasiteTree().getNodeWithPrefix(entrySet.getKey()) != null) {
				str += entrySet.getValue() + "\t" + entrySet.getKey() + "\n";
			}
		}
		return str;
	}
	
	private void getTreeString(Node node) {
		if (node.isLeafNode()) {
			this.parseString += this.referenceList.get(node.toString()) + "\tnull\n";
		}
		else {
			this.parseString += this.referenceList.get(node.toString()) + "\t" + this.referenceList.get(node.getLeftChild().toString()) + "\t" + this.referenceList.get(node.getRightChild().toString()) +"\n";
			this.getTreeString(node.getLeftChild());
			this.getTreeString(node.getRightChild());
		}
	}	

	private String getAssociationsString() {
		String str = "\n\nPHI\n";
		for (Node node : this.history.getHostTree().getAllNodes()) {
			if (node.isLeafNode()) {
				Associations associations = this.history.getAssociations().getAssociationsForHostNode(node);
				if (associations.size() > 0) {
					str += this.referenceList.get(associations.iterator().next().getFirstNode().toString());
					for (Association association : associations) {
						str += "\t" + this.referenceList.get(association.getSecondNode().toString());
					}
					str += "\n";
				}
			}
		}
		return str;
	}
	
	private String getRankings() {
		String str = "\n\nHOSTRANKS\n";
		for (Map.Entry<String, Integer> entrySet : this.referenceList.entrySet()) {
			if (this.history.getHostTree().getNodeWithPrefix(entrySet.getKey()) != null) {
				str += entrySet.getValue() + "\t1\n";
			}
		}
		str += "\n\nPARASITERANKS\n";
		for (Map.Entry<String, Integer> entrySet : this.referenceList.entrySet()) {
			if (this.history.getParasiteTree().getNodeWithPrefix(entrySet.getKey()) != null) {
				str += entrySet.getValue() + "\t1\n";
			}
		}		
		return str;
	}

	public boolean write() {
		this.generateOutputString();
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(this.file));
	        out.write(this.outputString);
	        out.close();
	    } 
		catch (IOException e) {
			return false;
		}
		catch (NullPointerException e) {
			return false;
	    }
		return true;
	}	
}
