 package reconstruction.solver.nm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.Tree;
import tree.TreeConstructor;
import util.Pair;
import association.Association;
import association.Associations;

public class HybridTreeConstructor {

	protected CoevolutionaryHistory history;
	protected Associations associationSet;
	protected Map<Association, Tree> treeMap;
	
	public HybridTreeConstructor(CoevolutionaryHistory history, Associations associationSet) {
		this.history = history;
		this.associationSet = associationSet;
		this.treeMap = new HashMap<Association, Tree>();
	}
	
	public Tree getHybridTreeForAssociationSet() {
		if (this.associationSet.size() < 1) {
			return null;
		}
		while (this.associationSet.size() > 1) {
			this.processNextSetOfClosestPoints();
		}
		return this.treeMap.get(this.associationSet.iterator().next());
	}
	
	protected void processNextSetOfClosestPoints() {
		Pair<Association, Association> pair = this.findClosestPoints(this.associationSet);
		if (null != pair) {
			Association parentAssociation = this.getParentAssociation(pair);		
			this.treeMap.put(parentAssociation, this.createNextTree(pair, parentAssociation));
			this.associationSet.delete(pair.getFirst());
			this.associationSet.delete(pair.getSecond());
			this.associationSet.add(parentAssociation);
		}
	}

	protected Association getParentAssociation(Pair<Association, Association> pair) {
		Node hostNode = this.history.getHostTree().getLowestCommonAncestor(pair.getFirst().getFirstNode(), pair.getSecond().getFirstNode());
		Node parasiteNode = pair.getSecond().getSecondNode();
		Association association = new HybridAssociation(hostNode, parasiteNode);
		return association;
	}
	
	protected Tree createNextTree(Pair<Association, Association> pair, Association parentAssociation) {
		Tree tree = TreeConstructor.createNewTree(parentAssociation.toString(), "HybridAssociationTree");
		Tree left = this.treeMap.get(pair.getFirst());
		Tree right = this.treeMap.get(pair.getSecond());
		if (null == left && null == right) {
			left = TreeConstructor.createNewTree(pair.getFirst().toString(), "HybridAssociationTree");
			right = TreeConstructor.createNewTree(pair.getSecond().toString(), "HybridAssociationTree");
		}
		else if (null == left) {
			left = TreeConstructor.createNewTree(pair.getFirst().toString(), "HybridAssociationTree");		
		}
		else if (null == right) {
			right = TreeConstructor.createNewTree(pair.getSecond().toString(), "HybridAssociationTree");		
		}
		tree.addChildren(parentAssociation.toString(), left, right);
		return tree;	
	}
	
	protected Pair<Association, Association> findClosestPoints(Associations assocaitions) {
		if (null == assocaitions || assocaitions.size() < 2) {
			return null;
		}
		if (2 == assocaitions.size()) {
			Iterator<Association> iterator = assocaitions.iterator();
			return new Pair<Association, Association>(iterator.next(), iterator.next());
		}
		else {
			List<Pair<Association, Association>> pairs = new LinkedList<Pair<Association, Association>>();
			for (Association first : assocaitions) {
				for (Association second : assocaitions) {
					if (first.equals(second)) {
						continue;
					}
					else {
						pairs.add(new Pair<Association, Association> (first, second));
					}
				}			
			}
			Pair<Association, Association> toReturn = null;
			for (Pair<Association, Association> pair : pairs) {
				if (null == toReturn) {
					toReturn = pair;
				}
				else {
					Node firstCommonAncestorOfToReturn = this.history.getHostTree().getLowestCommonAncestor(toReturn.getFirst().getFirstNode(), toReturn.getSecond().getFirstNode());
					Node firstCommonAncestorToComareTo = this.history.getHostTree().getLowestCommonAncestor(pair.getFirst().getFirstNode(), pair.getSecond().getFirstNode());
					if (firstCommonAncestorToComareTo.distanceToRoot() > firstCommonAncestorOfToReturn.distanceToRoot()) {
						toReturn = pair;
					}
				}
			}
			return toReturn;
		}
	}
}
