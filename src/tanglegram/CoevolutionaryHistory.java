package tanglegram;

import association.Associations;
import tree.Tree;

public final class CoevolutionaryHistory {

	private Tree hostTree;
	private Tree parasiteTree;
	private Associations associations;
	
	public CoevolutionaryHistory(Tree hostTree, Tree parasiteTree, Associations associations) {
		this.hostTree = hostTree;
		this.parasiteTree = parasiteTree;
		this.associations = associations;
	}
	
	public Tree getHostTree() {
		return this.hostTree;
	}
	
	public Tree getParasiteTree() {
		return this.parasiteTree;
	}
	
	public Associations getAssociations() {
		return this.associations;
	}
	
	public CoevolutionaryHistory cloneHistory() {
		Tree hostTree = this.hostTree.cloneTree();
		Tree parasiteTree = this.parasiteTree.cloneTree();
		Associations associations = this.associations.createCopy(hostTree, parasiteTree);
		return new CoevolutionaryHistory(hostTree, parasiteTree, associations);
	}
}
