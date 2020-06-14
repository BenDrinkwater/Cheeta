package association;

import util.Pair;
import tree.Node;

/**
 * The CophylogeneticAssociation encapsulates the colonization of a Parasite
 * on a specific Host. 
 * 
 * Within the calculations considered herein all Parasites must be associated with a Host
 * 
 * @author Ben
 *
 */
public class CophylogeneticAssociation implements Association {

	private Pair<Node, Node> nodes;
	
	public CophylogeneticAssociation(Node firstNode, Node secondNode) {
		this.nodes = new Pair<Node, Node>(firstNode, secondNode);
	}
	
	/**
	 * Gets the first (host tree) node for the association
	 * 
	 * @return first node of the association
	 */	
	public Node getFirstNode() {
		return this.nodes.getFirst();
	}
	
	/**
	 * Gets the second (parasite tree) node for the association
	 * 
	 * @return second node of the association
	 */	
	public Node getSecondNode() {
		return this.nodes.getSecond();
	}
	
	public void replaceSecondNode(Node node) {
		this.nodes.setSecond(node);
	}

	 /**
	 * Equality method for the Association class. Compares each node.
	 *
	 * @param compareTo object to compare association against
	 * @return true if they are equal otherwise false
	 */
	@Override
	public boolean equals(Object compareTo) {
		if (null != compareTo && compareTo instanceof CophylogeneticAssociation) {
			return (compare(this.nodes.getFirst(), ((CophylogeneticAssociation) compareTo).nodes.getFirst()) &&
					compare(this.nodes.getSecond(), ((CophylogeneticAssociation) compareTo).getSecondNode()));
		}
		return false;
	}	
	
	/**
	 * CompareTo method for the Association class to maintain order of a list of Association
	 *
	 * @param compareTo association to compare to
	 * @return negative indicates less than, 0 means equals and grater than 1 means greater than
	 */
	@Override
	public int compareTo(Association compareTo) {
		if (this.nodes.getFirst().compareTo(compareTo.getFirstNode()) < 0) {
			return -1;
		}
		else if (this.nodes.getFirst().compareTo(compareTo.getFirstNode()) > 0) {
			return 1;
		}
		else {
			return this.nodes.getSecond().compareTo(compareTo.getSecondNode());
		}
	}       

	/**
	 * Helper method to compare two objects for equality using null safety
	 *
	 * @param object1 object 1 for comparison
	 * @param object2 object 2 for comparison
	 * @return true if the objects are equal otherwise false
	 */
	private static boolean compare(Object object1, Object object2) {               
		if (null == object1) {
			return null == object2;
		}
		else {
			if (null == object2) {
				return false;
			}
			return object1.equals(object2);
		}
	}       

	/**
	 * Override of the hash code to allow for even more elements to be included in a hash map or set
	 *
	 * @return a hash function for the node
	 */
	@Override
	public int hashCode() {
		return this.nodes.hashCode();
	}

	/**
	 * To string method for the Association Class used for printing to Nexus Files
	 *
	 * @return a string representation of the association between the first and second node
	 */
	public String toString() {
		return this.nodes.getSecond().toString() + ":" + this.nodes.getFirst().toString();
	}
}
