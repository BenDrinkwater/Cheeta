package reconstruction.solver.nm;

import association.Associations;
import association.CophylogeneticAssociations;
import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.Tree;

public class HybridCoevolutionaryHistoryGenerator {

	private CoevolutionaryHistory history;
	private Tree tree;
	
	public HybridCoevolutionaryHistoryGenerator(CoevolutionaryHistory history, Tree tree) {
		this.history = history;
		this.tree = tree;
	}
	
	public CoevolutionaryHistory getHistory() {
		Associations associations = this.getAssociationSet();
		if (null == associations) {
			return null;
		}
		return new CoevolutionaryHistory(this.history.getHostTree(), tree, this.getAssociationSet());
	}
	
	protected Associations getAssociationSet() {
		if (null == this.history || null == this.tree) {
			return null;
		}
		Associations associations = new CophylogeneticAssociations();
		for (Node node : this.tree.getLeafNodes()) {
			associations.add(new HybridAssociation(this.getNode(node), node));
		}
		return associations;
	}
	
	protected Node getNode(Node node) {
		if (null != node) {
			String[] tuples = node.toString().split(":");
			if (tuples.length > 1 && tuples[1].length() > 4) {			
				Node toReturn = this.history.getHostTree().getNodeWithPrefix(tuples[1]);	
				return toReturn;
			}
		}
		return null;
	}
}
