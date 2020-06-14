package reconstruction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import association.Association;

import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.Tree;
import util.HashMultiMap;
import util.MultiMap;
import util.Pair;
import util.PartialMultiMap;

public class LooseParasiteMappingList {
	
	protected MultiMap<Node, LooseParasiteMapping> map;
	
	public LooseParasiteMappingList() {
		this.map = new HashMultiMap<Node, LooseParasiteMapping>();
	}
	
	public LooseParasiteMappingList(Tree hostTree) {
		this.map = new PartialMultiMap(hostTree);
	}
	
	public void add(Node parasiteNode, LooseParasiteMapping looseParasiteMapping) {
		this.map.add(parasiteNode, looseParasiteMapping);
	}
	

	public void add(Node parasiteNode, Set<LooseParasiteMapping> switchEvents) {
		for (LooseParasiteMapping mapping : switchEvents) {
			this.map.add(parasiteNode, mapping);	
		}
	}
	
	public void addLeafNode(Node parasiteNode, Node hostNode) {
		LooseParasiteMapping mapping = new LooseParasiteMapping(new MappingPoint(hostNode));
		mapping.setBestForwardPosition(hostNode.distanceToRoot());
		this.map.add(parasiteNode, mapping);
	}
	
	public void addLeafNode(Node parasiteNode, LooseParasiteMapping mapping) {
		this.map.add(parasiteNode, mapping);
	}	
	
	public boolean looseParasiteMappingIsLeafNode(Node parasiteNode) {
		Set<LooseParasiteMapping> mappings = this.getLooseParasiteMappings(parasiteNode);
		if (null != mappings) {
			for (LooseParasiteMapping mapping : mappings) {
				if (LooseParasiteMapping.Event.LeafNodeMapping.equals(mapping.getCurrentEvent()) && mapping.getCost() != Integer.MAX_VALUE) {
					return true;
				}
			}
		}
		return false;
	}
	
	public LooseParasiteMapping getLooseParasiteMapping(Node parasiteNode, MappingPoint mappingPoint) {
		Set<LooseParasiteMapping> mappings = this.getLooseParasiteMappings(parasiteNode);
		if (null != mappings) {
			for (LooseParasiteMapping mapping : mappings) {
				if (null != mappingPoint && mappingPoint.equals(mapping.getCurrentMappingPoint()) && mapping.getCost() != Integer.MAX_VALUE) {
					return mapping;
				}
			}
		}
		return null;
	}	
	
	public LooseParasiteMapping getLooseParasiteMapping(Node parasiteNode, LooseParasiteMapping.Event event) {
		Set<LooseParasiteMapping> mappings = this.getLooseParasiteMappings(parasiteNode);
		for (LooseParasiteMapping mapping : mappings) {
			if (event.equals(mapping.getCurrentEvent()) && mapping.getCost() != Integer.MAX_VALUE) {
				return mapping;
			}
		}
		return null;
	}
	
	public ParasiteMappingList generatorMappingList(CoevolutionaryHistory history) {
		ParasiteMappingList list = new ParasiteMappingList(); 
		for(Association association : history.getAssociations()) {
			list.addLeafNode(association.getSecondNode(), new MappingPoint(association.getFirstNode()));
		}
		
		List<LooseParasiteMapping> solutions = new LinkedList<LooseParasiteMapping>();
		for (LooseParasiteMapping solution : this.getLooseParasiteMappings(history.getParasiteTree().getRootNode())) {
			solutions.add(solution);
		}
		LooseParasiteMapping bestSolution = this.getBestSolution(solutions);
		Map<Node, MappingPoint> mapping = new TreeMap<Node, MappingPoint>();
		this.addToMapping(history.getParasiteTree().getRootNode(), bestSolution.getCurrentMappingPoint(), mapping);
		for (Map.Entry<Node, MappingPoint> entry : mapping.entrySet()) {
			list.add(entry.getKey(), entry.getValue());
		}
		return list;
	}
	
	public ParasiteMappingList generatorMappingList(CoevolutionaryHistory history, Map<LooseParasiteMapping, Pair<LooseParasiteMapping, LooseParasiteMapping>> solutionSpace) {
		ParasiteMappingList list = new ParasiteMappingList(); 
		
		List<LooseParasiteMapping> solutions = new LinkedList<LooseParasiteMapping>();
		for (LooseParasiteMapping solution : this.getLooseParasiteMappings(history.getParasiteTree().getRootNode())) {
			solutions.add(solution);
		}
		LooseParasiteMapping bestSolution = this.getBestSolution(solutions);
		
		Map<Node, MappingPoint> mapping = new TreeMap<Node, MappingPoint>();
		this.addToMapping(history.getParasiteTree().getRootNode(), bestSolution, mapping, solutionSpace);
		for (Map.Entry<Node, MappingPoint> entry : mapping.entrySet()) {
			list.add(entry.getKey(), entry.getValue());
		}
		return list;
	}

	private void addToMapping(Node rootNode, LooseParasiteMapping nodeMapping, Map<Node, MappingPoint> mapping, Map<LooseParasiteMapping, Pair<LooseParasiteMapping, LooseParasiteMapping>> solutionSpace) {
		if (rootNode.isLeafNode()) {
			this.processLeaves(rootNode, nodeMapping, mapping, solutionSpace);
			return;
		}
		
		mapping.put(rootNode, nodeMapping.getCurrentMappingPoint());
		
		this.addToMapping(rootNode.getLeftChild(), solutionSpace.get(nodeMapping).getFirst(), mapping, solutionSpace);
		this.addToMapping(rootNode.getRightChild(), solutionSpace.get(nodeMapping).getSecond(), mapping, solutionSpace);
	}
	
	private void processLeaves(Node leafNode, LooseParasiteMapping nodeMapping, Map<Node, MappingPoint> mapping, Map<LooseParasiteMapping, Pair<LooseParasiteMapping, LooseParasiteMapping>> solutionSpace) {
		mapping.put(leafNode, nodeMapping.getCurrentMappingPoint());
	}

	protected void addToMapping(Node current, MappingPoint mappingPoint, Map<Node, MappingPoint> mapping) {
		if (current.isLeafNode()) {
			this.handleLeafNodesInMapping(current, mappingPoint, mapping);
			return;
		}
		
		LooseParasiteMapping looseMapping = this.getLooseParasiteMapping(current, mappingPoint);
		
		mapping.put(current, looseMapping.getCurrentMappingPoint());
		
		this.addToMapping(current.getLeftChild(), looseMapping.getLeftChildsMappingPoint(), mapping);
		this.addToMapping(current.getRightChild(), looseMapping.getRightChildsMappingPoint(), mapping);
	}

	// this method only get called in derived classes
	protected void handleLeafNodesInMapping(Node current, MappingPoint mappingPoint, Map<Node, MappingPoint> mapping) {
		return;
	}
	
	public LooseParasiteMapping getBestSolution(List<LooseParasiteMapping> solutions) {
		if (null != solutions && solutions.size() > 0) {
			LooseParasiteMapping bestSolution = solutions.get(0);
			for (LooseParasiteMapping solution : solutions) {
				if (null == solution) {
					continue;
				}
				if (null == bestSolution || solution.getCost() < bestSolution.getCost()) {
					bestSolution = solution;
				}
			}
			return bestSolution;
		}	
		return null;
	}
	
	public Set<LooseParasiteMapping> getLooseParasiteMappings(Node parasiteNode) {
		return this.map.get(parasiteNode);
	}

}
