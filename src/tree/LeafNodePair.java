package tree;

import util.Pair;

public class LeafNodePair implements Comparable<LeafNodePair> {

	protected Pair<Node, Node> leafNodePair;
	
	/**
	 * Empty constructor for the leaf node pair class
	 * set private to prevent its use
	 */
	private LeafNodePair() {
		this.leafNodePair = null;
	}
	
	/**
	 * Constructor for a leaf node pair object where both the first and second node are set
	 * 
	 * @param first first of the two nodes that make up the leaf node pair
	 * @param second second of the two nodes that make up the leaf node pair
	 */
	public LeafNodePair(Node first, Node second) {
		this();
		this.leafNodePair = new Pair<Node, Node>(first, second);
	}
	
	/**
	 * Gets the first node of the leaf node pair
	 * 
	 * @return the first node of the leaf node pair
	 */
	public Node getFirst() {
		return this.leafNodePair.getFirst();
	}
	
	/**
	 * Gets the second node of the leaf node pair
	 * 
	 * @return the second node of the leaf node pair
	 */
	public Node getSecond() {
		return this.leafNodePair.getSecond();
	}
	
	/**
	 * Gets the parent node of the leaf node pair
	 * 
	 * @return the parent of the leaf node pair or if invalid leaf node pair null
	 */
	public Node getParent() {
		if (!this.isValidLeafNodePair()) {
			return null;
		}
		return this.leafNodePair.getFirst().getParent();
	}
	
	/**
	 * Checks if this is infact a valid leaf node pair
	 * 
	 * @return true if a valid leaf node pair that is both ndoes
	 * share a common parent else false
	 */
	public boolean isValidLeafNodePair() {
		return null != this.leafNodePair.getFirst() &&
				null != this.leafNodePair.getSecond() && 
				this.leafNodePair.getFirst().isLeafNode() && 
				this.leafNodePair.getSecond().isLeafNode() && 
				this.leafNodePair.getFirst().getParent().equals(
						this.leafNodePair.getSecond().getParent());
	}
	
	public String toString() {
		if (this.leafNodePair.getFirst().toString().compareTo(this.leafNodePair.getSecond().toString()) < 1) {
			return "(" + this.leafNodePair.getFirst() + "," 
					+ this.leafNodePair.getSecond() + ")";
		}
		else {
			return "(" + this.leafNodePair.getSecond() + "," 
					+ this.leafNodePair.getFirst() + ")";			
		}
	}

	@Override
	public int compareTo(LeafNodePair compareTo) {
		int distanceCompare = compareTo.getParent().distanceToRoot() - this.getParent().distanceToRoot();
		if (0 == distanceCompare && !this.equals(compareTo)) {
			return -1;
		}
		else if (this.equals(compareTo)){
			return 0;
		}
		return distanceCompare;
	}
	
	@Override
	public boolean equals(Object object) {
		return this.toString().equals(object.toString());
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}	
}
