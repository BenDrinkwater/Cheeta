package io.nexus.corepa;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import association.Association;

import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.Tree;

import io.configuration.Configuration;
import io.configuration.DefaultConfiguration;
import io.nexus.NexusFileWriter;
import io.nexus.NexusStringFormat;

public class CorePaNexusFileWriter extends NexusFileWriter {

	private Map<String, String> historyMapping;
	
	protected CorePaNexusFileWriter(CoevolutionaryHistory history, File file) {
		super(history, file, new DefaultConfiguration());
		this.historyMapping = new LinkedHashMap<String, String>();
	}
	
	public CorePaNexusFileWriter(CoevolutionaryHistory history, File file, Configuration configuration) {
		super(history, file, configuration);
		this.historyMapping = new LinkedHashMap<String, String>();
	}

	@Override
	public boolean generateOutputString() {
		super.outputString = this.cleanString("#nexus\n" + this.getTaxaString() +  this.getTreesString() + this.getCophylogenyString());
		return true;
	}

	private String cleanString(String string) {
		string = string.replaceAll("'", "");
		string = string.replaceAll("-", "");
		return string;
	}

	protected String getTaxaString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BEGIN TAXA;");
		builder.append("\n");
		builder.append("\tDIMENSIONS NTAX = "+ (super.history.getHostTree().numberOfLeafNodes() + super.history.getParasiteTree().numberOfLeafNodes()) + ";");
		builder.append("\n");
		builder.append("\tTAXLABELS");
		builder.append("\n");
		StringBuilder leafNodeBuilder = new StringBuilder();
		for (Node node : super.history.getHostTree().getLeafNodes()) {
			leafNodeBuilder.append("\t\t" + this.history.getHostTree().getNodeName(node));
			leafNodeBuilder.append("\n");			
		}
		for (Node node : super.history.getParasiteTree().getLeafNodes()) {
			leafNodeBuilder.append("\t\t" + this.history.getParasiteTree().getNodeName(node));
			leafNodeBuilder.append("\n");			
		}
		builder.append(leafNodeBuilder.toString().replaceAll(" ", "_"));
		builder.append("\t\t;");
		builder.append("\n");	
		builder.append("ENDBLOCK;");
		builder.append("\n");	
		builder.append("\n");			
		return builder.toString();
	}
	
	protected String getTreesString() {
		Map<String, String> hostTreeMap = this.getMapping(this.history.getHostTree());
		Map<String, String> parasiteTreeMap = this.getMapping(this.history.getParasiteTree());
		this.historyMapping.putAll(hostTreeMap);
		this.historyMapping.putAll(parasiteTreeMap);
		StringBuilder builder = new StringBuilder();
		builder.append("BEGIN TREES;");
		builder.append("\n");
		builder.append("\tTRANSLATE");
		StringBuilder mappingBuilder = new StringBuilder();
		for (Map.Entry<String, String> entry : this.historyMapping.entrySet()) {
			mappingBuilder.append("\n");	
			mappingBuilder.append("\t\t" + entry.getKey() + "\t" + entry.getValue() + ",");		
		}	
		mappingBuilder.delete(mappingBuilder.length()-1, mappingBuilder.length());
		mappingBuilder.append("\n");
		builder.append(mappingBuilder.toString().replaceAll(" ", "_"));
		builder.append("\t\t;");
		builder.append("\n");
		
		String hostTree = this.history.getHostTree().getNexusString(NexusStringFormat.CorePa) + ";";
		hostTree = this.replaceValues(hostTree, hostTreeMap, "\\(", ",");
		hostTree = this.replaceValues(hostTree, hostTreeMap, ",", "\\)");
		hostTree = this.replaceValues(hostTree, hostTreeMap, "\\)", ",");
		hostTree = this.replaceValues(hostTree, hostTreeMap, "\\)", "\\)");
		hostTree = this.replaceValues(hostTree, hostTreeMap, "\\)", "\\;");
		
		builder.append("\tTREE HOST = " + hostTree);
		builder.append("\n");	
		
		String parasiteTree = this.history.getParasiteTree().getNexusString(NexusStringFormat.CorePa) + ";";
		parasiteTree = this.replaceValues(parasiteTree, parasiteTreeMap, "\\(", ",");
		parasiteTree = this.replaceValues(parasiteTree, parasiteTreeMap, ",", "\\)");
		parasiteTree = this.replaceValues(parasiteTree, parasiteTreeMap, "\\)", ",");
		parasiteTree = this.replaceValues(parasiteTree, parasiteTreeMap, "\\)", "\\)");
		parasiteTree = this.replaceValues(parasiteTree, parasiteTreeMap, "\\)", "\\;");
		
		builder.append("\tTREE PARASITE = " + parasiteTree);
		builder.append("\n");			
		builder.append("ENDBLOCK;");
		builder.append("\n");	
		builder.append("\n");			
		return builder.toString();		
	}
	
	protected Map<String, String> getMapping(Tree tree) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		this.getMapping(tree.getRootNode(), tree, map);
		return map;
	}
	
	private void getMapping(Node currentNode, Tree tree, Map<String, String> map) {
		map.put(tree.getRootNode().getNodeName().substring(0,1).toUpperCase() + map.size(), tree.getNodeName(currentNode));
		if (!currentNode.isLeafNode()) {
			this.getMapping(currentNode.getLeftChild(), tree, map);
			this.getMapping(currentNode.getRightChild(), tree, map);		
		}
	}
	
	protected String replaceValues(String nexusString, Map<String,String> reference, String leadingPrefix, String endingPrefix) {
		for (Map.Entry<String, String> entry : reference.entrySet()) {
			nexusString = nexusString.replaceAll(leadingPrefix + entry.getValue() + endingPrefix, leadingPrefix + entry.getKey() + endingPrefix);		
		}
		return nexusString;
	}
	
	protected String getCophylogenyString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BEGIN COPHYLOGENY;");
		builder.append("\n");		
		builder.append("[RANKS represents the ranks of the nodes in the tree]");
		builder.append("\n");
		builder.append("[Syntax is: nodename timezone_from timezone_to]");
		builder.append("\n");
		builder.append("\tRANKS");
		for (Map.Entry<String, String> entry : this.historyMapping.entrySet()) {
			builder.append("\n");	
			builder.append("\t\t" + entry.getKey() + "\t0\t0,");		
		}			
		builder.delete(builder.length()-1, builder.length());
		builder.append("\n");
		builder.append("\t\t;");	
		builder.append("\n");	
		builder.append("\n");	
		builder.append("[PHI represents the associations from the parasite leaf nodes to the host leaf nodes]");
		builder.append("\n");
		builder.append("[Syntax is: parasite_leaf_name host_leaf_name]");
		builder.append("\n");
		builder.append("\tPHI");
		StringBuilder associationBuilder = new StringBuilder();
		for (Association association : this.history.getAssociations()) {
			associationBuilder.append("\n");
			associationBuilder.append("\t\t" + this.history.getParasiteTree().getNodeName(association.getSecondNode()) + "\t" + this.history.getHostTree().getNodeName(association.getFirstNode()) + ",");
		}
		associationBuilder.delete(associationBuilder.length()-1, associationBuilder.length());
		associationBuilder.append("\n");
		associationBuilder.append(";");
		associationBuilder.append("\n");
		builder.append(associationBuilder.toString().replaceAll(" ", "_"));
		builder.append(this.getCosts(0, 1, 1, 2));
		builder.append("ENDBLOCK;");
		builder.append("\n");	
		builder.append("\n");	
		return builder.toString();
	}
	
	protected String getCosts(int codivergenceCost, int duplciationCost, int sortingCost, int hostSwitchCost) {
		return "[COSTS represents the cost table for the operations COSPECIATION, DUPLICATION, EXTINCTION, SORTING and HOSTSWITCH]\n" +
				"[Syntax is: operation cost]\n" +
				"	COSTS\n" +
				"		COSPECIATION	" + super.configuration.getCostScheme().getCostForCodivergence() + ",\n" +
				"		DUPLICATION	" + super.configuration.getCostScheme().getCostForDuplication() + ",\n" + 
				"		SORTING	" + super.configuration.getCostScheme().getCostForSorting() + ",\n" +
				"		HOSTSWITCH	" + super.configuration.getCostScheme().getCostForSwitch() + "\n" +
				"		;\n";
	}
}
