package reconstruction.solver.tc;

import association.Association;
import association.Associations;
import association.CophylogeneticAssociation;
import association.CophylogeneticAssociations;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class TreeCollapser extends TreeCollapseUtil {
	
	public enum EdgeResolver {
		resolveBoth,
		onlyResolveFirst,
		onlyResolveSecond
	}
	
	private CoevolutionaryHistory history;

	public TreeCollapser(CoevolutionaryHistory history) {
		this.history = history;
	}
	
	protected boolean collapseHostTree(Node node) {
		Associations associationsForFirstNode = new CophylogeneticAssociations(this.history.getAssociations().getAssociationsForHostNode(node));
		Associations associationsForSecondNode = new CophylogeneticAssociations(this.history.getAssociations().getAssociationsForHostNode(this.alternateNodeInNodePair(node)));
		
		Node parentNode = node.getParent();
		
		for(Association association : associationsForFirstNode) {
			Node parasiteNode = association.getSecondNode();
			this.history.getAssociations().delete(association);
			this.history.getAssociations().add(new CophylogeneticAssociation(parentNode, parasiteNode));
		}
		
		for(Association association : associationsForSecondNode) {
			Node parasiteNode = association.getSecondNode();
			this.history.getAssociations().delete(association);
			this.history.getAssociations().add(new CophylogeneticAssociation(parentNode, parasiteNode));
		}
		
		return this.history.getHostTree().deleteLeafNodePair(this.alternateNodeInNodePair(node), node);
	}	
	
	protected boolean collapseParasiteTree(Node node, EdgeResolver edgeResolver) {
		Node parentNode = node.getParent();
		
		Associations associationsForFirstNode = this.history.getAssociations().getAssociationsForParasiteNode(node);
	
		for(Association association : associationsForFirstNode) {
			this.history.getAssociations().delete(association);
			if (!(EdgeResolver.onlyResolveSecond == edgeResolver)) {
				Node hostNode = association.getFirstNode();
				this.history.getAssociations().add(new CophylogeneticAssociation(hostNode, parentNode));
			}
		}	
		
		Associations associationsForSecondNode = this.history.getAssociations().getAssociationsForParasiteNode(this.alternateNodeInNodePair(node));
	
		for(Association association : associationsForSecondNode) {
			this.history.getAssociations().delete(association);
			if (!(EdgeResolver.onlyResolveFirst == edgeResolver)) {
				Node hostNode = association.getFirstNode();
				this.history.getAssociations().add(new CophylogeneticAssociation(hostNode, parentNode));
			}
		}
		
		return this.history.getParasiteTree().deleteLeafNodePair(this.alternateNodeInNodePair(node), node);
	}
}
