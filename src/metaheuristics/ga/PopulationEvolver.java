package metaheuristics.ga;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.Tree;
import association.Associations;

public class PopulationEvolver {

	private final Tree hostTree;
	private final Tree parasiteTree;
	private final Associations associations;
	private final int populationSize;
	
	public PopulationEvolver(final Tree hostTree, final Tree parasiteTree, final Associations associations, final int populationSize) {
		this.hostTree = hostTree;
		this.parasiteTree = parasiteTree;
		this.associations = associations;
		this.populationSize = (int) Math.floor(populationSize / 2.0);
	}
	
	public ArrayList<CoevolutionaryHistory> getNextHistories() {
		ArrayList<CoevolutionaryHistory> histories = new ArrayList<CoevolutionaryHistory>();
		
		histories.add(new CoevolutionaryHistory(this.hostTree, this.parasiteTree, this.associations));					
		for (int i = 0; i < this.populationSize; ++i) {
			final Tree mutatedTree = this.getMutatedTree(this.hostTree.cloneTree());
			
			if (TreeTimingCrosser.validNodeTiming(mutatedTree.getNodeTimings())) {
				CoevolutionaryHistory nextHistory = new CoevolutionaryHistory(mutatedTree.cloneTree(), this.parasiteTree, this.associations);
				histories.add(nextHistory);	
			}
		}
		
		return histories;
	}

	protected synchronized Tree getMutatedTree(final Tree tree) {
		Tree mutatedTree = tree.cloneTree();
		Map<Node, Integer> timings = this.mutate(mutatedTree);
		timings = TreeTimingCrosser.scaleTimings(mutatedTree, timings);
		mutatedTree.setUniqueNodeTimings(timings);
		return mutatedTree;
	}
	
	protected Map<Node, Integer> mutate(final Tree tree) {
		Node first = this.selectRandomInternalNode();
		if (null == first) {
			return tree.getNodeTimings();
		}
		return this.mutate(tree, first);
	}
	
	protected Map<Node, Integer> mutate(final Tree tree, final Node first) {		
		List<Node> validSwitchNodes = this.validSwitchNodes(tree, first);
		if (0 == validSwitchNodes.size()) {
			return tree.getNodeTimings();
		}
		Node second = this.selectRandomNodeFromList(validSwitchNodes);
		
		return this.mutate(tree, first, second);		
	}
	
	protected List<Node> validSwitchNodes(final Tree tree, final Node node) {
		List<Node> minusAncestors = tree.listMinusAncestors(node);
		List<Node> list = new LinkedList<Node>();
		for (Node minusAncestor : minusAncestors) {
			if (null == tree.distanceBetweenNodes(minusAncestor, node) && !node.isLeafNode()) {
				list.add(minusAncestor);
			}
		}
		return list;
	}
	
	protected Node selectRandomInternalNode() {
		Random random = new Random();
		int nodeSelector = random.nextInt(this.hostTree.numberOfInteranlNodes());
		int counter = 0;
		
		Node toReturn = null;
		
		Set<Node> nodes = this.hostTree.getAllNodes();
		
		for (Node node : nodes) {
			if (!node.isLeafNode()) {
				if (counter++ == nodeSelector) {
					toReturn = node;
					break;
				}
			}
		}
		
		return toReturn;
	}
	
	protected Node selectRandomNodeFromList(List<Node> list) {
		if (null == list || 0 == list.size()) {
			return null;
		}
		Random random = new Random();
		int nodeSelector = random.nextInt(list.size());
		int counter = 0;
		
		Node toReturn = null;
		for (Node node : list) {
			if (counter++ == nodeSelector) {
				toReturn = node;
				break;
			}
		}
		return toReturn;
	}	
	
	protected Map<Node, Integer> mutate(final Tree tree, final Node first, final Node second) {			
		Map<Node, Integer> nodeTimingsForTree = tree.getNodeTimings();
		
		Integer timing1 = nodeTimingsForTree.get(first);
		Integer timing2 = nodeTimingsForTree.get(second);
		
		nodeTimingsForTree.put(second, timing1);
		nodeTimingsForTree.put(first, timing2);
		return nodeTimingsForTree;
	}	
}
